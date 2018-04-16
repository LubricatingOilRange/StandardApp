package com.custom.view.view;

//  Created by ruibing.han on 2018/4/13.

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.custom.view.util.Logger;

/**
 * 1,初始化一个Scroller对象
 */

public class ScrollerView extends LinearLayout {
    private Scroller mScroller;
    private int mStartX, mStartY;
    private GestureDetector mGestureDetector;

    private Context mContext;

    public ScrollerView(Context context) {
        this(context, null);
    }

    public ScrollerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mScroller = new Scroller(context);//构造方法中 可以添加插值器

    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    public void startScroll() {
        mScroller.startScroll(mStartX, mStartY, -80, -160, 2000);
        mStartX -= 80;
        mStartY -= 160;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP :
                reset(0, 0);
                break;
            default:
                if (mGestureDetector == null) {
                    mGestureDetector = new GestureDetector(mContext, new GestureDetectorImpl());
                }
                return mGestureDetector.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }

    protected void reset(int x, int y) {
        int dx = x - mScroller.getFinalX();
        int dy = y - mScroller.getFinalY();
        beginScroll(dx, dy);
    }

    protected void beginScroll(int dx, int dy) {
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy);
        invalidate();
    }

     class GestureDetectorImpl implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            Logger.i("aaa","onDown");
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Logger.i("aaa","onShowPress");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Logger.i("aaa","onSingleTapUp");
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Logger.i("aaa","distanceX;" + distanceY + "/---distanceX:" + distanceY);
            int disY = (int) ((distanceY - 0.5) / 2);
            beginScroll(0, disY);
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Logger.i("aaa","onLongPress");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Logger.i("aaa","onFling");
            return false;
        }
    }
}
