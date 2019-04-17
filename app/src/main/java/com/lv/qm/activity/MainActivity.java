package com.lv.qm.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lv.monkey.utils.ToastUtils;
import com.lv.monkey.view.BaseActivity;
import com.lv.photo.imagechose.utils.ImageSelector;
import com.lv.qm.App;
import com.lv.qm.R;
import com.lv.qm.contract.MainContract;
import com.lv.qm.present.MainPresenter;
import com.lv.scan.zbar.lib.zbarcode.CaptureActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Notification.VISIBILITY_SECRET;


/**
 * 作者：created by albert on 2018/9/18 16:27
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, View.OnClickListener {

    private int REQUEST_CODE = 11;
    private int REQUEST_CODE_IMAGE_CHOSE = 10;


    @BindView(R.id.textview)
    TextView mTextView;
    @BindView(R.id.textview1)
    TextView mTextView1;
    @BindView(R.id.textview2)
    TextView mTextView2;
    @BindView(R.id.textview3)
    TextView mTextView3;
    @BindView(R.id.textview4)
    TextView mTextView4;
    @BindView(R.id.textview5)
    TextView mTextView5;
    @BindView(R.id.textview6)
    TextView mTextView6;
    @BindView(R.id.textview7)
    TextView mTextView7;
    @BindView(R.id.textview8)
    TextView mTextView8;
    @BindView(R.id.textview9)
    TextView mTextView9;
    @BindView(R.id.textview10)
    TextView mTextView10;
    @BindView(R.id.textview11)
    TextView mTextView11;
    @BindView(R.id.textview12)
    TextView mTextView12;
    @BindView(R.id.textview13)
    TextView mTextView13;
    @BindView(R.id.textview14)
    TextView mTextView14;

    @BindView(R.id.textview15)
    TextView mTextView15;

    @BindView(R.id.textview16)
    TextView mTextView16;

    @BindView(R.id.textview17)
    TextView mTextView17;

    @BindView(R.id.textview18)
    TextView mTextView18;

    @BindView(R.id.textview19)
    TextView mTextView19;
    @BindView(R.id.textview20)
    TextView mTextView20;

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    @Override
    protected MainPresenter initPresenter() {
        if (mPresenter == null)
            mPresenter = new MainPresenter();
        return mPresenter;
    }

    @Override
    protected int getActivityLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void refershDatas(Context context, Intent intent) {
        Toast.makeText(this, "刷新数据操作", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.textview,
            R.id.textview1,
            R.id.textview2,
            R.id.textview3,
            R.id.textview4,
            R.id.textview5,
            R.id.textview6,
            R.id.textview7,
            R.id.textview8,
            R.id.textview9,
            R.id.textview10,
            R.id.textview11,
            R.id.textview12,
            R.id.textview13,
            R.id.textview14,
            R.id.textview15,
            R.id.textview16,
            R.id.textview17,
            R.id.textview18,
            R.id.textview19,
            R.id.textview20})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textview1:
                Intent intent0 = new Intent(this, SecondActivity.class);
                startActivity(intent0);
                break;
            case R.id.textview:
                Intent intent1 = new Intent(this, FirstActivity.class);
                startActivity(intent1);
                break;
            case R.id.textview2:
                Intent intent2 = new Intent(this, ThirdActivity.class);
                startActivity(intent2);
                break;
            case R.id.textview3:
                Intent intent3 = new Intent(MainActivity.this, StandardViewPagerActivity.class);
                startActivity(intent3);
                break;
            case R.id.textview4:
                Intent intent4 = new Intent(MainActivity.this, CircleViewPagerActivity.class);
                startActivity(intent4);
                break;
            case R.id.textview5:
                startActivity(new Intent(this, ScreenActivity.class));
                break;
            case R.id.textview6:
                Intent intent6 = new Intent(MainActivity.this, RecycleActivity.class);
                startActivity(intent6);
                break;
            case R.id.textview7:
                //todo
                break;
            case R.id.textview8:
                Intent intent8 = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent8, REQUEST_CODE);
                break;
            case R.id.textview9:
                Intent intent9 = new Intent(MainActivity.this, TabLayoutActivity.class);
                startActivity(intent9);
                break;

            case R.id.textview10:
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(false)  //设置是否单选
                        .setMaxSelectCount(9) // 图片的最大选择数量，小于等于0时，不限数量。
                        .setViewImage(true) //是否点击放大图片查看,，默认为true
                        .start(this, REQUEST_CODE_IMAGE_CHOSE); // 打开相册
                break;
            case R.id.textview11:
                Intent intent = new Intent();  //Itent就是我们要发送的内容
                intent.setAction(App.BROADCAST_ACTION_DISC);   //设置你这个广播的action，只有和这个action一样的接受者才能接受者才能接收广播
                sendBroadcast(intent, App.BROADCAST_PERMISSION_DISC);   //发送广播
                break;
            case R.id.textview12:
                Intent intent7 = new Intent(this, BackGroundFragmentActivity.class);
                startActivity(intent7);
                break;
            case R.id.textview13:
                Intent intent13 = new Intent(this, DemoPushActivity.class);
                startActivity(intent13);
                break;
            case R.id.textview14:
                Intent intent14 = new Intent(this, SlideDemoActivity.class);
                startActivity(intent14);
                break;

            case R.id.textview15:
                Intent intent15 = new Intent(this, FragmentClickActivity.class);
                startActivity(intent15);
                break;
            case R.id.textview16:
                showNotifactionEven01();
                break;
            case R.id.textview17:
                //音频视频
                break;
            case R.id.textview18:
                Intent intent18 = new Intent(this,ViewActivity.class);
                startActivity(intent18);
                break;
            case R.id.textview19:
                Intent intent19 = new Intent(this,WebSocketActivity.class);
                startActivity(intent19);
                break;
            case R.id.textview20:
                Intent intent20 = new Intent(this,CustomDialogActivity.class);
                startActivity(intent20);
                break;
        }
    }


    private void showNotifactionEven01() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel_id", "channel_name", NotificationManager.IMPORTANCE_DEFAULT);
            channel.canBypassDnd();//设置是否开启勿扰模式通知
            channel.enableLights(true);//新消息来的时候是否闪光
            channel.setLockscreenVisibility(VISIBILITY_SECRET);//锁屏显示通知
            channel.setLightColor(Color.RED);
            channel.canShowBadge();
            channel.enableVibration(true);//是否震动
            channel.getAudioAttributes();//获取系统响铃
            channel.getGroup();//设置群组
            channel.setBypassDnd(true);
            channel.setVibrationPattern(new long[]{100, 200, 100});//震动频率
            channel.shouldShowLights();//是否会闪光

            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            manager.createNotificationChannel(channel);


            Notification notification = new Notification.Builder(this)
                    .setAutoCancel(true)
                    .setChannelId("channel_id")
                    .setContentTitle("您有一条新消息")
                    .setContentText("恭喜您，中奖了")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .build();


            manager.notify(1, notification);

        } else {
            showFullScreen(this, 1);
        }


    }

    /**
     * 悬挂式，支持6.0以上系统
     *
     * @param context
     */
    public static void showFullScreen(Context context, int id) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "defult");
        Intent mIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setAutoCancel(true);
        builder.setContentTitle(context.getResources().getString(R.string.app_name));
        builder.setContentText("恭喜您中奖了");
        builder.setWhen(System.currentTimeMillis());
        builder.setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {

            String extra_string = data.getStringExtra("extra_string");
            ToastUtils.getInstanc(this).showToast(extra_string);
        } else if (requestCode == REQUEST_CODE_IMAGE_CHOSE && data != null) {
            //获取选择器返回的数据
            ArrayList<String> images = data.getStringArrayListExtra(
                    ImageSelector.SELECT_RESULT);
            if (images.size() != 0) {
                for (String image : images) {
                    ToastUtils.getInstanc(this).showToast(image);
                }
            }
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
