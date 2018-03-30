package com.app.standard.modle.dagger2.component;

//  Created by ruibing.han on 2018/3/29.

import android.app.Activity;

import com.app.standard.modle.dagger2.module.FragmentModule;
import com.app.standard.modle.dagger2.scope.FragmentScope;
import com.tbruyelle.rxpermissions2.RxPermissions;

import dagger.Component;

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {

    Activity getActivity();

    RxPermissions getRxPermissions();//动态权限
}
