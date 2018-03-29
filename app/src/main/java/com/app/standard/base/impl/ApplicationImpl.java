package com.app.standard.base.impl;

//  Created by ruibing.han on 2018/3/28.

public interface ApplicationImpl {

    void onCreateInit();//在application 进行不耗时的初始化

    void onInitActivityLifecycleCallbacks();//activity生命周期

    void onInitComponentStorageCallbacks();//监听内存使用情况

    void onInitIntentService();//需要开启线程进行一些复杂的初始化（推送，友盟分享，数据...）
}
