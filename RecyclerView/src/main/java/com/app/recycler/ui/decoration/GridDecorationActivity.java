package com.app.recycler.ui.decoration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.app.recycler.R;
import com.app.recycler.adapter.StandardAdapter;
import com.app.recycler.entry.City;
import com.app.recycler.util.CityUtil;
import com.app.recycler.view.decoration.GridItemDecoration;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

//网格布局分割线
public class GridDecorationActivity extends AppCompatActivity {
    List<City> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_decoration);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        initRecyclerView(recyclerView);
    }

    private StandardAdapter<City> adapter;

    private void initRecyclerView(RecyclerView recyclerView) {
        //模拟数据
        adapter = new StandardAdapter<City>(R.layout.item_type_1, null) {
            @Override
            protected void convert(BaseViewHolder holder, City item) {
                holder.setText(R.id.tv_item_type, item.getName());
            }
        };

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);

        GridItemDecoration itemDecoration = new GridItemDecoration(this,3);
        itemDecoration.setDrawable(R.drawable.shape_line_grid_dp2);
        recyclerView.addItemDecoration(itemDecoration);//添加分割线
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        adapter.addData(getData());
    }

    private List<City> getData() {
        List<City> dataList = new ArrayList<>();
        dataList.add(new City("福州", "", 0));
        dataList.add(new City("福州", "", 0));
        dataList.add(new City("福州", "", 0));

        dataList.add(new City("福州", "", 0));
        dataList.add(new City("福州", "", 0));
        dataList.add(new City("福州", "", 0));

        dataList.add(new City("福州", "", 0));
        dataList.add(new City("福州", "", 0));
        return dataList;
    }
}
