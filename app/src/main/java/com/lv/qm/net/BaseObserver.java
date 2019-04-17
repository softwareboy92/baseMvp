package com.lv.qm.net;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zhuzidong
 * on 2018/1/30.
 * Email:591079255@qq.com
 */

public abstract class BaseObserver<T> implements Observer<T> {



    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        //ApiException apiException = ApiErrorHelper.handleCommonError(ThemisApplication.mApplication, e);
        Log.d("jc.lu", e.getMessage() + " || " +  e.getLocalizedMessage());
        onFail(e);
    }


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
//        Log.d("jc.lu",t.toString());
    }
    public abstract void onSuccess(T t);
    public abstract void onFail(Throwable e);
}
