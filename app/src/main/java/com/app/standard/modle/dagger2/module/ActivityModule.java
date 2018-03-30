package com.app.standard.modle.dagger2.module;

//  Created by ruibing.han on 2018/3/29.

import android.app.Activity;

import com.app.standard.app.MyApplication;
import com.app.standard.modle.dagger2.scope.ActivityScope;
import com.tbruyelle.rxpermissions2.RxPermissions;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @ActivityScope
    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    //Activity动态权限申请
    @ActivityScope
    @Provides
    RxPermissions provideRxPermissions( Activity activity) {
        return new RxPermissions(activity);
    }
}
