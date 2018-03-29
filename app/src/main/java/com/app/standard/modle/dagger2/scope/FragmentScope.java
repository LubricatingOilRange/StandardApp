package com.app.standard.modle.dagger2.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

//  Created by ruibing.han on 2018/3/29.
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScope {
    //在dagger2中 创建一片区域   来存储被标记的Fragment
}
