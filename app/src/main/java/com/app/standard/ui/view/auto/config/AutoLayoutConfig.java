package com.app.standard.ui.view.auto.config;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.app.standard.app.MyApplication;
import com.app.standard.util.LogUtil;

public class AutoLayoutConfig {

    private static AutoLayoutConfig sIntance = new AutoLayoutConfig();

    private static final String KEY_DESIGN_WIDTH = "design_width";
    private static final String KEY_DESIGN_HEIGHT = "design_height";

    private int mScreenWidth;//当前屏幕
    private int mScreenHeight;

    private int mDesignWidth;//设计 也就是清单文件中的值
    private int mDesignHeight;

    private boolean useDeviceSize;


    private AutoLayoutConfig() {
    }

    public void checkParams() {
        if (mDesignHeight <= 0 || mDesignWidth <= 0) {
            throw new RuntimeException(
                    "you must set " + KEY_DESIGN_WIDTH + " and " + KEY_DESIGN_HEIGHT + "  in your manifest file.");
        }
    }

    public AutoLayoutConfig useDeviceSize() {
        useDeviceSize = true;
        return this;
    }


    public static AutoLayoutConfig getInstance() {
        return sIntance;
    }


    public int getScreenWidth() {
        return mScreenWidth;
    }

    public int getScreenHeight() {
        return mScreenHeight;
    }

    public int getDesignWidth() {
        return mDesignWidth;
    }

    public int getDesignHeight() {
        return mDesignHeight;
    }


    public void init(Context context) {
        getMetaData(context);

        mScreenWidth = MyApplication.SCREEN_WIDTH;
        mScreenHeight = MyApplication.SCREEN_HEIGHT;
        LogUtil.i("bbb", " screenWidth =" + mScreenWidth + " ,screenHeight = " + mScreenHeight);
    }

    private void getMetaData(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = packageManager.getApplicationInfo(context
                    .getPackageName(), PackageManager.GET_META_DATA);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                mDesignWidth = (int) applicationInfo.metaData.get(KEY_DESIGN_WIDTH);
                mDesignHeight = (int) applicationInfo.metaData.get(KEY_DESIGN_HEIGHT);
            }
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(
                    "you must set " + KEY_DESIGN_WIDTH + " and " + KEY_DESIGN_HEIGHT + "  in your manifest file.", e);
        }

        LogUtil.i(" designWidth =" + mDesignWidth + " , designHeight = " + mDesignHeight);
    }
}
