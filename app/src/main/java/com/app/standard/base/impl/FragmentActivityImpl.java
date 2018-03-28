package com.app.standard.base.impl;

//  Created by ruibing.han on 2018/3/28.

import android.support.annotation.NonNull;

import com.app.standard.base.BaseFragment;

public interface FragmentActivityImpl {

    int getContainerViewId();//获取添加Fragment的容器Id （FrameLayout）

    int getInLeftAnim();//左边进入动画

    int getInRightAnim();//右边进入动画

    int getOutLeftAnim();//左边离开动画

    int getOutRightAnim();//右边边离开动画


    @NonNull
    BaseFragment getNextFragment(String nextFragTag);//根据tag,获取下一个Fragment
}
