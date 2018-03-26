package com.net.work.modle.helper;

import com.net.work.modle.http.api.AppService;
import com.net.work.modle.http.api.SubService;
import com.net.work.modle.http.response.BaseResponse;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class ServiceHelper implements HttpHelper {

    private AppService mAppService;

    private SubService mSubService;

    @Inject
    public ServiceHelper(AppService appService, SubService subService) {
        this.mAppService = appService;
        this.mSubService = subService;
    }

    @Override
    public Flowable<BaseResponse<String>> login(Map<String, String> parameterMap) {
        return mAppService.Login(parameterMap);
    }
}
