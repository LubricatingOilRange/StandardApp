package com.app.standard.modle.rxjava;

//  Created by ruibing.han on 2018/3/29.

import com.app.standard.base.mvp.BasePresenter;
import com.app.standard.base.mvp.BaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public abstract class RxJavaPresent<T extends BaseView> implements BasePresenter<T> {

    protected T mView;

    private CompositeDisposable mCompositeDisposable;

    /**
     * 将RxJava事件流返回的Disposable添加到CompositeDisposable进行统一管理
     *
     * @param subscription （订阅事件返回的事件流）
     */
    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    //解除所有订阅
    private void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }


    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
        unSubscribe();
    }
}
