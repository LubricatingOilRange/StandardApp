package com.app.standard.base.activity;

//  Created by ruibing.han on 2018/3/29.

import com.app.standard.app.MyApplication;
import com.app.standard.base.mvp.BasePresenter;
import com.app.standard.base.mvp.BaseView;
import com.app.standard.modle.dagger2.component.ActivityComponent;
import com.app.standard.modle.dagger2.component.DaggerActivityComponent;
import com.app.standard.modle.dagger2.module.ActivityModule;

import javax.inject.Inject;

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseDaggerActivity implements BaseView {

    @Inject
   protected T mPresenter;

    @Override
    protected void onInitPageAndData() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        onCreateInitPageAndData();//页面进行初始化操作
    }

    protected abstract void onCreateInitPageAndData();//初始化页面和数据

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }
}
