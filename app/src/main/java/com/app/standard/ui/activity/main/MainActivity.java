package com.app.standard.ui.activity.main;

import android.view.View;
import android.widget.LinearLayout;

import com.app.standard.R;
import com.app.standard.base.activity.BaseMvpActivity;
import com.app.standard.modle.dagger2.component.DaggerActivityComponent;
import com.app.standard.modle.helper.DialogHelper;
import com.app.standard.modle.helper.PermissionsHelper;
import com.app.standard.modle.helper.PopupWindowHelper;
import com.app.standard.modle.http.exception.AppException;
import com.app.standard.modle.rxjava.RxBus;
import com.app.standard.modle.window.dialog.CommonDialog;
import com.app.standard.modle.window.popup.CommonPopupWindow;
import com.app.standard.ui.view.custom.CustomToast;
import com.app.standard.util.LogUtil;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityInject() {
        getActivityComponent().inject(this);
    }

    @OnClick({R.id.tv_getData, R.id.tv_send})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_getData://RxJava 获取网络数据
                mPresenter.getData();
                break;
            case R.id.tv_send://RxBus 发送消息
                RxBus.getDefault().post("我是MainActivity");
                break;
        }
    }

    @Override
    protected void onInitPageAndData() {
        requestPermissions();
    }

    private void requestPermissions() {
        RxPermissions rxPermissions = getActivityComponent().getRxPermissions();
        rxPermissions
                .requestEach(PermissionsHelper.PERMISSION_GROUP_CAMERA)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            LogUtil.i("permission", permission.name + " is granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            LogUtil.i("permission", permission.name + " is denied. More info should be provided.");
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            LogUtil.i("permission", permission.name + " is denied.");
                        }
                    }
                });


    }

    //展示网络获取成功后的数据
    @Override
    public void showData() {
    }

    //展示网络获取失败的错误信息
    @Override
    public void showErrorMsg(AppException e) {
        CustomToast.create(this).longShow(e.getMsg());
    }

    @Override
    public void onBackPressed() {

        if (isTaskRoot()) {//判断是否是根
            moveTaskToBack(false);//将app 移动到后台  不关闭主页面，等再次打开app,实现暖启动
        } else {
            super.onBackPressed();
        }
    }
}
