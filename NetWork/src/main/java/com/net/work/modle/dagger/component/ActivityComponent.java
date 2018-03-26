package com.net.work.modle.dagger.component;

import android.app.Activity;

import com.net.work.MainActivity;
import com.net.work.modle.dagger.module.ActivityModule;
import com.net.work.modle.dagger.scope.ActivityScope;
import com.net.work.modle.helper.HttpHelper;
import com.net.work.modle.helper.ServiceHelper;
import com.tbruyelle.rxpermissions2.RxPermissions;

import dagger.Component;

@ActivityScope
@Component(modules = ActivityModule.class,dependencies = AppComponent.class)
public interface ActivityComponent {

    Activity getActivity();
}
