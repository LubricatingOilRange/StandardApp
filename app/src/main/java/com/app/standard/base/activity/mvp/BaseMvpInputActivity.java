package com.app.standard.base.activity.mvp;

//  Created by ruibing.han on 2018/3/29.

import com.app.standard.base.activity.dagger2.BaseDaggerInputActivity;
import com.app.standard.base.mvp.BasePresenter;
import com.app.standard.base.mvp.BaseView;

import javax.inject.Inject;

//MVP 页面 -- 当页面有EditText时 实现点击EditText外面后先进行隐藏软键盘效果
public abstract class BaseMvpInputActivity<T extends BasePresenter> extends BaseDaggerInputActivity implements BaseView {

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
