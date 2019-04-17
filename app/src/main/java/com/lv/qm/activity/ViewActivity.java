package com.lv.qm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lv.qm.R;
import com.lv.qm.weight.VerticalScrolledListview;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    private List<String> mList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        mList.add("更新最后1次心跳时间");
        mList.add("更新最后2次心跳时间");
        mList.add("更新最后3次心跳时间");
        mList.add("更新最后4次心跳时间");
        mList.add("更新最后5次心跳时间");
        mList.add("更新最后6次心跳时间");

        VerticalScrolledListview verticalScrolledListview = findViewById(R.id.verificationcodeview);
        verticalScrolledListview.setData(mList);
    }
}
