package com.net.work.mvp.present;

/**
 * Created by Ruibing.han on 2018/3/28.
 */

public interface BasePresent<T extends BaseView>{

    void attachView(T view);//将View传递到Present

    void detachView();
}
