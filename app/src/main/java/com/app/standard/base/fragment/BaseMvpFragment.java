package com.app.standard.base.fragment;


//  Created by ruibing.han on 2018/3/29.

import android.app.Activity;

import com.app.standard.app.MyApplication;
import com.app.standard.base.mvp.BasePresenter;
import com.app.standard.base.mvp.BaseView;
import com.app.standard.modle.dagger2.component.DaggerFragmentComponent;
import com.app.standard.modle.dagger2.component.FragmentComponent;
import com.app.standard.modle.dagger2.module.FragmentModule;

import javax.inject.Inject;

public abstract class BaseMvpFragment<T extends BasePresenter,A extends Activity> extends BaseDaggerFragment<A> implements BaseView {

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
    protected void onInitPagAndData() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        onViewCreatedInitPageAndData();//初始化页面和数据
    }

    protected abstract void onViewCreatedInitPageAndData();//进行页面和数据初始化

}
