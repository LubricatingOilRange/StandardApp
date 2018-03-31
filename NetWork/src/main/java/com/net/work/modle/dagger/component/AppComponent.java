package com.net.work.modle.dagger.component;

import com.net.work.app.MyApplication;
import com.net.work.modle.dagger.module.AppModule;
import com.net.work.modle.dagger.module.HttpModule;
import com.net.work.modle.helper.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    MyApplication getContent();

    RetrofitHelper getRetrofitHelper();
}
