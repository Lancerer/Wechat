package com.example.lancer.wechat.util;

import com.example.lancer.wechat.api.Robat_imp;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lancer on 2018/4/15.
 */

public class HttpUtil {
    public Robat_imp getRequest(String BaseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Robat_imp request = retrofit.create(Robat_imp.class);
        return request;
    }
}
