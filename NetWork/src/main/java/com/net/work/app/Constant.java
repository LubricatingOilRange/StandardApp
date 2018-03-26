package com.net.work.app;

import android.os.Environment;

import java.io.File;

/**
 * Created by ruibing.han on 2018/3/26.
 */

public class Constant {
    public static final boolean DEBUG = true;//测试包 --- 正式包
    public static final String CACHE_PATH = Environment.getExternalStorageDirectory().getPath()+ File.separator + "NetWork";
}
