package com.app.standard.base.activity.mvp;

//  Created by ruibing.han on 2018/3/29.

import com.app.standard.base.activity.dagger2.BaseDaggerActivity;
import com.app.standard.base.mvp.BasePresenter;
import com.app.standard.base.mvp.BaseView;

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
