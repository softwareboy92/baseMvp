package com.lv.qm.service;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;

import com.lv.qm.App;
import com.lv.qm.model.SayHiModel;
import com.lv.qm.net.BaseObserver;
import com.lv.qm.net.RetrofitServiceManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * 作者：create by albert on 2018/11/24 4:54 PM
 * 邮箱：lvhzongdi@iclou.com
 */
public class MyIntentService extends JobIntentService {

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        RetrofitServiceManager.getManager().createService().sayHi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<SayHiModel>() {
                    @Override
                    public void onSuccess(SayHiModel sayHiModel) {
                        if (sayHiModel.getCode() == 200) {
                            Intent intent = new Intent();  //Itent就是我们要发送的内容
                            intent.putExtra("show","show");
                            intent.setAction(App.BROADCAST_ACTION_DISC);   //设置你这个广播的action，只有和这个action一样的接受者才能接受者才能接收广播
                            sendBroadcast(intent, App.BROADCAST_PERMISSION_DISC);   //发送广播
                        }
                    }

                    @Override
                    public void onFail(Throwable e) {
                        Intent intent = new Intent();
                        intent.putExtra("show","showerror");//Itent就是我们要发送的内容
                        intent.setAction(App.BROADCAST_ACTION_DISC);   //设置你这个广播的action，只有和这个action一样的接受者才能接受者才能接收广播
                        sendBroadcast(intent, App.BROADCAST_PERMISSION_DISC);   //发送广播
                    }
                });


    }
}
