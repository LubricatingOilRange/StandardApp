package com.app.standard.modle.rxjava.file;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class DownLoadProgressInterceptor implements Interceptor {
    private DownLoadListener mDownLoadListener;

    DownLoadProgressInterceptor(DownLoadListener downLoadListener) {
        this.mDownLoadListener = downLoadListener;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder()
                .body(new DownProgressResponseBody(response.body(), mDownLoadListener))
                .build();
    }
}
