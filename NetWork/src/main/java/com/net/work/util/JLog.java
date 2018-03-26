package com.net.work.util;

import android.util.Log;

//打印日志的工具类
public class JLog {

    private static final String TAG = JLog.class.getSimpleName();

    public static void d(String content) {
        d(TAG, content);
    }

    public static void d(String tag,String content) {
        Log.d(tag, content);
    }
}
