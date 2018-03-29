package com.app.standard.modle.dagger2.module;

//  Created by ruibing.han on 2018/3/29.

import android.app.Activity;

import com.app.standard.modle.dagger2.scope.ActivityScope;

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
}
