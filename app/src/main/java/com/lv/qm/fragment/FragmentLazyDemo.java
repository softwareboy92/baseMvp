package com.lv.qm.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lv.qm.R;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：created by albert on 2019/1/8 14:10
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class FragmentLazyDemo extends RxFragment {

    Unbinder mUnBinder;
    private boolean isFirstAddDatas;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("lazy", "setUserVisibleHint:" + isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isFirstAddDatas = hidden;
        Log.e("lazy", "onHiddenChanged:" + hidden);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.e("lazy", "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("lazy", "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("lazy", "onCreateView");
        View mView = inflater.inflate(R.layout.fragment_lazy, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("lazy", "onViewCreated");
        //绑定
        mUnBinder = ButterKnife.bind(this, view);
        if (isFirstAddDatas) {
            Log.e("lazy", "LoadDatas");
        }
        initView(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("lazy", "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("lazy", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("lazy", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("lazy", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("lazy", "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("lazy", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("lazy", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("lazy", "onDetach");
    }


    private void initView(View view, Bundle savedInstanceState) {

    }
}
