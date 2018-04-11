package com.custom.view.util;

//  Created by ruibing.han on 2018/4/11.

import android.util.Log;

public class Logger {
    private static final String TAG = "Logger";

    public static void i(String content) {
        Log.i(TAG, content);
    }

    public static void i(String tag,String content) {
        Log.i(tag, content);
    }
}
