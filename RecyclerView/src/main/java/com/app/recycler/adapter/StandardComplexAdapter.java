package com.app.recycler.adapter;

//  Created by ruibing.han on 2018/4/2.

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import com.app.recycler.R;
import com.app.recycler.entry.ComplexItem;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public abstract class StandardComplexAdapter extends BaseMultiItemQuickAdapter<ComplexItem, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    protected StandardComplexAdapter(List data) {
        super(data);

        int[] layoutArray = getLayoutArray();
        int length = layoutArray.length;
        if (length < 2) {
            throw new IllegalStateException("Number of layouts greater than 2");
        }
        //添加五种类型的布局
        addItemType(ComplexItem.ITEM_TYPE_1, layoutArray[0]);
        addItemType(ComplexItem.ITEM_TYPE_2, layoutArray[1]);

        if (length > 2) {
            addItemType(ComplexItem.ITEM_TYPE_3, layoutArray[2]);
        }

        if (length > 3) {
            addItemType(ComplexItem.ITEM_TYPE_4, layoutArray[3]);
        }

        if (length > 4) {
            addItemType(ComplexItem.ITEM_TYPE_5, layoutArray[4]);
        }

        if (length > 5) {
            throw new IllegalStateException("The number of layouts should not exceed 5 ");
        }
    }

     protected abstract @NonNull int[] getLayoutArray();//获取布局
}
