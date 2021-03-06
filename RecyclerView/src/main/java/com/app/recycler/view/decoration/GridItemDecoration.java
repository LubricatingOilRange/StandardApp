package com.app.recycler.view.decoration;

//  Created by ruibing.han on 2018/4/4.

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * @author 网格布局分割线
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private int mSpanCount;

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable mDivider;

    public GridItemDecoration(Context context, int spanCount) {
        this.mContext = context;
        this.mSpanCount = spanCount;
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    /**
     * Sets the {@link Drawable} for this divider.
     *
     * @param drawable Drawable that should be used as a divider.
     */
    public void setDrawable(@NonNull Drawable drawable) {
        mDivider = drawable;
    }

    public void setDrawable(@DrawableRes int drawableRes) {
        mDivider = ContextCompat.getDrawable(mContext, drawableRes);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, State state) {

        int childCount = parent.getChildCount();
        for (int itemPosition = 0; itemPosition < parent.getChildCount(); itemPosition++) {

            final View child = parent.getChildAt(itemPosition);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();

            if (itemPosition + 1 + mSpanCount <= childCount) {//该条目下面有条目
                //判断是否是最后一列
                if (isLastColumn(parent, itemPosition, mSpanCount, childCount)) {
                    //横向
                    drawHorizontal(c, child, params, itemPosition,childCount);
                } else {
                    //纵向
                    drawVertical(c, child, params);
                    //横向
                    drawHorizontal(c, child, params, itemPosition,childCount);
                }
            } else { //该条目下面没有条目 -- 不绘制底部线
                if (itemPosition + 1 != childCount && !isLastColumn(parent, itemPosition, mSpanCount, childCount)) {
                    //不是最后一个，也不是最后一列
                    //纵向
                    drawVertical(c, child, params);
                }
            }
        }

    }

    private void drawHorizontal(Canvas c, View child, RecyclerView.LayoutParams params, int itemPosition, int childCount) {
        final int left = child.getLeft() - params.leftMargin;
        int point;
        if (itemPosition + 1 + mSpanCount == childCount) {
            point = 0;
        } else {
            point = mDivider.getIntrinsicWidth();
        }
        final int right = child.getRight() + params.rightMargin + point;
        final int top = child.getBottom() + params.bottomMargin;
        final int bottom = top + mDivider.getIntrinsicHeight();
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);

    }

    private void drawVertical(Canvas c, View child, RecyclerView.LayoutParams params) {
        final int top = child.getTop() - params.topMargin;
        final int bottom = child.getBottom() + params.bottomMargin;
        final int left = child.getRight() + params.rightMargin;
        final int right = left + mDivider.getIntrinsicWidth();

        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        int childCount = parent.getAdapter().getItemCount();

        if (itemPosition + 1 + mSpanCount <= childCount) {//该条目下面有条目
            //判断是否是最后一列
            if (isLastColumn(parent, itemPosition, mSpanCount, childCount)) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicWidth());
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicWidth());
            }

        } else { //该条目下面没有条目 -- 不绘制底部线
            if (itemPosition + 1 != childCount && !isLastColumn(parent, itemPosition, mSpanCount, childCount)) {

                //不是最后一个，也不是最后一列
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicWidth());
            } else {
                if (itemPosition + 1 == childCount && !isLastColumn(parent, itemPosition, mSpanCount, childCount)) {
                    outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicWidth());
                } else {
                    outRect.set(0, 0, 0, mDivider.getIntrinsicWidth());
                }
            }
        }
    }


    private int getSpanCount(RecyclerView parent) {
        // 列数
        int mSpanCount = -1;
        LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {

            mSpanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            mSpanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }
        return mSpanCount;
    }

    //判断是否是最后一行
    private boolean isLastRaw(RecyclerView parent, int pos, int mSpanCount,
                              int childCount) {
        LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            childCount = childCount - childCount % mSpanCount;//8 - 8%3 = 6
            if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
                return true;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                childCount = childCount - childCount % mSpanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount)
                    return true;
            } else
            // StaggeredGridLayoutManager 且横向滚动
            {
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % mSpanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isLastColumn(RecyclerView parent, int pos, int mSpanCount,
                                 int childCount) {
        LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos + 1) % mSpanCount == 0) {// 如果是最后一列，则不需要绘制右边
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((pos + 1) % mSpanCount == 0) {// 如果是最后一列，则不需要绘制右边
                    return true;
                }
            } else {
                childCount = childCount - childCount % mSpanCount;
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                    return true;
            }
        }
        return false;
    }
}
