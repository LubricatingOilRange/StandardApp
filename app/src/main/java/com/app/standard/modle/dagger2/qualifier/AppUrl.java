package com.app.standard.modle.dagger2.qualifier;

//  Created by ruibing.han on 2018/3/29.

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface AppUrl {
    //定义一个主要页面AppUrl的标记
}
