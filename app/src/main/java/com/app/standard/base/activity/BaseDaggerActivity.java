package com.app.standard.base.activity;

//  Created by ruibing.han on 2018/3/30.

import com.app.standard.app.MyApplication;
import com.app.standard.modle.dagger2.component.ActivityComponent;
import com.app.standard.modle.dagger2.component.DaggerActivityComponent;
import com.app.standard.modle.dagger2.module.ActivityModule;

public abstract class BaseDaggerActivity extends BaseActivity {

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

        onInitPageAndData();//初始化页面和数据
    }

    protected abstract void onActivityInject();

    protected abstract void onInitPageAndData();
}
