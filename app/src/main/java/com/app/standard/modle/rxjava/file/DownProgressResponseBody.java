package com.app.standard.modle.rxjava.file;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/*
 * 下载文件
 * Created by lxf on 2017/3/3.
 */
public class DownProgressResponseBody extends ResponseBody {

    private ResponseBody responseBody;
    private DownLoadListener mDownLoadListener;

    private BufferedSource bufferedSource;

     DownProgressResponseBody(ResponseBody responseBody, DownLoadListener downLoadListener) {
        this.responseBody = responseBody;
        this.mDownLoadListener = downLoadListener;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long bytesReadTemp = 0;

            @Override
            public long read(@NonNull Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                bytesReadTemp += bytesRead == -1 ? 0 : bytesRead;
                //实时发送当前已读取的字节和总字节
                mDownLoadListener.downLoading(bytesReadTemp, contentLength());
                return bytesRead;
            }
        };
    }
}