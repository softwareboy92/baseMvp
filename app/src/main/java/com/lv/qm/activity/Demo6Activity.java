package com.lv.qm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lv.qm.R;
import com.lv.qm.weight.VerificationCodeView;

public class Demo6Activity extends AppCompatActivity {


    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_demo6);
        VerificationCodeView verificationcodeview = findViewById(R.id.verificationcodeview);
        textView = findViewById(R.id.text);
        verificationcodeview.setOnCodeFinishListener(new VerificationCodeView.OnCodeFinishListener() {
            @Override
            public void onComplete(String content) {
                textView.setText(content);
            }
        });
    }

}
