package com.net.work.app;

import android.app.Application;

import com.net.work.modle.dagger.component.AppComponent;
import com.net.work.modle.dagger.component.DaggerAppComponent;
import com.net.work.modle.dagger.module.AppModule;
import com.net.work.modle.dagger.module.HttpModule;

/**
 * Created by ruibing.han on 2018/3/26.
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            return DaggerAppComponent.builder()
                    .httpModule(new HttpModule())
                    .appModule(new AppModule(MyApplication.getInstance()))
                    .build();
        } else {
            return appComponent;
        }
    }

}
