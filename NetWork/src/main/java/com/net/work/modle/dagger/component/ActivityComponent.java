package com.net.work.modle.dagger.component;

import android.app.Activity;

import com.net.work.modle.dagger.module.ActivityModule;
import com.net.work.modle.dagger.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

    Activity getActivity();
}
