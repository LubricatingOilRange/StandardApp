package com.app.standard.component.annotation;

//  Created by ruibing.han on 2018/4/12.

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Documented
@Retention(RetentionPolicy.RUNTIME)//定义生命周期
@Target(ElementType.FIELD)//定义作用的位置
public @interface FruitName {

    String name() default "";//定义一个方法名  默认返回""空字符串

}
