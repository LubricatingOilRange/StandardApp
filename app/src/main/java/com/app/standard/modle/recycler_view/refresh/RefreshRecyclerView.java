package com.app.standard.modle.recycler_view.refresh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.standard.R;
import com.app.standard.util.ViewTool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RefreshRecyclerView extends CustomRefreshLayout {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefresh;

    private Animation mAnimation;

    public RefreshRecyclerView(Context context) {
        super(context);
    }

    public RefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //获取加载中的布局ID
    @Override
    public int getLoadingLayoutId() {
        return R.layout.layout_loading;
    }

    private ImageView iv_progress;
    @Override
    public void onInitLoadingLayout(View loadingView) {
        iv_progress = loadingView.findViewById(R.id.iv_progress);
        mAnimation = AnimationUtils.loadAnimation(mContext, R.anim.set_rorate_loading);//给图片添加动态效果
    }

    //展示加载中动画效果
    @Override
    public void onShowLoading() {
        Animation animation = iv_progress.getAnimation();
        if (animation != null) {
            animation.start();
        } else {
            iv_progress.startAnimation(mAnimation);
        }
    }

    //取消加载中动画效果
    @Override
    public void onCancelLoading() {
        Animation animation = iv_progress.getAnimation();
        if (animation != null) {
            animation.cancel();
        }
    }

    @Override
    public int getSuccessfulLayoutId() {
        return R.layout.layout_frag_recycler;//获取成功的布局ID
    }

    @Override
    public void onInitSuccessfulLayout(View successfulView) {
        mSwipeRefresh = successfulView.findViewById(R.id.frag_swipeRefresh);
        mRecyclerView = successfulView.findViewById(R.id.frag_recyclerView);
        onInitSwipeRefreshLayout(mSwipeRefresh);//初始化刷新布局
    }

    //获取列表RecyclerView
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    //获取加载失败或空数据的布局ID
    @Override
    public int getEmptyFailureLayoutId() {
        return R.layout.layout_empty_failure;
    }

    ////初始化失败或空数据布局
    @Override
    public void onInitEmptyFailureLayout(View emptyFailureView) {
        TextView tv_empty = emptyFailureView.findViewById(R.id.tv_empty);
        tv_empty.setText("暂无数据...");
        ImageView iv_empty = emptyFailureView.findViewById(R.id.iv_empty);
        iv_empty.setBackgroundResource(R.mipmap.ic_search);
    }

    /**
     * 加载中页面，成功页面，失败或空数据页面切换
     *
     * @param loadingVisible      加载中页面
     * @param successfulVisible   成功页面
     * @param emptyFailureVisible 失败或空数据页面
     */
    @Override
    public void setFragViewVisible(boolean loadingVisible, boolean successfulVisible, boolean emptyFailureVisible) {
        ViewTool.setVisible(mLoadingView,loadingVisible);
        ViewTool.setVisible(mSuccessfulView,successfulVisible);
        ViewTool.setVisible(mEmptyFailureView,emptyFailureVisible);
    }

    //获取下拉刷新的布局ID
    private ProgressBar pb_refresh;
    private ImageView iv_refresh_arrow;
    private TextView tv_refresh_message,tv_refresh_update_time;
    @Override
    public View getPullRefreshLayout() {
        View pullRefreshView = LayoutInflater.from(mContext).inflate(R.layout.layout_pull_refresh, null);
        pb_refresh =  pullRefreshView.findViewById(R.id.pb_refresh);//加载进度
        iv_refresh_arrow = pullRefreshView.findViewById(R.id.iv_refresh_arrow);//箭头方向
        tv_refresh_message =  pullRefreshView.findViewById(R.id.tv_refresh_message);//刷新状态提示
        tv_refresh_update_time =  pullRefreshView.findViewById(R.id.tv_refresh_update_time);//刷新时间
        return pullRefreshView;
    }

    // 获取日期
    @SuppressLint("SimpleDateFormat")
    public static String getRefreshDate() {
        Date curDates = new Date(System.currentTimeMillis());// 获取当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");// 输入日期的格式
        return simpleDateFormat.format(curDates);
    }

    //初始化下拉刷新布局
    @Override
    public void onInitPullRefreshView(View pullRefreshView) {
        ViewTool.setINVisible(pb_refresh);//进度默认不可见

        ViewTool.setVisible(iv_refresh_arrow,true);//箭头方向背景默认可见
        iv_refresh_arrow.setImageResource(R.mipmap.ic_arrow);//设置默认背景

        tv_refresh_message.setText("下拉刷新");//刷新提示默认下拉刷新

        tv_refresh_update_time.setText(getRefreshDate());//默认时间
    }

    //half下拉是否过半，更新释放立即刷新(true)  (false) 的切换的布局
    @Override
    public void onUpdatePullRefreshRelease(boolean half) {
        tv_refresh_message.setText(half ? "释放立即刷新" : "下拉刷新");

        ViewTool.setVisible(iv_refresh_arrow,true);//显示箭头背景
        iv_refresh_arrow.setRotation(half ? 180 : 0);//设置箭头 没超过一半朝下，否则超下
    }

    //更新正在刷新布局
    @Override
    public void onUpdateOnRefreshingView() {
        ViewTool.setVisible(pb_refresh,true);//显示加载进图-正在加载状态
        ViewTool.setINVisible(iv_refresh_arrow);//隐藏箭头背景
        tv_refresh_message.setText("正在刷新");//正在刷新
        tv_refresh_update_time.setText(getRefreshDate());//更新刷新时间
    }

    //下拉刷新 下拉后距离顶部的距离
    @Override
    public void onPullRefreshDistance(int distance) {

    }

    //初始化加载更多布局
    private ProgressBar pb_load_more;
     private ImageView iv_load_more;
    private TextView tv_load_more_message,tv_load_more_time;
    @Override
    public View getLoadMoreLayout() {
        View loadMoreView = LayoutInflater.from(mContext).inflate(R.layout.layout_load_more, null);
        pb_load_more =  loadMoreView.findViewById(R.id.pb_load_more);//加载进度
        iv_load_more =  loadMoreView.findViewById(R.id.iv_load_more);//箭头方向
        tv_load_more_message =  loadMoreView.findViewById(R.id.tv_load_more_message);//刷新状态提示
        tv_load_more_time =  loadMoreView.findViewById(R.id.tv_load_more_time);//刷新时间
        return loadMoreView;
    }


    @Override
    public void onInitLoadMoreView(View loadMoreView) {
        pb_load_more = loadMoreView.findViewById(R.id.pb_load_more);

        ViewTool.setVisible(pb_load_more,true);//进度默认不可见

        ViewTool.setVisible(iv_load_more,true);//箭头方向背景默认可见
        iv_load_more.setImageResource(R.mipmap.ic_arrow);//设置默认背景

        tv_load_more_message.setText("上拉加载更多");///上拉加载更多

        tv_load_more_time.setText(getRefreshDate());//刷新提示默认下拉刷新

    }

    //更新正在加载更多布局
    @Override
    public void onUpdateLoadMoreView() {
        ViewTool.setVisible(pb_load_more,true);//进度默认可见

        ViewTool.setINVisible(iv_load_more);//箭头背景默认不可见

        tv_load_more_message.setText("正在加载");//正在加载

        tv_load_more_time.setText(getRefreshDate());
    }

    //上拉加载更多 上拉后距离底部的距离
    @Override
    public void onLoadMoreDistance(int distance) {

    }

    //上拉是否过半，更新上拉加载(false)  -- 松开加载(true)的切换
    @Override
    public void onUpdateLoadMoreRelease(boolean half) {
        tv_load_more_message.setText(half ? "松开加载" : "上拉加载");
        ViewTool.setVisible(iv_load_more,true);//设置箭头背景可见
        iv_load_more.setRotation(half ? 0 : 180);
    }

    //下拉刷新成功
    @Override
    public void onPushRefreshSuccessful() {
        ViewTool.setINVisible(pb_refresh);//隐藏刷新进度
        tv_refresh_message.setText("刷新成功");//刷新成功
        mSwipeRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSwipeRefresh != null) {
                    mSwipeRefresh.setRefreshing(false);
                }
            }
        }, 1000);
    }

    //下拉刷新失败
    @Override
    public void onPushRefreshFailure() {
        ViewTool.setINVisible(pb_refresh);//隐藏刷新进度
        tv_refresh_message.setText("刷新失败");
        mSwipeRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSwipeRefresh != null) {
                    mSwipeRefresh.setRefreshing(false);
                }
            }
        }, 1000);
    }

    //加载更多成功
    @Override
    public void onLoadMoreSuccessful() {
        ViewTool.setINVisible(pb_load_more);//隐藏加载更多进度
        ViewTool.setINVisible(iv_load_more);//隐藏加载更多 箭头布局
        tv_load_more_message.setText("加载完成");
        mSwipeRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSwipeRefresh != null) {
                    mSwipeRefresh.setLoadMore(false);
                }
            }
        }, 1000);
    }

    //加载更多失败
    @Override
    public void onLoadMoreFailure() {
        ViewTool.setINVisible(pb_load_more);//隐藏加载更多进度
        ViewTool.setINVisible(iv_load_more);//隐藏加载更多 箭头布局
        tv_load_more_message.setText("加载失败");
        mSwipeRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSwipeRefresh != null) {
                    mSwipeRefresh.setLoadMore(false);
                }
            }
        }, 1000);
    }

    //加载更多成功-没有更多数据了
    @Override
    public void onLoadNoMore() {
        ViewTool.setINVisible(pb_load_more);//隐藏加载更多进度
        ViewTool.setINVisible(iv_load_more);//隐藏加载更多 箭头布局
        tv_load_more_message.setText("没有更多数据了");
        mSwipeRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSwipeRefresh != null) {
                    mSwipeRefresh.setLoadMore(false);
                }
            }
        }, 1000);
    }
}
