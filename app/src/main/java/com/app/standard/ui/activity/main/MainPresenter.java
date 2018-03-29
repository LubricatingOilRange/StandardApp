package com.app.standard.ui.activity.main;

//  Created by ruibing.han on 2018/3/29.

import com.app.standard.app.MyApplication;
import com.app.standard.modle.helper.ExceptionHelper;
import com.app.standard.modle.helper.RetrofitHelper;
import com.app.standard.modle.http.response.BaseResponse;
import com.app.standard.modle.rxjava.CommonSubscriber;
import com.app.standard.modle.rxjava.RxJavaPresent;
import com.app.standard.util.RxJavaUtil;

import java.util.HashMap;

import javax.inject.Inject;

public class MainPresenter extends RxJavaPresent<MainContract.View> implements MainContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    MainPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getData() {
        addSubscribe(
                mRetrofitHelper.login(new HashMap<String, String>())
                        .compose(RxJavaUtil.<BaseResponse<String>>rxSchedulerHelper())
                        .compose(RxJavaUtil.<String>handleBaseResponseResult())
                        .subscribeWith(new CommonSubscriber<String>(mView) {
                            @Override
                            public void onNext(String userCommand) {
                                mView.showData();
                            }
                        }));
    }
}
