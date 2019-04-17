package com.lv.qm.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.dss886.emotioninputdetector.library.EmotionInputDetector;
import com.lv.qm.R;
import com.lv.qm.adapter.EmotionAdapter;
import com.lv.qm.listener.GlobalOnItemClickManager;
import com.lv.qm.weight.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：created by albert on 2018/9/28 14:15
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class FirstActivity extends FragmentActivity {

    private ListView mListView;
    private List<String> mList = new ArrayList<>();
    private ArrayAdapter mAdapter;
    private Button mButton;
    private EditText mEditText;

    private EmotionInputDetector mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initDatas();
        initViews();
        initAction();
    }

    private void initAction() {
        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, mList);
        mListView.setAdapter(mAdapter);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mEditText.getText().toString();
                if (TextUtils.isEmpty(content)) {

                } else {
                    mList.add(content);
                    mAdapter.notifyDataSetChanged();
                    mEditText.setText("");
                    mListView.setSelection(mListView.getBottom());
                }
            }
        });

    }

    private void initDatas() {
        for (int i = 0; i < 10; i++) {
            mList.add("显示：" + i);
        }
    }

    private void initViews() {
        mListView = findViewById(R.id.chat_listview);
        mButton = findViewById(R.id.btn_send);
        mEditText = findViewById(R.id.edit_text);
        mDetector = EmotionInputDetector.with(this)
                .setEmotionView(findViewById(R.id.emotion_layout))
                .bindToContent(mListView)
                .bindToEditText(mEditText)
                .bindToEmotionButton(findViewById(R.id.emotion_button))
                .build();

        setUpEmotionViewPager();
    }


    private void setUpEmotionViewPager() {
        final String[] titles = new String[]{"经典"};
        EmotionAdapter mViewPagerAdapter = new EmotionAdapter(getSupportFragmentManager(), titles);
        final ViewPager mViewPager = findViewById(R.id.pager);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setCurrentItem(1);

        SlidingTabLayout slidingTabLayout = findViewById(R.id.sliding_tabs);
        slidingTabLayout.setCustomTabView(R.layout.widget_tab_indicator, R.id.text);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.colorPrimary));
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(mViewPager);

        GlobalOnItemClickManager globalOnItemClickListener = GlobalOnItemClickManager.getInstance();
        globalOnItemClickListener.attachToEditText(findViewById(R.id.edit_text));
    }


    @Override
    public void onBackPressed() {
        if (!mDetector.interceptBackPress()) {
            super.onBackPressed();
        }
    }

}
