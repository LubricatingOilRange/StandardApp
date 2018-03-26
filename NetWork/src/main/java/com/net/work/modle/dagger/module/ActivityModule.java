package com.net.work.modle.dagger.module;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.net.work.modle.dagger.scope.ActivityScope;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@ActivityScope
@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(@NonNull Activity activity) {
        this.activity = activity;
    }

    //提供到ActivityScope的区域中
    @ActivityScope
    @Provides
    Activity provideActivity() {
        return activity;
    }

}
