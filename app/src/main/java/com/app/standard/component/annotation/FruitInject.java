package com.app.standard.component.annotation;

//  Created by ruibing.han on 2018/4/12.

import java.lang.reflect.Field;

/**
 *
 * 返回直接存在于此元素上的所有注解
 * 与此接口中的其他方法不同，该方法将忽略继承的注解
 * clazz.getDeclaredFields()
 *
 * field.isAnnotationPresent(XXX.class)  判断该程序元素上是否包含指定类型的注解，存在则返回true，否则返回false
 * field.getAnnotation(XXX.class)        返回该程序元素上存在的、指定类型的注解，如果该类型注解不存在，则返回null
 */
public class FruitInject {
    public static void inject(Object object)   {
        Class<?> clazz = object.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (clazz.getSimpleName().equals("Fruit")) {
                Fruit fruit;
                fruit = (Fruit) object;
                if (field.isAnnotationPresent(FruitName.class)) {
                    FruitName fruitName = field.getAnnotation(FruitName.class);
                    fruit.setName(fruitName.name());
                } else if (field.isAnnotationPresent(FruitColor.class)) {
//                    FruitColor fruitColor = field.getAnnotation(FruitColor.class);
//                    FruitColor.Color color = fruitColor.fruitColor();
                    fruit.setColor("颜色");
                } else if (field.isAnnotationPresent(FruitProvider.class)){
                    FruitProvider fruitProvider = field.getAnnotation(FruitProvider.class);
                    int providerId = fruitProvider.providerId();
                    String providerName = fruitProvider.providerName();
                    String providerAddress = fruitProvider.providerAddress();
                    fruit.setProviderInfo("provider信息:" + providerId + "/" + providerName + "/" + providerAddress);
                }
            }
        }
    }

}
