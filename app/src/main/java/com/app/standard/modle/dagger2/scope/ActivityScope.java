package com.app.standard.modle.dagger2.scope;

//  Created by ruibing.han on 2018/3/29.

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
    //在dagger2中 创建一片区域   来存储被标记的Activity
}
