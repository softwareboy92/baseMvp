package com.lv.qm.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lv.monkey.view.BaseActivity;
import com.lv.qm.R;
import com.lv.qm.contract.BackGroundContract;
import com.lv.qm.fragment.BackGroundFragment;
import com.lv.qm.fragment.BackGroundFragment2;
import com.lv.qm.present.BackGroundPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class BackGroundFragmentActivity extends BaseActivity<BackGroundPresenter> implements BackGroundContract.View {


    @BindView(R.id.ll_container)
    LinearLayout mLinearLayout;
    @BindView(R.id.btn01)
    TextView m1;
    @BindView(R.id.btn02)
    TextView m2;

    private BackGroundFragment fragment;
    private BackGroundFragment2 fragment2;


    @Override
    protected void initView(Bundle savedInstanceState) {
        fragment = new BackGroundFragment();
        fragment2 = new BackGroundFragment2();
        getSupportFragmentManager().beginTransaction().add(R.id.ll_container, fragment, "fragmetn")
                .add(R.id.ll_container, fragment2, "fragment2")
                .show(fragment)
                .hide(fragment2)
                .commit();
    }


    @OnClick(R.id.btn01)
    public void btn01() {
        getSupportFragmentManager().beginTransaction()
                .show(fragment)
                .hide(fragment2)
                .commit();
    }

    @OnClick(R.id.btn02)
    public void btn02() {
        getSupportFragmentManager().beginTransaction()
                .show(fragment2)
                .hide(fragment)
                .commit();
    }

    @Override
    protected BackGroundPresenter initPresenter() {
        return mPresenter;
    }

    @Override
    protected int getActivityLayoutID() {
        return R.layout.activity_back_ground_fragment;
    }

    @Override
    protected void refershDatas(Context context, Intent intent) {

    }
}
