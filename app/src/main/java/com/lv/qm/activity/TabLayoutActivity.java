package com.lv.qm.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lv.qm.R;

public class TabLayoutActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TextView tv_tab_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        tabLayout = findViewById(R.id.tabLayout);
        initTab();
    }

    private void initTab() {
        android.support.design.widget.TabLayout.Tab tab = tabLayout.newTab().setText("全部");
        tabLayout.addTab(tab);

        //待付款栏目-加载自定义显示小红点的布局
        tab = tabLayout.newTab();
        tab.setCustomView(R.layout.red_node);
        tv_tab_title = tab.getCustomView().findViewById(R.id.tv_tab_title);
        tv_tab_title.setText("待付款");
        tabLayout.addTab(tab);

        tab = tabLayout.newTab().setText("待发货");
        tabLayout.addTab(tab);

        tab = tabLayout.newTab().setText("待收货");
        tabLayout.addTab(tab);

        tab = tabLayout.newTab().setText("已完成");
        tabLayout.addTab(tab);

        tab = tabLayout.newTab().setText("已取消");
        tabLayout.addTab(tab);

        //添加tabLayout选中监听
        tabLayout.addOnTabSelectedListener(new android.support.design.widget.TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(android.support.design.widget.TabLayout.Tab tab) {
                //设置选中时的文字颜色
                if (tab.getCustomView() != null) {
                    tv_tab_title.setTextColor(getResources().getColor(R.color.colorAccent));
                }
            }

            @Override
            public void onTabUnselected(android.support.design.widget.TabLayout.Tab tab) {
                //设置未选中时的文字颜色
                if (tab.getCustomView() != null) {
                    tv_tab_title.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                }
            }

            @Override
            public void onTabReselected(android.support.design.widget.TabLayout.Tab tab) {

            }
        });
    }
}
