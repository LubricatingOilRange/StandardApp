package com.app.standard.component.annotation;

//  Created by ruibing.han on 2018/4/12.

public class Fruit {
    @FruitName(name = "苹果")
    private String name;

    @FruitColor(fruitColor = FruitColor.Color.RED)
    private String color;

    @FruitProvider(providerId = 12323,providerName = "宝尊电商",providerAddress = "万荣路1256号")
    private String providerInfo;

    public Fruit() {
    }

    public Fruit(String name, String color, String providerInfo) {
        this.name = name;
        this.color = color;
        this.providerInfo = providerInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProviderInfo() {
        return providerInfo;
    }

    public void setProviderInfo(String providerInfo) {
        this.providerInfo = providerInfo;
    }

    @Override
    public String toString() {
        return "Fruit介绍:" + name + "\n" + color + "\n" + providerInfo;
    }
}
