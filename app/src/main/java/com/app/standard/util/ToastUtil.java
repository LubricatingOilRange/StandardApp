package com.app.standard.util;

import android.content.Context;
import android.widget.Toast;

import com.app.standard.app.Constant;

//一次性Toast
public class ToastUtil {

    /*cannot be instantiated*/
    private ToastUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /*
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        if (Constant.DEBUG)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /*
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message) {
        if (Constant.DEBUG)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /*
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        if (Constant.DEBUG)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /*
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message) {
        if (Constant.DEBUG)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

}
