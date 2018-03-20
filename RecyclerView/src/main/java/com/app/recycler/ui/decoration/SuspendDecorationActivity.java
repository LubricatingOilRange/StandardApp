package com.app.recycler.ui.decoration;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.app.recycler.R;
import com.app.recycler.adapter.StandardAdapter;
import com.app.recycler.entry.City;
import com.app.recycler.util.CityUtil;
import com.app.recycler.view.decoration.ColorItemDecoration;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SuspendDecorationActivity extends AppCompatActivity {

    List<City> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspend_decoration);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        initRecyclerView(recyclerView);
    }

    private StandardAdapter<City> adapter;

    private void initRecyclerView(RecyclerView recyclerView) {
        //模拟数据
        dataList.addAll(CityUtil.getCityList());
        dataList.addAll(CityUtil.getCityList());

        adapter = new StandardAdapter<City>(R.layout.item_expandable_lv0, null) {
            @Override
            protected void convert(BaseViewHolder holder, City item) {
                holder.setText(R.id.tv_lv0_title, item.getName());
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setClipToPadding(false);//不会绘制到Padding区域
        ColorItemDecoration itemDecoration = new ColorItemDecoration(this, 1,R.color.colorAccent,10);
        recyclerView.addItemDecoration(itemDecoration);//添加分割线
        recyclerView.setAdapter(adapter);

        adapter.addData(dataList);
    }

    private List<String> getData() {
        List<String> data = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            data.add("天上掉了" + i + "块馅饼");
        }
        return data;
    }
}
