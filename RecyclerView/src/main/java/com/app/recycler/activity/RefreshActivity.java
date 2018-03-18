package com.app.recycler.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.app.recycler.R;
import com.app.recycler.view.MySwipeRefreshLayout;
import com.app.recycler.view.RefreshView;

public class RefreshActivity extends AppCompatActivity {

    private RefreshView refreshView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        refreshView = findViewById(R.id.refreshView);

        RecyclerView recyclerView = refreshView.getRecyclerView();
        initRecyclerView(recyclerView);

        refreshView.setOnRefreshCallBack(new MySwipeRefreshLayout.OnRefreshCallBack() {
            @Override
            public void onRefresh() {//下拉刷新

            }

            @Override
            public void onLoadMore() {//上拉加载更多

            }
        });

        initRefreshView();
    }

    private void initRecyclerView(RecyclerView recyclerView) {

    }

    private void initRefreshView() {

    }
}
