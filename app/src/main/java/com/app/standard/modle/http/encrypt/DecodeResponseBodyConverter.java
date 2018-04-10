package com.app.standard.modle.http.encrypt;

import android.support.annotation.NonNull;

import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;


//对返回的数据进行解密处理
public class DecodeResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;

    DecodeResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(@NonNull ResponseBody value) throws IOException {
        //获取ResponseBody 传递的Json字符串
        String resultJson = value.string();
        //将resultJson进行解密处理
        return adapter.fromJson(resultJson);
    }
}

