package com.app.standard.util;

//  Created by ruibing.han on 2018/3/27.

import android.util.Log;

public class LogUtil {

    private static final String TAG = LogUtil.class.getSimpleName();
    private static boolean DEBUG;

    public static void init(boolean debug) {
        DEBUG = debug;
    }

    //错误日志
    public static void e(String errorMessage) {
        if (!DEBUG) {
            Log.e(TAG, errorMessage);
        }
    }

    public static void e(String tag,String errorMessage) {
        if (!DEBUG) {
            Log.i(tag, errorMessage);
        }
    }

    //信息
    public static void i(String errorMessage) {
        if (!DEBUG) {
            Log.i(TAG, errorMessage);
        }
    }

    public static void i(String tag,String errorMessage) {
        if (!DEBUG) {
            Log.i(tag, errorMessage);
        }
    }
}
