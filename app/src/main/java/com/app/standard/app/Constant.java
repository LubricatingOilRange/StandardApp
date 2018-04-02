package com.app.standard.app;

//  Created by ruibing.han on 2018/3/29.

import android.os.Environment;

import java.io.File;

public class Constant {
    public static final boolean DEBUG = true;//当前是否是在测试阶段

    //网络缓存
    public static final String CACHE_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator + "StandardApp";
}
