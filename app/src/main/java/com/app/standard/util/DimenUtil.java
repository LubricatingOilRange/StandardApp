package com.app.standard.util;

//  Created by ruibing.han on 2018/3/27.

import android.content.Context;
import android.util.TypedValue;

public class DimenUtil {
    //屏蔽new 创建和反射创建
    private DimenUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static int getComplexUnit(int data) {
        return TypedValue.COMPLEX_UNIT_MASK & (data);
    }

    //判断是否是px 值
    public static boolean isPxVal(TypedValue val) {
        return val != null && val.type == TypedValue.TYPE_DIMENSION &&
                getComplexUnit(val.data) == TypedValue.COMPLEX_UNIT_PX;
    }

    /*
     * dp转px
     * @param context
     * @param dipValue
     */
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /*
     * px转dp
     * @param context
     * @param pxValue
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
