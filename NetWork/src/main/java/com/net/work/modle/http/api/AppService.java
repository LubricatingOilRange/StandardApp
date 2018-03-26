package com.net.work.modle.http.api;


import com.net.work.modle.http.response.BaseResponse;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface AppService {

    String APP_URL = "http://180.168.134.212:18601/";

    //登陆页面
    @POST("shopdog/auth/login")
    Flowable<BaseResponse<String>> Login(@QueryMap Map<String, String> map);
}
