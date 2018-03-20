package com.app.recycler.view.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

/**
 * Created by ruibing.han on 2018/3/20.
 */

public class SuspendItemDecoration extends RecyclerView.ItemDecoration {

   private int mGroupHeight = 120;  //悬浮栏高度

    private int mDividerHeight;//分割线的高度

    private SuspendItemListener mSuspendItemListener;

    public SuspendItemDecoration() {
    }

    //通过该方法，在Canvas上绘制内容，在绘制Item之前调用
    //如果没有通过getItemOffsets设置偏移的话，Item的内容会将其覆盖
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    //在Canvas上绘制内容,在Item之后调用。(画的内容会覆盖在item的上层)
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    //通过Rect为每个Item设置偏移，用于绘制Decoration
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if ( layoutManager instanceof LinearLayoutManager) {//判断当前RecyclerView是不是使用LinearLayoutManager
            if (((LinearLayoutManager) layoutManager).getOrientation() == LinearLayoutManager.HORIZONTAL) {//禁用横向
                throw new IllegalArgumentException(
                        "Invalid orientation. It should be VERTICAL");
            }
            int position = parent.getChildAdapterPosition(view);
            if (isFirstInGroup(position)) {//一组数据的第一个
                outRect.top = mGroupHeight;
            } else {
                if (mDividerHeight > 0) {//设置分割线
                    outRect.top = mDividerHeight;
                }
            }
        }
    }

    /**
     * 判断是不是组中的第一个位置
     * 根据前一个组名，判断当前是否为新的组
     * 当前为groupId为null时，则与上一个为同一组
     */
    protected boolean isFirstInGroup(int position) {
        String preGroupName;
        if (position == 0) {
            preGroupName = null;
        } else {
            preGroupName = mSuspendItemListener.getGroupName(position - 1);
        }
        String curGroupName = mSuspendItemListener.getGroupName(position);
        return !TextUtils.equals(preGroupName, curGroupName);
    }
}
