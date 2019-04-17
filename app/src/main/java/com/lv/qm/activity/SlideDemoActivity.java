package com.lv.qm.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.lv.qm.R;


public class SlideDemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutInflater.from(this).inflate(R.layout.activity_slide_demo,null,false));
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SlideDemoActivity.this, SlideDemoActivity.class));

            }
        });
    }

    @Override
    public boolean isActivitySlideBack() {
        return true;
    }


}
