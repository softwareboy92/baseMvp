package com.lv.qm.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lv.qm.R;
import com.lv.qm.fragment.ViewPagerFragment;
import com.lv.qm.weight.NevigationViewPager;

import java.util.ArrayList;
import java.util.List;




/**
 * 作者：created by albert on 2018/10/8 11:40
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class ThirdActivity extends FragmentActivity {

    private TextView mTextView;


    private List<Integer> pageList = new ArrayList<>();

    private TabLayout mTabLayout;
    private NevigationViewPager mNevigationViewPager;
    private FragmentAdapter mAdapter;
    private RelativeLayout top;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        initTabLayout();


    }


    public void animation(View view){
        Animation animation = AnimationUtils.loadAnimation(ThirdActivity.this,R.anim.scale_anim);
        mTextView.startAnimation(animation);
    }

    private void initTabLayout() {
        mAdapter = new FragmentAdapter(getSupportFragmentManager());
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < pageList.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(pageList.get(i) + ""));
        }
        mNevigationViewPager.setScrollble(true);
        mNevigationViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mNevigationViewPager);
    }


    public class FragmentAdapter extends FragmentPagerAdapter {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return ViewPagerFragment.getInstanceFragment(pageList.get(i));
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return pageList.get(position)+"";
        }

        @Override
        public int getCount() {
            return pageList.size();
        }
    }

}
