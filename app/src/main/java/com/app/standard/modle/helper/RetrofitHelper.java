package com.app.standard.modle.helper;

//  Created by ruibing.han on 2018/3/29.

import android.support.annotation.NonNull;

import com.app.standard.base.impl.RetrofitImpl;
import com.app.standard.modle.http.api.AppService;
import com.app.standard.modle.http.api.SubService;
import com.app.standard.modle.http.response.BaseResponse;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;

//网络请求的帮助类
public class RetrofitHelper implements RetrofitImpl {

    private AppService mAppService;

    private SubService mSubService;

    public RetrofitHelper(AppService appService, SubService subService) {
        this.mAppService = appService;
        this.mSubService = subService;
    }

    @Override
    public Flowable<BaseResponse<String>> login(@NonNull Map<String, String> map) {
        return mAppService.login(map);
    }
}
