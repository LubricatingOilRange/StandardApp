package com.app.recycler.entry;

//  Created by ruibing.han on 2018/4/2.

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ComplexItem implements MultiItemEntity {

    public static final int ITEM_TYPE_1 = 1;
    public static final int ITEM_TYPE_2 = 2;
    public static final int ITEM_TYPE_3 = 3;
    public static final int ITEM_TYPE_4 = 4;
    public static final int ITEM_TYPE_5 = 5;

    //占的百分比
    public static final int SPAN_SIZE_1 = 1;
    public static final int SPAN_SIZE_2 = 2;
    public static final int SPAN_SIZE_3 = 3;
    public static final int SPAN_SIZE_4 = 4;
    private int itemType;
    private int spanSize;

    public ComplexItem(int itemType, int spanSize, String content) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.content = content;
    }

    public ComplexItem(int itemType, int spanSize) {
        this.itemType = itemType;
        this.spanSize = spanSize;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
