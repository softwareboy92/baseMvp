package com.lv.qm.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lv.monkey.view.BaseFragment;
import com.lv.qm.R;
import com.lv.qm.contract.BackGroundFragmentContract;
import com.lv.qm.present.BackGroundFragmentPresenter;
import com.lv.qm.service.MyIntentService;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：create by albert on 2018/11/24 4:38 PM
 * 邮箱：lvhzongdi@iclou.com
 */
public class BackGroundFragment extends BaseFragment<BackGroundFragmentPresenter> implements BackGroundFragmentContract.View{


    @BindView(R.id.back_tv)
    TextView mView;
    private AlertDialog.Builder normalDialog;

    @Override
    protected BackGroundFragmentPresenter initPresenter() {
        if (mPresenter==null)
            mPresenter = new BackGroundFragmentPresenter();
        return mPresenter;
    }

    @Override
    protected int getFragmentLayoutID() {
        return R.layout.fragment_background;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        normalDialog = new AlertDialog.Builder(getContext());

        normalDialog.setIcon(R.drawable.ic_emotion);
        normalDialog.setTitle("我是一个普通Dialog");
        normalDialog.setMessage("你要点击哪一个按钮呢?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
    }

    @Override
    protected void refershDatas(Context context, Intent intent) {
        String show = intent.getStringExtra("show");
        if (show.equals("show")){
            normalDialog.show();
        }
    }


    @OnClick(R.id.back_tv)
    public void backGround(){
        Intent intent = new Intent(getActivity(),MyIntentService.class);
        getActivity().startService(intent);
    }

}
