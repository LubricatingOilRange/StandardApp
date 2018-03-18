package com.app.recycler.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.app.recycler.R;

public class RefreshView extends MySwipeRefreshLayout {
    public RefreshView(Context context) {
        super(context);
    }

    public RefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //获取加载中的布局ID
    @Override
    public int getLoadingLayoutId() {
        return R.layout.layout_loading;
    }

    //展示加载中动画效果
    @Override
    public void onShowLoading() {

    }

    //取消加载中动画效果
    @Override
    public void onCancelLoading() {

    }

    //获取加载失败或空数据的布局ID
    @Override
    public int getEmptyFailureLayoutId() {
        return R.layout.layout_empty_failure;
    }

    /**
     * 加载中页面，成功页面，失败或空数据页面切换
     * @param loadingVisible 加载中页面
     * @param successfulVisible 成功页面
     * @param emptyFailureVisible 失败或空数据页面
     */
    @Override
    public void setFragViewVisible(boolean loadingVisible, boolean successfulVisible, boolean emptyFailureVisible) {

    }

    //获取下拉刷新的布局ID
    @Override
    public int getPullRefreshLayoutId() {
        return R.layout.layout_pull_refresh;
    }

    //初始化下拉刷新布局
    @Override
    public void onInitPullRefreshView(View pullRefreshView) {

    }

    //更新正在刷新布局
    @Override
    public void onUpdateOnRefreshingView() {

    }

    //下拉刷新 下拉后距离顶部的距离
    @Override
    public void onPullRefreshDistance(int distance) {

    }

    //下拉过半后，更新下拉刷新(false)  -- 释放立即刷新(true)的切换的布局
    @Override
    public void onUpdatePullRefreshRelease(boolean half) {

    }

    //获取加载更多的布局ID
    @Override
    public int getLoadMoreLayoutId() {
        return R.layout.layout_load_more;
    }

    //初始化加载更多布局
    @Override
    public void onInitLoadMoreView(View loadMoreView) {

    }

    //更新正在加载更多布局
    @Override
    public void onUpdateLoadMoreView() {

    }

    //上拉加载更多 上拉后距离底部的距离
    @Override
    public void onLoadMoreDistance(int distance) {

    }

    //上拉过半后，更新上拉加载(false)  -- 松开刷新(true)的切换
    @Override
    public void onUpdateLoadMoreRelease(boolean half) {

    }

    //下拉刷新成功
    @Override
    public void onPushRefreshSuccessful() {

    }

    //下拉刷新失败
    @Override
    public void onPushRefreshFailure() {

    }

    //加载更多成功
    @Override
    public void onLoadMoreSuccessful() {

    }

    //加载更多失败
    @Override
    public void onLoadMoreFailure() {

    }

    //加载更多成功-没有更多数据了
    @Override
    public void onLoadNoMore() {

    }
}
