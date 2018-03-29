package com.app.standard.modle.dagger2.module;

//  Created by ruibing.han on 2018/3/29.

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.app.standard.modle.dagger2.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private Activity mActivity;

    public FragmentModule(Fragment fragment) {
        this.mActivity = fragment.getActivity();
    }

    @FragmentScope
    @Provides
    Activity provideActivity() {
        return mActivity;
    }
}
