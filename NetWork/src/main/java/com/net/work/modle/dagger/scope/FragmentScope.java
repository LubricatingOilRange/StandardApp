package com.net.work.modle.dagger.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FragmentScope {
    //定义一个Fragment的区域
}
