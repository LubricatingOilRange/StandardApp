package com.app.standard.util;


import com.app.standard.modle.http.encrypt.DecodeConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitUtil {

    private RetrofitUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    //获取RetrofitBuilder
    public static Retrofit.Builder getRetrofitBuilder() {
        return new Retrofit.Builder();
    }


    //获取Retrofit
    public static Retrofit getRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(DecodeConverterFactory.create(GSonUtil.defaultGSon()))//如果后台返回的数据是加密了
                .build();
    }
}
