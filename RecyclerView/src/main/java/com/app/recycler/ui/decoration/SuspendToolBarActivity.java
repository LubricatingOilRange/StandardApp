package com.app.recycler.ui.decoration;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.recycler.R;
import com.app.recycler.adapter.StandardAdapter;
import com.app.recycler.entry.Province;
import com.app.recycler.util.ProvinceUtil;
import com.app.recycler.view.CustomRefreshLayout;
import com.app.recycler.view.decoration.DrawableItemDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SuspendToolBarActivity extends AppCompatActivity {
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspend_tool_bar);
        recyclerView = findViewById(R.id.recyclerView);
        initRecyclerView(recyclerView);
    }

    private StandardAdapter<Province> mAdapter;
    private void initRecyclerView(RecyclerView recyclerView) {
        mAdapter = new StandardAdapter<Province>(R.layout.item_scroll_header, null) {
            @Override
            protected void convert(BaseViewHolder holder, Province item) {
                if (holder.getAdapterPosition() > 0) {
                    holder.setVisible(R.id.ll_suspend_header, true);
                } else {
                    holder.setVisible(R.id.ll_suspend_header, false);
                }
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
    }
    private final String[] colorArray = {"#CCCCCC", "#FF4081", "#303F9F", "#40ff69", "#df3b2f", "#13b7c2"};
    public String getRandomColor(int position) {
        return colorArray[position% colorArray.length];
    }
}
