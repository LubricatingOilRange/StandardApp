package com.app.recycler.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.app.recycler.R;
import com.app.recycler.adapter.StandardAdapter;
import com.app.recycler.view.CustomItemDecoration;
import com.app.recycler.view.CustomRefreshLayout;
import com.app.recycler.view.RefreshRecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RefreshActivity extends AppCompatActivity {

    private RefreshRecyclerView refreshView;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0://第一次获取数据成功
                    refreshView.setType(CustomRefreshLayout.SUCCESSFUL);
                    adapter.addData(getData());
                    break;
                case 1://第一次获取数据失败
                    refreshView.setType(CustomRefreshLayout.EMPTY_FAILURE);
                    break;
                case 2://下拉刷新成功
                    index = 0;
                    refreshView.setRefreshType(CustomRefreshLayout.REFRESH_SUCCESSFUL);
                    adapter.getData().clear();
                    adapter.addData(getData());
                    break;
                case 3://下拉刷新失败
                    refreshView.setRefreshType(CustomRefreshLayout.REFRESH_FAILURE);
                    adapter.getData().clear();
                    adapter.notifyDataSetChanged();
                    break;
                case 4://加载更多成功
                    refreshView.setRefreshType(CustomRefreshLayout.LOAD_MORE_SUCCESSFUL);
                    adapter.addData(adapter.getData().size(),getData());
                    break;
                case 5://加载更多成功 - 没有更多数据了
                    refreshView.setRefreshType(CustomRefreshLayout.LOAD_NO_MORE);
                    break;
                case 6://加载更多失败
                    refreshView.setRefreshType(CustomRefreshLayout.LOAD_MORE_FAILURE);
                    break;
            }
        }
    };

    private int refresh;
    private int loadMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        refreshView = findViewById(R.id.refreshView);

        RecyclerView recyclerView = refreshView.getRecyclerView();
        initRecyclerView(recyclerView);

        refreshView.setOnRefreshCallBack(new CustomRefreshLayout.OnRefreshCallBack() {
            @Override
            public void onRefresh() {//下拉刷新
                Log.d("aa", "onRefresh:" + refresh);
                if (refresh == 0) {
                    refresh = 1;
                    handler.sendEmptyMessageDelayed(2, 2000);//下拉刷新成功
                } else {
                    refresh = 0;
                    handler.sendEmptyMessageDelayed(3, 2000);//下拉刷新失败
                }
            }

            @Override
            public void onLoadMore() {//上拉加载更多
                switch (loadMore) {
                    case 0:
                        loadMore = 1;
                        handler.sendEmptyMessageDelayed(4, 2000);//加载更多成功
                        break;
                    case 1:
                        loadMore = 2;
                        handler.sendEmptyMessageDelayed(5, 2000);//加载更多成功 - 没有更多数据了
                        break;
                    case 2:
                        loadMore = 0;
                        handler.sendEmptyMessageDelayed(6, 2000);//加载更多失败
                        break;
                }
            }
        });

        initRefreshView();
    }

    private StandardAdapter<String> adapter;

    private void initRecyclerView(RecyclerView recyclerView) {
        adapter = new StandardAdapter<String>(R.layout.item_expandable_lv0, null) {
            @Override
            protected void convert(BaseViewHolder holder, String item) {
                holder.setText(R.id.tv_lv0_title, item);
            }
        };
        CustomItemDecoration itemDecoration = new CustomItemDecoration(this, CustomItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_line_dp2));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(itemDecoration);//添加分割线
        recyclerView.setAdapter(adapter);

        refreshView.setType(CustomRefreshLayout.LOADING);
        handler.sendEmptyMessageDelayed(0, 2000);
    }

    private int index;

    private List<String> getData() {
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            dataList.add("山上有 " + (index + i) + "和尚");
        }
        index += dataList.size();
        return dataList;
    }

    private void initRefreshView() {

    }
}
