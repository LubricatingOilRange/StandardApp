package com.app.standard.base.mvp;

//  Created by ruibing.han on 2018/3/29.

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
