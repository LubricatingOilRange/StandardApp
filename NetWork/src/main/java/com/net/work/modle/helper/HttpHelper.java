package com.net.work.modle.helper;


import com.net.work.modle.http.response.BaseResponse;

import java.util.Map;

import io.reactivex.Flowable;

/*
 * Created by ruibing.han on 2017/10/20.
 */

public interface HttpHelper {

    /*
     * 登录
     *
     * @return
     */
    Flowable<BaseResponse<String>> login(Map<String, String> parameterMap);

}
