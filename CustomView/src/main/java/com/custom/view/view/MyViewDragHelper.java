package com.custom.view.view;

//  Created by ruibing.han on 2018/4/16.

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import static android.support.v4.view.MotionEventCompat.getActionMasked;

public class MyViewDragHelper extends LinearLayout {
    private ViewDragHelper mViewDragHelper;
    public MyViewDragHelper(Context context) {
        this(context,null);
    }

    public MyViewDragHelper(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyViewDragHelper(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initViewDragHelper();
    }

    private void initViewDragHelper() {
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {

            //true则表示可以捕获该view
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                //开启边界之后, 这个方法的返回值可能需要进一步处理.要不然开边界就没啥意思了.

                //child 表示想要滑动的view
                //pointerId 表示触摸点的id, 比如多点按压的那个id
                //返回值表示,是否可以capture,也就是是否可以滑动.可以根据不同的child决定是否可以滑动
                return true;
            }

            //监听拖动状态的改变
            @Override
            public void onViewDragStateChanged(int state) {
                super.onViewDragStateChanged(state);
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
            }

            //捕获View
            @Override
            public void onViewCaptured(View capturedChild, int activePointerId) {
                super.onViewCaptured(capturedChild, activePointerId);
            }

            //释放的时候, 会回调下面的方法
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                //调用这个方法,就可以设置releasedChild回弹得位置.
                mViewDragHelper.settleCapturedViewAt(0, 100);//参数就是x,y的坐标
                postInvalidate();//注意一定要调用这个方法,否则没效果.
            }

            //当开启边界滑动之后, 此方法就会回调
            @Override
            public void onEdgeTouched(int edgeFlags, int pointerId) {
                //通常开启边界之后, 都需要手动capture view.之后就可以滑动view了.
                mViewDragHelper.captureChildView(getChildAt(1), pointerId);
            }

            @Override
            public boolean onEdgeLock(int edgeFlags) {
                return super.onEdgeLock(edgeFlags);
            }

            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                super.onEdgeDragStarted(edgeFlags, pointerId);
            }

            @Override
            public int getOrderedChildIndex(int index) {
                return super.getOrderedChildIndex(index);
            }

            @Override
            public int getViewHorizontalDragRange(View child) {
                return super.getViewHorizontalDragRange(child);
            }

            @Override
            public int getViewVerticalDragRange(View child) {
                return super.getViewVerticalDragRange(child);
            }

            //控制child只能在ViewGroup的横向中移动
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                //child 表示当前正在移动的view
                //left 表示当前的view正要移动到左边距为left的地方
                //dx 表示和上一次滑动的距离间隔
                //返回值就是child要移动的目标位置.可以通过控制返回值,从而控制child只能在ViewGroup的范围中移动.
                final int leftBound = getPaddingLeft();
                final int rightBound = getWidth() - child.getWidth();
                return Math.min(Math.max(left, leftBound), rightBound);
            }

            //控制child只能在ViewGroup的纵向中移动
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                //child 表示当前正在移动的view
                //top 表示当前的view正要移动到上边距为top的地方
                //dx 表示和上一次滑动的距离间隔
                final int topBound = getPaddingTop();
                final int bottomBound = getHeight() - child.getHeight();
                return Math.min(Math.max(top, topBound), bottomBound);
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //固定写法
        int action = MotionEventCompat.getActionMasked(ev);
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mViewDragHelper.cancel();
            return false;
        }
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //固定写法
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        //固定写法
        //此方法用于自动滚动,比如自动回滚到默认位置.
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
