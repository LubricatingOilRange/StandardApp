package com.net.work.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

import com.net.work.app.MyApplication;


public class AppUtil {
    private AppUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 检查是否已挂载SD卡镜像（是否存在SD卡）
     */
    public static boolean isMountedSDCard() {
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            return true;
        } else {
            JLog.d("SDCARD is not MOUNTED !");
            return false;
        }
    }

    /**
     * 检查是否有可用网络
     */
    public static boolean isConnected() {
        boolean flag = false;
        ConnectivityManager mConnectivityManager = (ConnectivityManager) MyApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mConnectivityManager == null) {
            return flag;
        }
        NetworkInfo[] arrayOfNetworkInfo = mConnectivityManager.getAllNetworkInfo();
        if (arrayOfNetworkInfo != null) {
            for (int j = 0; j < arrayOfNetworkInfo.length; j++) {
                if (arrayOfNetworkInfo[j].getState() == NetworkInfo.State.CONNECTED) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
}
