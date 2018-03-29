package com.app.standard.modle.rxjava;

import com.app.standard.app.Constant;
import com.app.standard.base.mvp.BaseView;
import com.app.standard.modle.http.exception.AppException;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by codeest on 2017/2/23.
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private BaseView mView;
    private boolean isShowErrorState;

    public CommonSubscriber(BaseView view) {
        this(view, true);
    }

    public CommonSubscriber(BaseView view, boolean isShowErrorState) {
        this.mView = view;
        this.isShowErrorState = isShowErrorState;
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        if (mView == null) {
            return;
        }
        if (isShowErrorState) {
            if (e instanceof AppException) {
                mView.showErrorMsg((AppException) e);
            } else {
                mView.showErrorMsg(new AppException(e.getMessage(), "-1"));//设置默认Code
            }
        }
    }

}
