package com.net.work.modle.rxjava;

import com.net.work.mvp.present.BasePresent;
import com.net.work.mvp.present.BaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class RxPresent<T extends BaseView> implements BasePresent<T> {

    private T mView;
    private CompositeDisposable mCompositeDisposable;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        //解除订阅
        unSubscribe();
    }

    /**
     * 将RxJava事件流返回的Disposable添加到CompositeDisposable进行统一管理
     *
     * @param subscription  RxJava调用一次接口的返回的一个事件流
     */
    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    /**
     * 当前页面退出时  切断所有的事件流，不再接受事件上流的数据
     */
    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}
