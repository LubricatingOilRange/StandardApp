package com.app.standard.modle.rxjava.file;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/*
 * Created by ruibing.han on 2018/2/23.
 */

public class UpLoadProgressInterceptor implements Interceptor {
    private UploadListener mUploadListener;

    UpLoadProgressInterceptor(UploadListener uploadListener) {
        mUploadListener = uploadListener;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        if (null == request.body()) {
            return chain.proceed(request);
        }

        Request build = request.newBuilder()
                .method(request.method(), new UpProgressRequestBody(request.body(), mUploadListener))
                .build();
        return chain.proceed(build);
    }
}
