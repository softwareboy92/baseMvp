package com.lv.monkey.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lv.monkey.contract.BaseContract;
import com.lv.monkey.widget.DialogLoadding;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：created by albert on 2019/1/8 11:52
 * 邮箱：lvzhongdi@icloud.com
 **/
public abstract class BaseLazyFragment<P extends BaseContract.BasePresenter> extends RxFragment implements BaseContract.BaseView {

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    private static final String BROADCAST_PERMISSION_DISC = "com.cn.customview.permissions.MY_BROADCAST";
    private static final String BROADCAST_ACTION_DISC = "com.cn.customview.permissions.my_broadcast";
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private Unbinder mUnBinder;
    protected P mPresenter;
    private DialogLoadding mDialogLoadding;
    public final String TAG = getClass().getSimpleName();
    private BroadcastReceiver receiveBroadCast;

    private boolean isFirstVisible;
    private boolean isFragmentVisiable;
    private boolean isResuseView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        attachView();
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
        initVariable();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getFragmentLayoutID(), container, false);
        mActivity = getActivity();
        mContext = mActivity;
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = view;
            if (getUserVisibleHint()) {
                if (isFirstVisible) {
                    onFragmentFirstVisible();
                    isFirstVisible = false;
                }
                onFragmentFirstVisibleChange(true);
                isFragmentVisiable = true;
            }
        }
        mUnBinder = ButterKnife.bind(this, view);
        initView(view, savedInstanceState);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        detachView();
        initVariable();
    }

    protected abstract int getFragmentLayoutID();

    protected abstract void initView(View view, Bundle savedInstanceState);

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mView == null) {
            return;
        }
        if (isFirstVisible && isVisibleToUser) {
            onFragmentFirstVisible();
            isFirstVisible = false;
        }
        if (isVisibleToUser) {
            onFragmentFirstVisibleChange(true);
            isFragmentVisiable = true;
            return;
        }
        if (isFragmentVisiable) {
            isFragmentVisiable = false;
            onFragmentFirstVisibleChange(false);
        }
    }

    protected abstract void onFragmentFirstVisibleChange(boolean b);

    protected abstract void onFragmentFirstVisible();


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }


    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initBroadCaseRevierce();
    }

    private void initBroadCaseRevierce() {
        // 注册广播接收
        receiveBroadCast = new ReceiveBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BROADCAST_ACTION_DISC); // 只有持有相同的action的接受者才能接收此广播
        getActivity().registerReceiver(receiveBroadCast, filter, BROADCAST_PERMISSION_DISC, null);
    }

    protected abstract P initPresenter();

    protected abstract void initVariable();


    protected void reuseView(boolean isResuse) {
        isResuseView = isResuse;
    }

    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (receiveBroadCast != null)
            getActivity().unregisterReceiver(receiveBroadCast);
    }

    /**
     * 注册动态广播
     */
    public class ReceiveBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BROADCAST_ACTION_DISC)) {
                refershDatas(context, intent);
            }
        }

    }

    protected abstract void refershDatas(Context context, Intent intent);


    @Override
    public void showLoading() {
        mDialogLoadding = new DialogLoadding(getActivity());
        mDialogLoadding.showDialog();
    }


    @Override
    public void hideLoading() {
        if (mDialogLoadding != null) {
            mDialogLoadding.closeDialog();
        }
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }

    protected boolean isFragmentVisiable(){
        return isFragmentVisiable;
    }
}
