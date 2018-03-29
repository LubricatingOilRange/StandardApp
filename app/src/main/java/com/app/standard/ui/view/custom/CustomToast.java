package com.app.standard.ui.view.custom;

//  Created by ruibing.han on 2018/3/27.

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.standard.R;

public class CustomToast extends Toast {

    private Context mContext;

    private CustomToast(Context context) {
        super(context);
        mContext = context;
        initToast();
    }

    //创建Toast
    public static CustomToast create(Context context) {
        synchronized (CustomToast.class) {
            return new CustomToast(context);
        }
    }

    private TextView tv_toast_msg;

    //初始化Toast
    private void initToast() {
        View contentView = View.inflate(mContext, R.layout.layout_toast, null);
        tv_toast_msg = contentView.findViewById(R.id.tv_toast_msg);
        setView(contentView);
        setGravity(Gravity.CENTER, 0, 0);
    }

    // -----------------展示短Toast-------
    public void shortShow(@StringRes int msgRes) {
        tv_toast_msg.setText(msgRes);
        setDuration(LENGTH_SHORT);
        show();
    }

    public void shortShow(String msg) {
        tv_toast_msg.setText(msg);
        setDuration(LENGTH_SHORT);
        show();
    }

    // ------------------展示长Toast-----------

    public void longShow(@StringRes int msgRes) {
        tv_toast_msg.setText(msgRes);
        setDuration(LENGTH_LONG);
        show();
    }

    public void longShow(String msg) {
        tv_toast_msg.setText(msg);
        setDuration(LENGTH_LONG);
        show();
    }
}
