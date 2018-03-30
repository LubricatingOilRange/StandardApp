package com.app.standard.base.activity;

//  Created by ruibing.han on 2018/3/29.

import com.app.standard.app.MyApplication;
import com.app.standard.base.mvp.BasePresenter;
import com.app.standard.base.mvp.BaseView;
import com.app.standard.modle.dagger2.component.ActivityComponent;
import com.app.standard.modle.dagger2.component.DaggerActivityComponent;
import com.app.standard.modle.dagger2.module.ActivityModule;

import javax.inject.Inject;

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    @Inject
   protected T mPresenter;

    private ActivityComponent mActivityComponent;
    protected ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent =  DaggerActivityComponent.builder()
                    .appComponent(MyApplication.getAppComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return mActivityComponent;
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