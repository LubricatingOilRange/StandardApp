package com.app.standard.modle.rxjava.file;

import com.app.standard.modle.http.api.FileService;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ANetWorkConfig {
    private static final int DEFAULT_TIMEOUT = 20;
    public static final int TYPE_UPLOAD = 1;//文件上传
    public static final int TYPE_DOWNLOAD = 2;//文件下载

    public static Retrofit getRetrofit(int type ,UploadListener uploadListener,DownLoadListener downListener) {
        if (type != TYPE_UPLOAD && type != TYPE_DOWNLOAD) {
            throw new IllegalStateException("type must be TYPE_UPLOAD or TYPE_DOWNLOAD");
        }

        Interceptor interceptor = null;
        switch (type) {
            case TYPE_UPLOAD:
                if (uploadListener == null) {
                    throw new IllegalStateException("uploadListener is Null");
                }
                interceptor = new UpLoadProgressInterceptor(uploadListener);
                break;
            case TYPE_DOWNLOAD:
                if (downListener == null) {
                    throw new IllegalStateException("downListener is Null");
                }
                interceptor = new DownLoadProgressInterceptor(downListener);
                break;
        }
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)//设置失败后重新请求一次
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(FileService.FILE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
