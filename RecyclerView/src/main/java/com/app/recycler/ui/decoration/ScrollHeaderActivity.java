package com.app.recycler.ui.decoration;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.recycler.R;
import com.app.recycler.adapter.StandardAdapter;
import com.app.recycler.entry.City;
import com.app.recycler.entry.Province;
import com.app.recycler.util.ProvinceUtil;
import com.app.recycler.view.decoration.DrawableItemDecoration;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrollHeaderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView tv_suspend;
    private LinearLayout ll_suspend_header;

    private int mSuspendHeight;//悬浮栏的高度

    private int mCurrentPosition;//页面上当前显示的第一个的索引
    private LinearLayoutManager mLinearLayoutManager;
    private int randomColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_header);

        recyclerView = findViewById(R.id.recyclerView);
        tv_suspend = findViewById(R.id.tv_suspend);//悬浮栏文本
        ll_suspend_header = findViewById(R.id.ll_suspend_header);//悬浮栏文本

        initRecyclerView(recyclerView);//初始化RecyclerView
    }

    private StandardAdapter<Province> mAdapter;
    private void initRecyclerView(RecyclerView recyclerView) {
        mAdapter = new StandardAdapter<Province>(R.layout.item_scroll_header, null) {
            @Override
            protected void convert(BaseViewHolder holder, Province item) {

                holder.setText(R.id.tv_suspend, item.getProvinceName());
                holder.setBackgroundRes(R.id.iv_suspend, item.getIcon());
                holder.setBackgroundColor(R.id.ll_suspend_header, Color.parseColor(getRandomColor(holder.getAdapterPosition())));

            }
        };
        mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        ;
        recyclerView.setAdapter(mAdapter);

        mAdapter.addData(ProvinceUtil.getProvinceList());
        mAdapter.addData(ProvinceUtil.getProvinceList());

        tv_suspend.setText(mAdapter.getData().get(0).getProvinceName());

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mSuspendHeight = ll_suspend_header.getHeight();
                Log.d("height", "mSuspendHeight:" + mSuspendHeight);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                View view = mLinearLayoutManager.findViewByPosition(mCurrentPosition + 1);//获取当前页面第二个条目的View
                if (view != null) {
                    if (view.getTop() <= mSuspendHeight) {
                        ll_suspend_header.setY(-(mSuspendHeight - view.getTop()));
                    } else {
                        ll_suspend_header.setY(0);
                    }
                }

                int firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
                if (mCurrentPosition  != firstVisibleItemPosition) {
                    //当条目刚好滑出去了，回到初始位置
                    mCurrentPosition = firstVisibleItemPosition;
                    ll_suspend_header.setY(0);
                    tv_suspend.setText(mAdapter.getData().get(mCurrentPosition).getProvinceName());//更新悬浮栏的文本
                    ll_suspend_header.setBackgroundColor(Color.parseColor(getRandomColor(mCurrentPosition)));
                }
            }
        });
    }
    private final String[] colorArray = {"#CCCCCC", "#FF4081", "#303F9F", "#40ff69", "#df3b2f", "#13b7c2"};
    public String getRandomColor(int position) {
        return colorArray[position% colorArray.length];
    }
}
