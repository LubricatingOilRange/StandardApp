package com.app.standard.base.fragment;


//  Created by ruibing.han on 2018/3/29.

import com.app.standard.app.MyApplication;
import com.app.standard.base.mvp.BasePresenter;
import com.app.standard.base.mvp.BaseView;
import com.app.standard.modle.dagger2.component.DaggerFragmentComponent;
import com.app.standard.modle.dagger2.component.FragmentComponent;
import com.app.standard.modle.dagger2.module.FragmentModule;

import javax.inject.Inject;

public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {

    @Inject
    T mPresenter;

    private FragmentComponent mFragmentComponent;

    protected FragmentComponent getFragmentComponent() {
        if (mFragmentComponent == null) {
            mFragmentComponent = DaggerFragmentComponent.builder()
                    .fragmentModule(new FragmentModule(this))
                    .appComponent(MyApplication.getAppComponent())
                    .build();
        }
        return mFragmentComponent;
    }

    @Override
    public void onViewCreatedInit() {
        onFragmentInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        onInitPagAndData();
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    protected abstract void onFragmentInject();//将当前fragment注入到 FragmentComponent容器中 产生依赖

    protected abstract void onInitPagAndData();//初始化页面和数据
}
