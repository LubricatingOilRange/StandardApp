package com.app.standard.component.annotation;

//  Created by ruibing.han on 2018/4/12.

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FruitProvider {

    int providerId() default -1;//供应商编号

    String providerName() default "";//供应商名称

    String providerAddress() default "";//供应商地址
}
