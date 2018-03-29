package com.app.standard.base.impl;

//  Created by ruibing.han on 2018/3/28.

public interface FragmentImpl {

    int getLayoutId();//获取布局Id

    void onViewCreatedInit();//在onViewCreated方法中可进行布局的初始化操作

    void onStartInit();//在onStart方法中可进行网络请求操作
}
