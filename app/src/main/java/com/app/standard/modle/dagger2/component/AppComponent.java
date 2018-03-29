package com.app.standard.modle.dagger2.component;

//  Created by ruibing.han on 2018/3/29.

import com.app.standard.app.MyApplication;
import com.app.standard.modle.dagger2.module.AppModule;
import com.app.standard.modle.dagger2.module.HttpModule;
import com.app.standard.modle.helper.ExceptionHelper;
import com.app.standard.modle.helper.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    MyApplication getApplication();

    RetrofitHelper getRetrofitHelper();
}
