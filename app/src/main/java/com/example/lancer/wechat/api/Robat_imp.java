package com.example.lancer.wechat.api;

import com.example.lancer.wechat.model.RobatBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Lancer on 2018/4/15.
 */
//http://www.tuling123.com/openapi/api?key=d1525b710de1405380f7d554006bac36&info=" + name
public interface Robat_imp {
    @GET
    Observable<RobatBean> getRobat(@Url String url);
}
