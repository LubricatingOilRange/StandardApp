package com.app.standard.ui.activity.main;

import android.view.View;

import com.app.standard.R;
import com.app.standard.base.activity.mvp.BaseMvpActivity;
import com.app.standard.modle.http.exception.AppException;
import com.app.standard.ui.view.custom.CustomToast;

import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {
    private Disposable mPermissionDisposable;

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
//                mPresenter.getData();
                break;
            case R.id.tv_send://RxBus 发送消息
                break;
        }
    }

    @Override
    protected void onCreateInitPageAndData() {

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
