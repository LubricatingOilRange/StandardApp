package com.net.work.modle.dagger.module;

import com.net.work.app.MyApplication;
import com.net.work.modle.helper.HttpHelper;
import com.net.work.modle.helper.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private MyApplication application;

    public AppModule(MyApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    MyApplication provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideServiceHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

}
