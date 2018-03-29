package com.app.standard.util;

//  Created by ruibing.han on 2018/3/27.

import android.util.Log;

import com.app.standard.BuildConfig;

public class LogUtil {

    private static final String TAG = LogUtil.class.getSimpleName();

    //错误日志
    public static void e(String errorMessage) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, errorMessage);
        }
    }

    public static void e(String tag,String errorMessage) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, errorMessage);
        }
    }

    //信息
    public static void i(String errorMessage) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, errorMessage);
        }
    }

    public static void i(String tag,String errorMessage) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, errorMessage);
        }
    }
}
