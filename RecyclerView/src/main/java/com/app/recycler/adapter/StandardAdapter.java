package com.app.recycler.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

// 只有一级 条目
public abstract class StandardAdapter<T> extends BaseQuickAdapter<T,BaseViewHolder> {
    public StandardAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
        openLoadAnimation(StandardAdapter.SLIDEIN_LEFT);//打开动画效果
        isFirstOnly(false);// false (条目每次出现在页面上展示动画效果) true(只在第一次出现在页面上的时候出现动画效果)
    }
}
