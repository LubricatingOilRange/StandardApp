package com.custom.view.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.UiThread;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MySeekBar extends View implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {
    /**
     * SeekBar最小值
     */
    int minValue;
    /**
     * SeekBar共包含多少个坐标点
     */
    int pointCount;
    /**
     * 每个分段代表的数值
     */
    private int perValue;
    /**
     * 分段的端点坐标记录数组，长度等于pointCount
     */
    Point[] mPoints;
    /**
     * SeekBar长宽比
     */
    float mLWRatio = 1f / 10f;
    CircleIndicator mLeftCI;//左侧滑动指示器
    CircleIndicator mRightCI;//右侧滑动指示器
    int mR;//滑动指示器半径
    int mPadding = 5;//指定的Padding

    Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint indicatorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    float LINE_WIDTH = 8F;//线宽
    /**
     * 滑动指示器颜色
     */
    private int indicatorColor;
    /**
     * 2个滑动器之间的线颜色
     */
    private int indicatorLineColor;
    /**
     * 背景线颜色
     */
    private int backLineColor;
    /**
     * 圆形区域
     */
    private RectF mRectF;
    /**
     * 当前SeekBar是否有滑动指示器处于被滑动状态
     */
    boolean isSelected = false;
    /**
     * 属性动画是否正在执行
     */
    boolean isPlaying;
    /**
     * 与滑动指示器最近的mPoints index值
     */
    private int mCloseIndex;
    /**
     * 动画时长
     */
    private final long ANIM_DURATION = 200;


    public MySeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //无视padding属性 使用内部定义的mPadding
        setPadding(0, 0, 0, 0);
        init(context, attrs);
    }

    /**
     * Returns the model object that handles single selections.
     *
     * @param context the new MenuBarUI L&F object
     * @return the <code>SingleSelectionModel</code> property
     * @see MySeekBar
     * @see MySeekBar#init
     * @see MySeekBar#onMeasure
     */
    private void init(Context context, AttributeSet attrs) {
        //获取自定义属性
        perValue = 10;
        minValue = 80;
        pointCount = 20;
        backLineColor = Color.LTGRAY;
        indicatorLineColor = Color.GREEN;
        indicatorColor = Color.GRAY;
        //初始化SeekBar内部坐标对象
        mPoints = new Point[pointCount];
        for (int i = 0; i < mPoints.length; i++) {
            mPoints[i] = new Point(minValue + i * perValue);
        }
        //初始化2个滑动指示器 默认左边的位于mPoints数组第一个，右边位于mPoints数组最后一个
        mLeftCI = new CircleIndicator();
        mLeftCI.setPoint(mPoints[0]);
        mRightCI = new CircleIndicator();
        mRightCI.setPoint(mPoints[mPoints.length - 1]);
        //初始化Paint
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeCap(Paint.Cap.ROUND);
        linePaint.setStrokeWidth(LINE_WIDTH);
        //初始化滑动指示器Paint
        indicatorPaint.setStyle(Paint.Style.FILL);
        indicatorPaint.setColor(indicatorColor);

        //设置阴影 注意：当前view需要添加 android:layerType="software"
        indicatorPaint.setShadowLayer(5, 2, 2, Color.LTGRAY);
        //初始化圆形区域
        mRectF = new RectF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = (int) (size * mLWRatio);
        int spec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY);
        //设置View的长宽比
        setMeasuredDimension(widthMeasureSpec, spec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //CircleIndicator半径
        mR = h / 2 - mPadding;
        //指定滑动指示器半径
        mLeftCI.setR(mR);
        mRightCI.setR(mR);
        //分段点坐标 mPoints数组 均分SeekBar宽度
        int y = h / 2;
        int perWidth = (w - 2 * mPadding - 2 * mR) / (mPoints.length - 1);
        for (int i = 0; i < mPoints.length; i++) {
            mPoints[i].setX(mPadding + mR + i * perWidth);
            mPoints[i].setY(y);
        }
        //更新一下 滑动指示器当前的坐标
        mLeftCI.setPoint(mLeftCI.getPoint());
        mRightCI.setPoint(mRightCI.getPoint());
        //回调当前Activity 告知2个滑动指示器的属性
        callBack();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        if (mPoints.length >= 2) {
            //画背景线
            linePaint.setColor(backLineColor);
            canvas.drawLine(mPoints[0].getX(), mPoints[0].getY()
                    , mPoints[mPoints.length - 1].getX(), mPoints[mPoints.length - 1].getY(), linePaint);
            //画区间线
            linePaint.setColor(indicatorLineColor);
            canvas.drawLine(mLeftCI.getCurX(), mLeftCI.getCurY(), mRightCI.getCurX(), mRightCI.getCurY(), linePaint);

            //画左边的Indicator
            mRectF.set(mLeftCI.getCurX() - mR, mLeftCI.getCurY() - mR,
                    mLeftCI.getCurX() + mR, mLeftCI.getCurY() + mR);
            canvas.drawOval(mRectF, indicatorPaint);
            //画右边的Indicator
            mRectF.set(mRightCI.getCurX() - mR, mRightCI.getCurY() - mR,
                    mRightCI.getCurX() + mR, mRightCI.getCurY() + mR);
            canvas.drawOval(mRectF, indicatorPaint);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //如果当前正在执行动画 则忽略用户点击
        if (isPlaying) {
            return true;
        }
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //检查当前按下的坐标是否命中滑动指示器
                isSelected = checkPoint(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                //在命中的情况下，滑动指示器会在有限范围内滑动
                move(x);
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            case MotionEvent.ACTION_UP:
                //当Up时，检查是否需要开启属性动画
                reset();
                break;
        }
        //如果已经有滑动指示器呗滑动了，就需要刷新当前View了
        if (isSelected) {
            invalidate();
        }
        return true;
    }

    private void reset() {
        //重置滑动状态
        isSelected = false;
        //执行动画
        if (mLeftCI.isTouch()) {
            mCloseIndex = getCloseIndex(mLeftCI);
            statAnim(mLeftCI, mCloseIndex);
        }
        if (mRightCI.isTouch()) {
            mCloseIndex = getCloseIndex(mRightCI);
            statAnim(mRightCI, mCloseIndex);
        }
        mLeftCI.setIsTouch(false);
        mRightCI.setIsTouch(false);
    }

    /**
     * 加载动画
     */
    private void statAnim(CircleIndicator rightCI, int closeIndex) {
        ObjectAnimator animator = ObjectAnimator.ofInt(rightCI, "curX", rightCI.getCurX(), mPoints[closeIndex].getX());
        animator.addUpdateListener(this);
        animator.addListener(this);
        animator.setDuration(ANIM_DURATION);
        animator.start();
    }

    /**
     * 获取距离 该Indicator最近的 坐标点
     *
     * @param indicator
     * @return
     */
    private int getCloseIndex(CircleIndicator indicator) {
        int curX = indicator.getCurX();
        int distance = Integer.MAX_VALUE;
        int index = 0;
        //循环找出距离当前indicator 最近的坐标对象
        for (int i = 0; i < mPoints.length; i++) {
            int abs = Math.abs(curX - mPoints[i].getX());
            if (abs <= distance) {
                distance = abs;
                index = i;
            }
        }
        if (indicator.equals(mLeftCI)) {
            //如果是左边的Indicator,那么最大的index不能超过 右边的Indicator所属的坐标index
            if (mPoints[index].getX() >= mRightCI.getCurX()) {
                index--;
            }
            return index;
        }
        if (indicator.equals(mRightCI)) {
            //同理
            if (mPoints[index].getX() <= mLeftCI.getCurX()) {
                index++;
            }
            return index;
        }
        return index;
    }

    private void move(float x) {
        if (mLeftCI.isTouch()) {
            //如果左边的Indicator呗拖拽，其x坐标应该在 第一个坐标 和右边的Indicator 之间
            //即限定 indicator可移动的范围
            if (x >= mPoints[0].getX() && x < mRightCI.getCurX()) {
                mLeftCI.setCurX((int) x);
            }
            return;
        }
        //同理
        if (mRightCI.isTouch()) {
            if (x <= mPoints[mPoints.length - 1].getX() && x > mLeftCI.getCurX()) {
                mRightCI.setCurX((int) x);
            }
        }
    }

    /**
     * 检查 Down的x y是否命中 CircleIndicator，如果命中更新属性
     *
     * @param x
     * @param y
     * @return true 命中， false 为命中
     */
    private boolean checkPoint(float x, float y) {
        boolean containsL = mLeftCI.getRect().contains((int) x, (int) y);
        if (containsL) {
            mLeftCI.setIsTouch(true);
            return true;
        }
        boolean containsR = mRightCI.getRect().contains((int) x, (int) y);
        if (containsR) {
            mRightCI.setIsTouch(true);
            return true;
        }
        return false;
    }

    /**
     * 设置2个Indicator的位置
     *
     * @param left
     * @param right
     */
    public void setPos(int left, int right) {
        if (right > left && right >= 0 && left >= 0 && right <= pointCount) {
            mLeftCI.setPoint(mPoints[left]);
            mRightCI.setPoint(mPoints[right]);
            callBack();
            invalidate();
        }
    }

    //addUpdateListener动画回调部分
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        //每次修改完成属性 就应该刷新当前View
        invalidate();
    }

    //addListener动画回调部分
    @Override
    public void onAnimationStart(Animator animation) {
        //动画执行开始
        isPlaying = true;
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        CircleIndicator indicator = (CircleIndicator) ((ObjectAnimator) animation).getTarget();
        //更新被移动Indicator 坐标属性
        indicator.setPoint(mPoints[mCloseIndex]);
        //动画执行结束
        isPlaying = false;
        //回调Activity告知 滑动指示器信息
        callBack();
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    /**
     * SeekBar被拖拽的滑动指示器
     */
    public class CircleIndicator {
        int curX;//indicator x坐标
        int curY;//indicator y坐标
        int mR;  //indicator 半径
        /**
         * 当前滑动指示器附着的坐标点
         */
        Point mPoint;
        /**
         * 是否被触摸
         */
        boolean isTouch;
        /**
         * Indicator 所包含的矩形区域
         */
        Rect mRect = new Rect();

        /**
         * 获取当前Indicator所在的矩形区域
         *
         * @return Rect
         */
         Rect getRect() {
            mRect.set(curX - mR, curY - mR, curX + mR, curY + mR);
            return mRect;
        }

         boolean isTouch() {
            return isTouch;
        }

         void setIsTouch(boolean isTouch) {
            this.isTouch = isTouch;
        }

         Point getPoint() {
            return mPoint;
        }

         void setPoint(Point point) {
            mPoint = point;
            curX = point.getX();
            curY = point.getY();
            invalidate();
        }

        public void setPosition(Point point) {
            curX = point.getX();
            curY = point.getY();
        }

        public int getR() {
            return mR;
        }

        public void setR(int r) {
            mR = r;
        }

        public int getCurX() {
            return curX;
        }

        public void setCurX(int curX) {
            this.curX = curX;
        }

        public int getCurY() {
            return curY;
        }

        public void setCurY(int curY) {
            this.curY = curY;
        }
    }

    /**
     * SeekBar内部的 坐标类
     */
    public class Point {
        /**
         * 当前坐标点所代表的数值
         */
        int mark;
        int x;//x坐标
        int y;//y坐标

        public Point(int mark) {
            this.mark = mark;
        }

        public int getMark() {
            return mark;
        }

        public void setMark(int mark) {
            this.mark = mark;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    public interface OnSeekFinishListener {
        void seekPos(CircleIndicator left, CircleIndicator right);
    }

    OnSeekFinishListener mListener;

    public void setListener(OnSeekFinishListener listener) {
        mListener = listener;
    }

    private void callBack() {
        if (mListener != null)
            mListener.seekPos(mLeftCI, mRightCI);
    }
}