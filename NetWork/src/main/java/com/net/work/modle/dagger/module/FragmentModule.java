package com.net.work.modle.dagger.module;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.net.work.modle.dagger.scope.FragmentScope;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private Activity activity;

    public FragmentModule(@NonNull Fragment fragment) {
        this.activity = fragment.getActivity();
    }

    //提供到FragmentScope的区域中
    @FragmentScope
    @Provides
    Activity provideActivity() {
        return activity;
    }

}
