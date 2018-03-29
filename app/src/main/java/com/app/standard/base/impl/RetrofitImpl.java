package com.app.standard.base.impl;

//  Created by ruibing.han on 2018/3/29.

import android.support.annotation.NonNull;

import com.app.standard.modle.http.response.BaseResponse;

import java.util.Map;

import io.reactivex.Flowable;

public interface RetrofitImpl {

    Flowable<BaseResponse<String>> login(@NonNull Map<String, String> map);//登录
}
