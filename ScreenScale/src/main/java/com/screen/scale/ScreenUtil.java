package com.screen.scale;

//  Created by ruibing.han on 2018/4/13.

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class ScreenUtil {

    private static ScreenUtil sIntance = new ScreenUtil();

    private static final String KEY_DESIGN_WIDTH = "design_width";
    private static final String KEY_DESIGN_HEIGHT = "design_height";

    private int mScreenWidth;//当前屏幕
    private int mScreenHeight;

    private int mDesignWidth;//设计 也就是清单文件中的值
    private int mDesignHeight;

    private float mScaleValue;

    public static ScreenUtil getInstance() {
        return sIntance;
    }

    /**
     * @param context applicationContext
     */
    public void init(Context context) {
        getMetaData(context);//获取设计的宽高
        getScreenSize(context);//获取屏幕的宽高
        intScaleValue();
    }

    /**
     * 获取清单文件中的 设计尺寸的大小
     *
     * @param context applicationContext
     */
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

        Log.i("aaa", " designWidth =" + mDesignWidth + " , designHeight = " + mDesignHeight);
    }

    /**
     * 获取屏幕的尺寸大小
     *
     * @param context applicationContext
     */
    private void getScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            DisplayMetrics dm = new DisplayMetrics();
            Display display = windowManager.getDefaultDisplay();
            display.getMetrics(dm);
            mScreenWidth = dm.widthPixels;
            mScreenHeight = dm.heightPixels;
            Log.i("aaa", "screen宽: " + mScreenWidth + ",高: " + mScreenHeight);
        }
    }

    /**
     * 根据设计尺寸大小和屏幕的尺寸大小 初始化缩放比例
     */
    private void intScaleValue() {
        checkParams();
        float widthScale = (mScreenWidth + 0.0f) / mDesignWidth;
        float heightScale = (mScreenHeight + 0.0f) / mDesignHeight;
        if (widthScale >= heightScale) mScaleValue = heightScale;
        else mScaleValue = widthScale;
    }

    private void checkParams() {
        if (mDesignHeight <= 0 || mDesignWidth <= 0) {
            throw new RuntimeException(
                    "you must set " + KEY_DESIGN_WIDTH + " and " + KEY_DESIGN_HEIGHT + "  in your manifest file.");
        }
    }

    /**
     * 对View进行缩放
     *
     * @param view 单个的View
     */
    public void scaleViewSize(View view) {
        if (view != null) {
            int paddingLeft = getScaleValue(view.getPaddingLeft());
            int paddingTop = getScaleValue(view.getPaddingTop());
            int paddingRight = getScaleValue(view.getPaddingRight());
            int paddingBottom = getScaleValue(view.getPaddingBottom());
            view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);

            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

            if (layoutParams != null) {

                if (layoutParams.width > 0) {
                    layoutParams.width = getScaleValue(layoutParams.width);
                }

                if (layoutParams.height > 0) {
                    layoutParams.height = getScaleValue(layoutParams.height);
                }

                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    int topMargin = getScaleValue(marginLayoutParams.topMargin);
                    int leftMargin = getScaleValue(marginLayoutParams.leftMargin);
                    int bottomMargin = getScaleValue(marginLayoutParams.bottomMargin);
                    int rightMargin = getScaleValue(marginLayoutParams.rightMargin);
                    marginLayoutParams.topMargin = topMargin;
                    marginLayoutParams.leftMargin = leftMargin;
                    marginLayoutParams.bottomMargin = bottomMargin;
                    marginLayoutParams.rightMargin = rightMargin;
                }
            }
            view.setLayoutParams(layoutParams);

        }

        // textView ,Button ,EditText
        if (view instanceof TextView) {
            //缩放字体大小
            TextView textView = (TextView) view;
            int textSize = (int) textView.getTextSize();
            textView.setTextSize(getScaleValue(textSize));

            Drawable[] compoundDrawables = textView.getCompoundDrawables();
            if (compoundDrawables.length > 0) {
                for (Drawable drawable : compoundDrawables) {
                    if (drawable != null) {
                        scaleDrawable(drawable);
                    }
                }
            }
        } else if (view instanceof ImageView) {
            // ImageView 缩放图片
            ImageView imageView = (ImageView) view;
            Drawable drawable = imageView.getDrawable();
            scaleDrawable(drawable);
            //imageView.setBackground();
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            if (childCount > 0) {
                for (int i = 0; i < childCount; i++) {
                    scaleViewSize(viewGroup.getChildAt(i));
                }
            }
        }
    }

    /**
     * 缩放Drawable
     *
     * @param drawable (Res)
     */
    private Drawable scaleDrawable(Drawable drawable) {
        int right = getScaleValue(drawable.getIntrinsicWidth());
        int bottom = getScaleValue(drawable.getIntrinsicHeight());
        drawable.setBounds(0, 0, right, bottom);
        return drawable;
    }

    private int getScaleValue(int value) {
        return (int) (mScaleValue * value);
    }

}
