package com.app.standard.app;

import com.app.standard.base.BaseApplication;
import com.app.standard.modle.dagger2.component.AppComponent;
import com.app.standard.modle.dagger2.component.DaggerAppComponent;
import com.app.standard.modle.dagger2.module.AppModule;
import com.app.standard.modle.dagger2.module.HttpModule;


//  Created by ruibing.han on 2018/3/29.

public class MyApplication extends BaseApplication {
    private static MyApplication instance;

    private static AppComponent mAppComponent;

    public static AppComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent =  DaggerAppComponent.builder()
                    .appModule(new AppModule(getInstance()))
                    .httpModule(new HttpModule())
                    .build();
        }
        return mAppComponent;
    }

    @Override
    public void onCreateInit() {
        instance = this;
    }

    //获取全局的上下文
    public static MyApplication getInstance() {
        return instance;
    }

    //activity生命周期回调监听
    @Override
    public void onInitActivityLifecycleCallbacks() {

    }

    //内存使用情况监听
    @Override
    public void onInitComponentStorageCallbacks() {

    }

    //耗时初始化
    @Override
    public void onInitIntentService() {

    }
}
