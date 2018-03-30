package com.app.standard.modle.dagger2.module;

//  Created by ruibing.han on 2018/3/29.

import com.app.standard.modle.dagger2.qualifier.AppUrl;
import com.app.standard.modle.dagger2.qualifier.SubUrl;
import com.app.standard.modle.http.api.AppService;
import com.app.standard.modle.http.api.SubService;
import com.app.standard.util.OkHttpUtil;
import com.app.standard.util.RetrofitUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Module
public class HttpModule {

    //---------------------------------------------OkHttp------------------------------------------
    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpClientBuilder() {
        return OkHttpUtil.getOkHttpClientBuilder();//提供OKHttpClientBuilder
    }

    //直接从容器中获取 OkHttpClient.Builder
    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        return OkHttpUtil.getOkHttpClient(builder);//提供OKHttpClient
    }

    //----------------------------------------------Retrofit----------------------------------
    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return RetrofitUtil.getRetrofitBuilder();//提供RetrofitBuilder
    }

    // -------------AppUrl 给当前的获取的Retrofit添加一个标记，提供给AppService
    @Singleton
    @AppUrl
    @Provides
    Retrofit provideAppRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return RetrofitUtil.getRetrofit(builder, client, AppService.APP_URL);
    }

    //根据标记获取容器中并添加了AppUrl标记的Retrofit，创建AppService
    @Singleton
    @Provides
    AppService provideAppService(@AppUrl Retrofit retrofit) {
        return retrofit.create(AppService.class);
    }

    //-------------------SubUrl 给当前的获取的Retrofit添加一个标记，提供给SubService
    @Singleton
    @SubUrl
    @Provides
    Retrofit provideSubRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return RetrofitUtil.getRetrofit(builder, client, SubService.SUB_URL);
    }

    //根据标记获取容器中并添加了SubUrl标记的Retrofit，创建SubService
    @Singleton
    @Provides
    SubService provideSubService(@SubUrl Retrofit retrofit) {
        return retrofit.create(SubService.class);
    }
}
