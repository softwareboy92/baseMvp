package com.lv.qm.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lv.monkey.view.BaseFragment;
import com.lv.qm.R;
import com.lv.qm.contract.BackGroundFragmentContract;
import com.lv.qm.present.BackGroundFragmentPresenter;

import butterknife.OnClick;

/**
 * 作者：create by albert on 2018/11/24 4:38 PM
 * 邮箱：lvhzongdi@iclou.com
 */
public class BackGroundFragment2 extends BaseFragment<BackGroundFragmentPresenter> implements BackGroundFragmentContract.View{



    @Override
    protected BackGroundFragmentPresenter initPresenter() {
        if (mPresenter==null)
            mPresenter = new BackGroundFragmentPresenter();
        return mPresenter;
    }

    @Override
    protected int getFragmentLayoutID() {
        return R.layout.fragment_background2;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void refershDatas(Context context, Intent intent) {
    }


    @OnClick(R.id.back_tv)
    public void backGround(){

    }

}
