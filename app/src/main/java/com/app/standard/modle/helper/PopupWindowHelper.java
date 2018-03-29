package com.app.standard.modle.helper;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.app.standard.R;
import com.app.standard.app.MyApplication;
import com.app.standard.modle.window.popup.CommonPopupWindow;
import com.app.standard.ui.view.custom.CustomToast;

public class PopupWindowHelper {
    private static final int GRAVITY = Gravity.CENTER;

    /*
     * 默认显示中间
     *
     * @param parent
     * @param context
     * @param commonPopupWindow
     */
    public static void showNormal(final Context context, View parent, String[] arrayData, CommonPopupWindow commonPopupWindow) {
        if (commonPopupWindow == null) {
            commonPopupWindow = new CommonPopupWindow.Builder(context)
                    .setBackGroundLevel(0.5f)//window展示的时候，背景透明度
                    .setOutsideTouchable(true)//设置点击窗口外部是否消失
                    .setWidthAndHeight(MyApplication.SCREEN_WIDTH * 2 / 3, MyApplication.SCREEN_HEIGHT / 3)//设置PopupWindow的宽高
//                  .setAnimationStyle(R.style.anim_dialog)//设置动画的样式ID
                    .setView(R.layout.layout_window, null)
                    .create(new CommonPopupWindow.OnPopupWindowDismiss() {
                        @Override
                        public void onDismiss() {
                            CustomToast.create(context).shortShow("popupWindow is dismissed");
                        }
                    });
        }
        View contentView = commonPopupWindow.getContentView();
        TextView tv_window = contentView.findViewById(R.id.tv_window);
        TextView bt_window = contentView.findViewById(R.id.bt_window);

        tv_window.setText(arrayData[0]);
        bt_window.setText(arrayData[1]);

        commonPopupWindow.showNormal(parent, GRAVITY);//设置显示在parent的左边
    }
}
