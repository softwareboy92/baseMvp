package com.lv.libsocket;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


public class WsManager {

    private static WsManager mInstance;
    private final String TAG = "WsManager";
    private AtomicLong seqId = new AtomicLong(SystemClock.uptimeMillis());//每个请求的唯一标识
    private static final long HEARTBEAT_INTERVAL = 10 * 1000;//心跳间隔

    private static final int FRAME_QUEUE_SIZE = 5;
    private static final int CONNECT_TIMEOUT = 5 * 1000;
    private String url;

    private Context mContext;
    private WsStatus mStatus;
    private WebSocket ws;
    private WsListener mListener;

    private int reconnectCount = 0;//重连次数
    private long minInterval = 3 * 1000;//重连最小时间间隔
    private long maxInterval = 60 * 1000;//重连最大时间间隔

    private String sendRequest;


    public void setSendRequest(String sendRequest) {
        this.sendRequest = sendRequest;
    }

    private onPushListener mPushListener;

    private onSocketConnectionListener mConnectionListener;

    public void setConnectionListener(onSocketConnectionListener connectionListener) {
        mConnectionListener = connectionListener;
    }

    public void setPushListener(onPushListener pushListener) {
        mPushListener = pushListener;
    }

    public interface onPushListener{
        void getPushText(String text);
    }

    public interface  onSocketConnectionListener{
        void connectedSuccess();
        void disConnected();
        void conncetedError();
    }


    public void setUrl(String url) {
        this.url = url;
    }

    private Handler mHandler = new Handler();

    public WsManager(Context context) {
        mContext = context;
    }
    /**
     * 单例模式
     *
     * @return
     */
    public static WsManager getInstance(Context mContext) {
        if (mInstance == null) {
            synchronized (WsManager.class) {
                if (mInstance == null) {
                    mInstance = new WsManager(mContext);
                }
            }
        }
        return mInstance;
    }

    /**
     * 开始心跳
     */
    private void startHeartbeat() {
        mHandler.postDelayed(heartbeatTask, HEARTBEAT_INTERVAL);
    }


    /**
     * 发送消息给服务端
     */
    private  void sendReq() {
        if (!isNetConnect(mContext)) {
            return;
        }
        if (TextUtils.isEmpty(sendRequest)) {
            return;
        }
        String request = sendRequest;
        ws.sendText(new Gson().toJson(request));
    }


    /**
     * 初始化
     */
    public void init() {
        try {
            reconnectCount = 0;
            mListener = new WsListener();
            ws = new WebSocketFactory().createSocket(url, CONNECT_TIMEOUT)
                    .setFrameQueueSize(FRAME_QUEUE_SIZE)//设置帧队列最大值为5
                    .setMissingCloseFrameAllowed(false)//设置不允许服务端关闭连接却未发送关闭帧
                    .addListener(mListener)//添加回调监听
                    .connectAsynchronously();//异步连接
            setStatus(WsStatus.CONNECTING);
            Log.d(TAG, "init: 第一次连接");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * 继承默认的监听空实现WebSocketAdapter,重写我们需要的方法
     * onTextMessage 收到文字信息
     * onConnected 连接成功
     * onConnectError 连接失败
     * onDisconnected 连接关闭
     */
    class WsListener extends WebSocketAdapter {
        @Override
        public void onTextMessage(WebSocket websocket, String text) throws Exception {
            super.onTextMessage(websocket, text);
            Log.d(TAG, "onTextMessage: " + text);
            //提供一个对外的方法，来处理这里面的参数
            getPushMessage(text);
        }


        @Override
        public void onConnected(WebSocket websocket, Map<String, List<String>> headers)
                throws Exception {
            super.onConnected(websocket, headers);
            Log.d(TAG, "onConnected: 连接成功");
            setStatus(WsStatus.CONNECT_SUCCESS);
            cancelReconnect();//连接成功的时候取消重连，初始化连接次数
            startHeartbeat();
            mConnectionListener.connectedSuccess();
        }


        @Override
        public void onConnectError(WebSocket websocket, WebSocketException exception)
                throws Exception {
            super.onConnectError(websocket, exception);
            Log.d(TAG, "onConnectError:连接错误" + exception.getMessage());
            setStatus(WsStatus.CONNECT_FAIL);
            reconnect();//连接错误的时候调用重连方法
            mConnectionListener.conncetedError();
        }


        @Override
        public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer)
                throws Exception {
            super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
            Log.d(TAG, "onDisconnected: 断开连接");
            setStatus(WsStatus.CONNECT_FAIL);
            reconnect();
            mConnectionListener.disConnected();
        }
    }

    /**
     * 获取到推送过来的数据
     * @param text
     */
    private void getPushMessage(String text) {
        mPushListener.getPushText(text);
    }

    private void setStatus(WsStatus status) {
        this.mStatus = status;
    }

    private WsStatus getStatus() {
        return mStatus;
    }

    /**
     * 断开连接
     */
    public void disconnect() {
        if (ws != null)
            ws.disconnect();
        if (mHandler != null) {
            mHandler.removeCallbacks(heartbeatTask);
            mHandler = null;
        }

    }

    /**
     * 心跳
     */
    private Runnable heartbeatTask = new Runnable() {
        @Override
        public void run() {
            sendReq();
            mHandler.postDelayed(heartbeatTask, HEARTBEAT_INTERVAL);
        }
    };


    public void reconnect() {
        if (!isNetConnect(mContext)) {
            reconnectCount = 0;
            Log.i(TAG, "reconnect: 重连网络不可用");
            return;
        }
        if (ws != null && !ws.isOpen() && getStatus() != WsStatus.CONNECTING) {
            reconnectCount++;
            setStatus(WsStatus.CONNECTING);

            cancelHeartbeat();//取消心跳

            long reconnectTime = minInterval;

            if (reconnectCount > 3) {
                long temp = minInterval * (reconnectCount - 2);
                reconnectTime = temp > maxInterval ? maxInterval : temp;
            }
            Log.d(TAG, "准备开始第" + reconnectCount + "次重连,重连间隔" + reconnectTime + " -- url:");
            mHandler.postDelayed(mReconnectTask, reconnectTime);
        }
    }

    private void cancelHeartbeat() {
        if (heartbeatTask != null)
            mHandler.removeCallbacks(heartbeatTask);
    }

    /**
     * 重新连接的runnable
     */
    private Runnable mReconnectTask = () -> {
        try {
            Log.i(TAG, "mReconnectTask ");
            mListener = new WsListener();
            ws = new WebSocketFactory().createSocket(url, CONNECT_TIMEOUT)
                    .setFrameQueueSize(FRAME_QUEUE_SIZE)
                    .setMissingCloseFrameAllowed(false)
                    .addListener(mListener)
                    .connectAsynchronously();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "mReconnectTask: " + e.toString());
        }
    };

    /**
     * 取消重连
     */
    private void cancelReconnect() {
        Log.i(TAG, "cancelReconnect");
        reconnectCount = 0;
        mHandler.removeCallbacks(mReconnectTask);
    }

    /**
     * 检查网络是否可用
     *
     * @return
     */
    private boolean isNetConnect(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }


}
