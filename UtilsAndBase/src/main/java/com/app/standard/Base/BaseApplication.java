package com.app.standard.Base;

import android.app.Application;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public abstract class BaseApplication extends Application {

    public static int SCREEN_WIDTH;//屏幕的宽
    public static int SCREEN_HEIGHT;//屏幕的高

    @Override
    public void onCreate() {
        super.onCreate();

        getScreenSize();

        onInitIntentService();//需要开启线程进行一些复杂的初始化（推送，友盟分享，数据...）
    }

    /**
     * 初始化屏幕的宽高信息
     */
    private void getScreenSize() {
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        if (windowManager != null) {
            DisplayMetrics metrics = new DisplayMetrics();
            Display display = windowManager.getDefaultDisplay();
            display.getMetrics(metrics);
            SCREEN_WIDTH = metrics.widthPixels;
            SCREEN_HEIGHT = metrics.heightPixels;
        }
    }

    protected abstract void onInitIntentService();
}
