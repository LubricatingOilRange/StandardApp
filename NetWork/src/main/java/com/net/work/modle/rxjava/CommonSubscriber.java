package com.net.work.modle.rxjava;

import io.reactivex.subscribers.ResourceSubscriber;


public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {

    protected CommonSubscriber() {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {

    }
}
