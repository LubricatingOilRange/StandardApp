package com.app.recycler.ui.section;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.app.recycler.R;
import com.app.recycler.adapter.StandardSectionAdapter;
import com.app.recycler.entry.City;
import com.app.recycler.entry.SectionItemEntry;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);

        recyclerView = findViewById(R.id.recyclerView);

        initView();
    }

    private void initView() {

        List<SectionItemEntry> sectionData = getSectionData();

        StandardSectionAdapter adapter = new StandardSectionAdapter(R.layout.item_type_1, R.layout.item_section_header, sectionData);

        final GridLayoutManager manager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        //添加条目点击事件
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SectionItemEntry item = (SectionItemEntry) adapter.getItem(position);
                if (item != null) {
                    if (item.isHeader) {
                        Toast.makeText(SectionActivity.this, item.header, Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(SectionActivity.this, item.t.getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        // 添加子条目点击事件

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_more:
                        Toast.makeText(SectionActivity.this, "加载更多：" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.b_item_type:
                        Toast.makeText(SectionActivity.this, "点我：" + position, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private List<SectionItemEntry> getSectionData() {
        List<SectionItemEntry> data = new ArrayList<>();
        data.add(new SectionItemEntry(R.mipmap.ic_launcher, true, "福建省", true));
        data.add(new SectionItemEntry(new City("福州", "", 0)));
        data.add(new SectionItemEntry(new City("厦门", "", 0)));
        data.add(new SectionItemEntry(new City("泉州", "", 0)));
        data.add(new SectionItemEntry(new City("宁德", "", 0)));

        data.add(new SectionItemEntry(R.mipmap.ic_launcher, true, "安徽省", false));
        data.add(new SectionItemEntry(new City("合肥", "", 0)));
        data.add(new SectionItemEntry(new City("芜湖", "", 0)));

        data.add(new SectionItemEntry(R.mipmap.ic_launcher, true, "浙江省", true));
        data.add(new SectionItemEntry(new City("杭州", "", 0)));
        data.add(new SectionItemEntry(new City("宁波", "", 0)));
        data.add(new SectionItemEntry(new City("温州", "", 0)));

        data.add(new SectionItemEntry(R.mipmap.ic_launcher, true, "江苏省", false));
        data.add(new SectionItemEntry(new City("南京", "", 0)));
        data.add(new SectionItemEntry(new City("苏州", "", 0)));

        data.add(new SectionItemEntry(R.mipmap.ic_launcher, true, "福建省", false));
        data.add(new SectionItemEntry(new City("福州", "", 0)));
        data.add(new SectionItemEntry(new City("厦门", "", 0)));
        data.add(new SectionItemEntry(new City("泉州", "", 0)));
        data.add(new SectionItemEntry(new City("宁德", "", 0)));

        data.add(new SectionItemEntry(R.mipmap.ic_launcher, true, "安徽省-2", true));
        data.add(new SectionItemEntry(new City("合肥 - 2", "", 0)));

        return data;
    }
}
