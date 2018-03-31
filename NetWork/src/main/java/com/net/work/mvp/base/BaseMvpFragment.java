package com.net.work.mvp.base;

import android.support.v4.app.Fragment;

import com.net.work.app.MyApplication;
import com.net.work.base.BaseFragment;
import com.net.work.modle.dagger.component.DaggerFragmentComponent;
import com.net.work.modle.dagger.component.FragmentComponent;
import com.net.work.modle.dagger.module.FragmentModule;
import com.net.work.mvp.present.BasePresent;
import com.net.work.mvp.present.BaseView;

import javax.inject.Inject;

public abstract class BaseMvpFragment<T extends BasePresent> extends BaseFragment implements BaseView {

    @Inject
    T mPresent;

    //获取Dagger2中的FragmentComponent
    private FragmentComponent mFragmentComponent;
    protected FragmentComponent getDaggerFragmentComponent() {
        if (mFragmentComponent == null) {
            mFragmentComponent = DaggerFragmentComponent.builder()
                    .appComponent(MyApplication.getAppComponent())
                    .fragmentModule(new FragmentModule(this))
                    .build();
        }
        return mFragmentComponent;
    }

    @Override
    protected void onViewCreatedInit() {
        onFragmentInject();//将当前的Fragment注入到Dagger2容器中

        if (mPresent != null) {
            mPresent.attachView(this);
        }
    }

    @Override
    public void onDestroyView() {
        if (mPresent != null) {
            mPresent.detachView();
        }
        super.onDestroyView();
    }

    protected abstract void onFragmentInject();
}
