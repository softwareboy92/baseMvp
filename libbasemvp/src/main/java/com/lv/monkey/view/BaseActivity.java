package com.lv.monkey.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.lv.monkey.contract.BaseContract;
import com.lv.monkey.utils.ToastUtils;
import com.lv.monkey.utils.languageutils.MultiLanguageUtil;
import com.lv.monkey.utils.languageutils.OnChangeLanguageEvent;
import com.lv.monkey.widget.DialogLoadding;
import com.lv.monkey.widget.SlideBackLayout;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：create by albert on 2018/11/11 5:18 PM
 * 邮箱：lvhzongdi@iclou.com
 */
public abstract class BaseActivity<P extends BaseContract.BasePresenter>
        extends RxAppCompatActivity implements BaseContract.BaseView {

    private int mColor = Color.parseColor("#191b20");
    private static final String BROADCAST_PERMISSION_DISC = "com.cn.customview.permissions.MY_BROADCAST";
    private static final String BROADCAST_ACTION_DISC = "com.cn.customview.permissions.my_broadcast";

    protected Activity mContext;
    protected P mPresenter;
    private Unbinder mUnBinder;
    private DialogLoadding dialogLoadding;

    private BroadcastReceiver receiveBroadCast;
    private SlideBackLayout mSlideBackLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getActivityLayoutID());
        //设置侧滑
        //创建侧滑关闭 Activity 控件
        mSlideBackLayout = new SlideBackLayout(this);
        //绑定 Activity
        mSlideBackLayout.bindActivity(this);
        mSlideBackLayout.unbindActivity();
        //设置语言
        MultiLanguageUtil.init(this);
        MultiLanguageUtil.getInstance().setConfiguration();
        //强制竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setStatusBar();
        //初始化butterknife
        initBroadCaseRevierce();
        mUnBinder = ButterKnife.bind(this);
        mPresenter = initPresenter();
        mContext = this;
        attachView();
        initView(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != Unbinder.EMPTY) {
            mUnBinder.unbind();
        }
        if (receiveBroadCast != null)
            unregisterReceiver(receiveBroadCast);
        if (mSlideBackLayout != null)
            mSlideBackLayout.unbindActivity();
        detachView();
    }

    private void initBroadCaseRevierce() {
        // 注册广播接收
        receiveBroadCast = new ReceiveBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BROADCAST_ACTION_DISC); // 只有持有相同的action的接受者才能接收此广播
        registerReceiver(receiveBroadCast, filter, BROADCAST_PERMISSION_DISC, null);
    }


    protected int getColor() {
        return mColor;
    }


    public void setStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.setStatusBarColor(getColor());
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup viewGroup = findViewById(Window.ID_ANDROID_CONTENT);

            int statusBarHeight = getStatusBarHeight(this);
            View contentView = viewGroup.getChildAt(0);
            if (contentView != null) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) contentView.getLayoutParams();
                ViewCompat.setFitsSystemWindows(contentView, false);
                lp.topMargin = lp.topMargin + statusBarHeight;
                Log.e("topMargin", lp.topMargin + "");
                contentView.setLayoutParams(lp);
            }

            FrameLayout decoView = (FrameLayout) getWindow().getDecorView();

            View childStatusView = decoView.getChildAt(0);

            if (childStatusView != null && childStatusView.getLayoutParams().height != statusBarHeight) {
                View statusView = new View(this);
                FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
                statusView.setBackgroundColor(getColor());
                statusView.setLayoutParams(lp);
                decoView.addView(statusView, 0);
            } else {
                childStatusView.setBackgroundColor(getColor());
            }
        }
    }


    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 挂载view
     */
    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 卸载view
     */
    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    /**
     * 初始化View
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 在子View中初始化Presenter
     *
     * @return
     */
    protected abstract P initPresenter();

    /**
     * 设置Activity的布局ID
     *
     * @retur
     */
    protected abstract int getActivityLayoutID();

    @Override
    public void showLoading() {
        dialogLoadding = new DialogLoadding(this);
        dialogLoadding.showDialog();
    }

    @Override
    public void hideLoading() {
        if (dialogLoadding != null)
            dialogLoadding.closeDialog();
    }

    @Override
    public void showSuccess(String message) {
        ToastUtils.getInstanc(this).showToast(message);
    }

    @Override
    public void showFailed(String message) {
        ToastUtils.getInstanc(this).showToast(message);
    }

    @Override
    public void showNoNet() {
        ToastUtils.getInstanc(this).showToast("无网络");
    }

    @Override
    public void onRetry() {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(MultiLanguageUtil.attachBaseContext(newBase));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChangeLanguageEvent(OnChangeLanguageEvent event) {
        Log.d("onchange", "ChangeLanguage");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        MultiLanguageUtil.getInstance().setConfiguration();

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

    /**
     * 刷新操作都在这里面进行
     */
    protected abstract void refershDatas(Context context, Intent intent);
}