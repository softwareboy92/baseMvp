package com.lv.qm.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.lv.monkey.view.BaseDialogFragment;
import com.lv.qm.R;
import com.lv.qm.contract.BottomDialogContract;
import com.lv.qm.present.BottomDialogPresenter;

/**
 * 作者：create by albert on 2018/11/11 5:50 PM
 * 邮箱：lvhzongdi@iclou.com
 */
public class BottomDialogFragment extends BaseDialogFragment<BottomDialogPresenter> implements BottomDialogContract.View {

    private BottomDialogPresenter mPresenter;

    @Override
    protected double getWightPercent() {
        return 1;
    }

    @Override
    protected double getHeightPercent() {
        return 0.5;
    }

    @Override
    protected int getGravity() {
        return Gravity.BOTTOM;
    }

    @Override
    protected int getThemeStyle() {
        return R.style.BottomDialog;
    }

    @Override
    protected BottomDialogPresenter initPresenter() {
        if (mPresenter == null)
            mPresenter = new BottomDialogPresenter();
        return mPresenter;
    }

    @Override
    protected int getFragmentLayoutID() {
        return R.layout.dialog_bottom_fragment01;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void refershDatas(Context context, Intent intent) {

    }
}
