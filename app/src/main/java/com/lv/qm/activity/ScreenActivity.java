package com.lv.qm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lv.qm.R;

public class ScreenActivity extends AppCompatActivity {

    private TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        mTextView = findViewById(R.id.screen_text);

        mTextView.setText("show me number");
    }
}
