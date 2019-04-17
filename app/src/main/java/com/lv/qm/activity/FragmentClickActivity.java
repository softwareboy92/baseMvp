package com.lv.qm.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lv.monkey.view.BaseActivity;
import com.lv.qm.R;
import com.lv.qm.contract.FragmentClickContract;
import com.lv.qm.fragment.BackGroundFragment;
import com.lv.qm.fragment.BackGroundFragment2;
import com.lv.qm.fragment.FragmentLazyDemo;
import com.lv.qm.present.FragmentClickPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：create by albert on 2018/12/31 8:46 PM
 * 邮箱：lvhzongdi@iclou.com
 */
public class FragmentClickActivity extends BaseActivity<FragmentClickPresenter> implements FragmentClickContract.View {


    @BindView(R.id.activity_title)
    LinearLayout mTitle;


    @BindView(R.id.tab_01)
    TextView mTab01;
    @BindView(R.id.tab_02)
    TextView mTab02;

    private BackGroundFragment mFragment01;
    private BackGroundFragment2 mFragment02;
    private FragmentLazyDemo mFragmentLazyDemo;

    @Override
    protected void initView(Bundle savedInstanceState) {


        mFragment01 = new BackGroundFragment();
        mFragment02 = new BackGroundFragment2();
        mFragmentLazyDemo = new FragmentLazyDemo();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_title, mFragment01, "mFragment01")
                .add(R.id.activity_title, mFragmentLazyDemo, "mFragmentLazyDemo")
                .show(mFragment01)
                .hide(mFragmentLazyDemo)
                .commit();

    }

    @Override
    protected FragmentClickPresenter initPresenter() {
        if (mPresenter == null)
            mPresenter = new FragmentClickPresenter();
        return mPresenter;
    }

    @Override
    protected int getActivityLayoutID() {
        return R.layout.activity_fragment_click;
    }

    @Override
    protected void refershDatas(Context context, Intent intent) {

    }

    @OnClick(R.id.tab_01)
    public void click01() {
        mTab01.setTextColor(Color.RED);
        mTab02.setTextColor(Color.BLACK);
        hideAllFragment(getSupportFragmentManager());
        addFragment(getSupportFragmentManager(),mFragment01,"mFragment01");
        showFragment(getSupportFragmentManager(),mFragment01);
    }

    @OnClick(R.id.tab_02)
    public void click02() {
        mTab01.setTextColor(Color.BLACK);
        mTab02.setTextColor(Color.RED);
        hideAllFragment(getSupportFragmentManager());
        addFragment(getSupportFragmentManager(),mFragmentLazyDemo,"mFragmentLazyDemo");
        showFragment(getSupportFragmentManager(),mFragmentLazyDemo);
    }

    private void showFragment(FragmentManager fm, Fragment fragment) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.show( fragment );
        ft.commitAllowingStateLoss();
    }



    private void addFragment(FragmentManager fm, Fragment fragment, String tag) {
        if (!fragment.isAdded()&&null == fm.findFragmentByTag( tag )) {
            FragmentTransaction ft = fm.beginTransaction();
            fm.executePendingTransactions();
            ft.add( R.id.activity_title, fragment, tag );
            ft.commitAllowingStateLoss();
        }

    }


    /**
     * 隐藏所有的fragment
     * @param fm
     */
    private void hideAllFragment(FragmentManager fm) {
        FragmentTransaction ft = fm.beginTransaction();
        if (!mFragment01.isHidden())
            ft.hide(mFragment01);

        if (!mFragment02.isHidden())
            ft.hide(mFragment02);
        ft.commitAllowingStateLoss();
    }


}
