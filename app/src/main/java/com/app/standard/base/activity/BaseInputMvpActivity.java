package com.app.standard.base.activity;

//  Created by ruibing.han on 2018/3/29.

import com.app.standard.app.MyApplication;
import com.app.standard.base.mvp.BasePresenter;
import com.app.standard.base.mvp.BaseView;
import com.app.standard.modle.dagger2.component.ActivityComponent;
import com.app.standard.modle.dagger2.component.DaggerActivityComponent;
import com.app.standard.modle.dagger2.module.ActivityModule;

import javax.inject.Inject;

//MVP 页面 -- 当页面有EditText时 实现点击EditText外面后先进行隐藏软键盘效果
public abstract class BaseInputMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    @Inject
    protected T mPresenter;

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(MyApplication.getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    @Override
    public void onCreateInit() {

        onActivityInject();//将当前的Activity注入到ActivityComponent容器中

        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        onInitPageAndData();//初始化页面和数据
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    protected abstract void onActivityInject();

    protected abstract void onInitPageAndData();
}
