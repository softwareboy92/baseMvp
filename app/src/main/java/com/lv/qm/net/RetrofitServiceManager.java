package com.lv.qm.net;

import com.lv.qm.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：created by albert on 2018/9/6 10:55
 * 邮箱：lvzhongdi@icloud.com
 **/
public class RetrofitServiceManager {

    private static final int DEFAULT_TIME_OUT = 5;//设置超时时间
    private static final int DEFAULT_READ_TIME_OUT = 10;//设置读取的超时时间
    private Retrofit mRetrofit;
    private static RetrofitServiceManager manager;
    private static final String TAG = "RetrofitServiceManager";

    public RetrofitServiceManager() {

        //创建一个OkhttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//设置连接超时
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//设置读超时
        builder.writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);//设置写超时

        //创建一拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtils.e(message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);

        builder.addInterceptor(interceptor);


        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(State.URL)
                .build();
    }


    public static RetrofitServiceManager getManager() {
        if (manager != null) return manager;
        synchronized (TAG) {
            if (manager != null) return manager;
            manager = new RetrofitServiceManager();
        }
        return manager;
    }


    public  ApiService createService() {
        return mRetrofit.create(ApiService.class);
    }

}
