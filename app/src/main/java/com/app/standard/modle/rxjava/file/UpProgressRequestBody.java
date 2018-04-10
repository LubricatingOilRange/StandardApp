package com.app.standard.modle.rxjava.file;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/*
 * Created by ruibing.han on 2018/2/23.
 */

public class UpProgressRequestBody extends RequestBody {
    private RequestBody mRequestBody;
    private UploadListener mUploadListener;

    UpProgressRequestBody(RequestBody requestBody, UploadListener uploadListener) {
        mRequestBody = requestBody;
        mUploadListener = uploadListener;
    }

    @Override
    public MediaType contentType() {
        return mRequestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        try {
            return mRequestBody.contentLength();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void writeTo(@NonNull BufferedSink sink) throws IOException {
        BufferedSink bufferedSink;

        CountingSink mCountingSink = new CountingSink(sink);
        bufferedSink = Okio.buffer(mCountingSink);

        mRequestBody.writeTo(bufferedSink);
        bufferedSink.flush();
    }

    class CountingSink extends ForwardingSink {

        private long bytesWritten = 0;

         CountingSink(Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(@NonNull Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);
            bytesWritten += byteCount;
            if (mUploadListener != null) {
                mUploadListener.uploading(bytesWritten, contentLength());
            }
        }
    }
}
