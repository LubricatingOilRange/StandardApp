package com.net.work.modle.dagger.component;

import android.app.Activity;

import com.net.work.modle.dagger.module.FragmentModule;
import com.net.work.modle.dagger.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {

    Activity getActivity();
}
