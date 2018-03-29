package com.app.standard.ui.activity.main;

import android.view.View;
import android.widget.TextView;

import com.app.standard.R;
import com.app.standard.base.activity.BaseMvpActivity;
import com.app.standard.modle.http.exception.AppException;
import com.app.standard.modle.rxjava.RxBus;
import com.app.standard.ui.view.custom.CustomToast;

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

    @OnClick({R.id.tv_getData,R.id.tv_send})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_getData://RxJava 获取网络数据
                mPresenter.getData();
                RxBus.getDefault().post(true);
                break;
            case R.id.tv_send://RxBus 发送消息
                RxBus.getDefault().post("我是MainActivity");

                break;
        }
    }

    @Override
    protected void onInitPageAndData() {
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
}
