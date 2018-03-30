package com.app.standard.modle.helper;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.standard.R;
import com.app.standard.app.AppViewHolder;
import com.app.standard.app.MyApplication;
import com.app.standard.modle.window.dialog.CommonDialog;
import com.app.standard.modle.window.dialog.CommonDialogFragment;
import com.app.standard.ui.view.custom.CustomToast;

public class DialogHelper {
    //--------------------------------------DialogFragment的使用------------------------------------
    static final String dialog = "dialogFragment";

    //每次弹框 都会创建一次DialogFragment 不适合多次重复的弹框
    public static void showDialogFragment(final String[] arrayData, FragmentManager manager) {
        CommonDialogFragment.newInstance(new CommonDialogFragment.OnDialogCallBack() {
            @Override
            public Dialog getDialog(final Context context) {
                //Dialog参数可以从外部传进来，这需要个人喜好设置
                return new CommonDialog.Builder(context)
                        .setBackGroundLevel(1f)//背景透明（0-1）
                        //.setWidthAndHeight(ScreenUtil.getScreenWidth(context) * 2 / 3, ScreenUtil.getScreenHeight(context) / 3)//dialog的宽高
                        .setWidthAndHeight(0, 0)//dialog的宽高
//                            .setXAndY(-100, 300)//设置dialog坐标点(基于Gravity之后的偏移量，默认为中心点)
                        .setXAndY(0, 0)//设置dialog坐标点(基于Gravity之后的偏移量，默认为中心点)
                        .setOutsideTouchable(true)//设置dialog外部点击是否消失
                        // .setAnimationStyle(R.style.anim_dialog)//设置dialog显示消失动画
                        .setGravity(Gravity.CENTER)//设置显示在activity的左边
                        .setView(R.layout.layout_window, new CommonDialogFragment.OnHandleViewCallBack() {
                            @Override
                            public void onHandleView(AppViewHolder holder) {
                                holder.setText(R.id.tv_window, arrayData[0])
                                        .setText(R.id.bt_window, arrayData[1])
                                        .setOnClickListener(R.id.bt_window, new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(context, arrayData[2], Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        })//布局ID
                        .create(R.style.dialog);//主题创建
            }

            @Override
            public void onDismiss(Context context) {
                CustomToast.create(context).shortShow("关闭了Dialog");
            }
        })
                .show(manager, dialog);
    }

    //-----------------------------------------Dialog的使用-----------------------------------------
    //多次相同弹框，只跟页面内容
    public static CommonDialog showDialog(final Context context, final String[] arrayData, CommonDialog commonDialog) {
        if (commonDialog == null) {
            commonDialog = new CommonDialog.Builder(context)
                    .setBackGroundLevel(1f)//背景透明（0-1）
                    //.setWidthAndHeight(ScreenUtil.getScreenWidth(context) * 2 / 3, ScreenUtil.getScreenHeight(context) / 3)//dialog的宽高
                    .setWidthAndHeight(MyApplication.SCREEN_WIDTH * 2 / 3, MyApplication.SCREEN_HEIGHT / 3)//dialog的宽高
//                    .setWidthAndHeight(0, 0)//dialog的宽高
                    .setXAndY(-100, 300)//设置dialog坐标点(基于Gravity之后的偏移量，默认为中心点)
                    .setXAndY(0, 0)//设置dialog坐标点(基于Gravity之后的偏移量，默认为中心点)
                    .setOutsideTouchable(true)//设置dialog外部点击是否消失
                    //.setAnimationStyle(R.style.anim_dialog)//设置dialog显示消失动画
                    .setGravity(Gravity.CENTER)//设置显示在activity的左边
                    .setView(R.layout.layout_window, null)//布局ID
                    .create(R.style.dialog);//主题创建}

            commonDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    CustomToast.create(context).shortShow("关闭了Dialog");
                }
            });
        }

        TextView tv_window = commonDialog.findViewById(R.id.tv_window);
        TextView bt_window = commonDialog.findViewById(R.id.bt_window);

        tv_window.setText(arrayData[0]);
        bt_window.setText(arrayData[1]);

        commonDialog.show();

        return commonDialog;
    }
}