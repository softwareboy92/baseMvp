package com.lv.qm.activity;

import android.os.Bundle;

import com.lv.qm.exitactivity.SlideBackAppCompatActivity;

public abstract class BaseActivity extends SlideBackAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //设置是否可以左滑返回，必须在super.onCreate（）之前
        setSlideable(isActivitySlideBack());

        super.onCreate(savedInstanceState);

    }

    public abstract boolean isActivitySlideBack();


}