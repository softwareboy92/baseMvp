package com.lv.qm.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lv.qm.R;
import com.lv.qm.service.WsService;

/**
 * 作者：created by albert on 2019/4/4 10:13
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class WebSocketActivity extends Activity {

    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_websocket);
        initView();
    }

    private void initView() {
        findViewById(R.id.start_service).setOnClickListener((v)->{
            mIntent = new Intent(this,WsService.class);
            mIntent.putExtra("isOpened",true);
            startService(mIntent);
        });
        findViewById(R.id.stop_service).setOnClickListener((v)->{
            mIntent = new Intent(this, WsService.class);
            mIntent.putExtra("isOpened",false);
            startService(mIntent);
        });

    }
}
