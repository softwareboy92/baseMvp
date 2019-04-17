package com.lv.qm.net;

import com.lv.qm.model.SayHiModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 作者：created by albert on 2018/9/6 10:53
 * 邮箱：lvzhongdi@icloud.com
 **/
public interface ApiService {

    @GET("/monkey/sayhi")
    Observable<SayHiModel> sayHi();
}
