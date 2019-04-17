package com.lv.qm.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.lv.libsocket.WsManager;


/**
 * 作者：created by albert on 2019/4/4 10:38
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class WsService extends Service {

    private static final String TAG = "WsService";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getBooleanExtra("isOpened", true)) {
            initSocket();
            Toast.makeText(this, "open", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "close", Toast.LENGTH_SHORT).show();
            stopWebSocket();
        }
        return START_STICKY;
    }

    private void stopWebSocket() {
        WsManager.getInstance(this).disconnect();
    }

    private void initSocket() {
        //第一步 初始化url
        WsManager.getInstance(this).setUrl("wss:192....");
        //第二步 初始化发送socket数据
        WsManager.getInstance(this).setSendRequest("{\"code\": 200}");
        //第三步 初始化websocket
        WsManager.getInstance(this).init();
        //第四步 监听推送过来的消息
        WsManager.getInstance(this).setPushListener((text) -> {
            Log.i(TAG, "push getText: " + text);
        });
        //第五步 监听连接成功后需要在service中做的其他操作
        WsManager.getInstance(this).setConnectionListener(new WsManager.onSocketConnectionListener() {
            @Override
            public void connectedSuccess() {
                Log.i(TAG, "connectedSuccess: do something");
            }

            @Override
            public void disConnected() {
                Log.i(TAG, "disConnected: do something");

            }

            @Override
            public void conncetedError() {
                Log.i(TAG, "conncetedError: do something");
            }
        });
    }
}
