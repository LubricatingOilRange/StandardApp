package com.app.standard.base.impl;

//  Created by ruibing.han on 2018/3/28.

import android.support.annotation.NonNull;

import com.app.standard.base.fragment.BaseFragment;

public interface FragmentActivityImpl {

    int getContainerViewId();//获取添加Fragment的容器Id （FrameLayout）

    @NonNull
    BaseFragment getNextFragment(String nextFragTag);//根据tag,获取下一个Fragment
}
