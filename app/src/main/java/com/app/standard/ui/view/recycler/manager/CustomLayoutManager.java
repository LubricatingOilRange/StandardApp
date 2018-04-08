package com.app.standard.ui.view.recycler.manager;

//  Created by ruibing.han on 2018/4/8.

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CustomLayoutManager extends RecyclerView.LayoutManager {

    public final static int VERTICAL = 0;

    public final static int HORIZONTAL = 1;

    private int mTotalHeightOrWidth;

    private int mOrientation;

    private int mOffsetYOrX;

    public CustomLayoutManager(int orientation) {

        setOrientation(orientation);
    }

    private void setOrientation(int orientation) {
        if (orientation == VERTICAL || orientation == HORIZONTAL) {
            this.mOrientation = orientation;
        } else {
            throw new IllegalStateException("orientation is not VERTICAL or HORIZONTAL");
        }
    }

    //子View的layoutParam布局参数
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    //是否能垂直滑动
    @Override
    public boolean canScrollVertically() {
        return this.mOrientation == VERTICAL;
    }

    //处理垂直滑动
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int distance = dy;
        int visibleHeight = getVerticalVisibleHeight();
        int moreHeight = mTotalHeightOrWidth - visibleHeight;

        if (mOffsetYOrX + dy < 0) {//滑到顶部
            distance = -mOffsetYOrX;
        } else if (mTotalHeightOrWidth > visibleHeight && mOffsetYOrX + dy > moreHeight) {//滑到顶部
            distance = mTotalHeightOrWidth - visibleHeight - mOffsetYOrX;
        }

        mOffsetYOrX += distance;

        offsetChildrenVertical(-distance);

        return distance;
    }

    //是否能水平滑动
    @Override
    public boolean canScrollHorizontally() {
        return this.mOrientation == HORIZONTAL;
    }

    //处理水平滑动
    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int distance = dx;

        int visibleWidth = getHorizontalVisibleWidth();

        int moreWidth = mTotalHeightOrWidth - visibleWidth;

        if (mOffsetYOrX + dx < 0) {
            distance = -mOffsetYOrX;
        } else if(mTotalHeightOrWidth > visibleWidth && mOffsetYOrX + dx > moreWidth){
            distance = mTotalHeightOrWidth - visibleWidth - mOffsetYOrX;
        }

        mOffsetYOrX += distance;

        offsetChildrenHorizontal(-distance);

        return distance;
    }

    //对子View进行排版
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        detachAndScrapAttachedViews(recycler);

        int offsetYOrX = 0;

        for (int i = 0; i < getItemCount(); i++) {
            //添加
            View childView = recycler.getViewForPosition(i);
            addView(childView);

            //测量
            measureChildWithMargins(childView, 0, 0);

            offsetYOrX  += onLayoutOrientation(childView, offsetYOrX);
        }
        mTotalHeightOrWidth = offsetYOrX;
    }

    private int onLayoutOrientation(View childView, int offset) {

        int childWidth = getDecoratedMeasuredWidth(childView);
        int childHeight = getDecoratedMeasuredHeight(childView);

        //排版
        if (mOrientation == VERTICAL) {
            layoutDecorated(childView, 0, offset, childWidth, offset + childHeight);
            return childHeight;
        } else {
            layoutDecorated(childView, offset, 0, offset + childWidth, childHeight);
            return childWidth;
        }
    }

    private int getVerticalVisibleHeight() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }

    private int getHorizontalVisibleWidth() {
        return getWidth() - getPaddingStart() - getPaddingEnd();
    }
}
