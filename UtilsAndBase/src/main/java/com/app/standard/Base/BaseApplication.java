package com.app.standard.Base;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public abstract class BaseApplication extends Application {

    private static BaseApplication instance;

    public static int SCREEN_WIDTH = -1;//屏幕的宽
    public static int SCREEN_HEIGHT = -1;//屏幕的高
    public static float DIMEN_RATE = -1.0F;// 常见取值 1.0 ， 1.5
    public static int DIMEN_DPI = -1; //常见取值 120，160，240

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        getScreenSize();

        onCreateInit();

        onInitIntentService();//需要开启线程进行一些复杂的初始化（推送，友盟分享，数据...）
    }
    //获取全局的上下文
    public static BaseApplication getInstance() {
        return instance;
    }

    /**
     * 初始化屏幕的宽高信息
     */
    private void getScreenSize() {
        WindowManager windowManager = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if(SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    protected abstract void onCreateInit();// 在onCreate方法中进行其他的初始化操作，如数据库，更改时区

    protected abstract void onInitIntentService();//通过IntentService初始化第三方（友盟分享，极光推送，第三方支付等）
}
