package com.app.recycler.ui.section;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.recycler.R;
import com.app.recycler.adapter.StandardSectionAdapter;
import com.app.recycler.entry.City;
import com.app.recycler.entry.SectionItemEntry;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionSuspendActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayout ll_header;
    private TextView tv_header;

    private int mSuspendHeight;
    private int mCurrentPosition;
    private GridLayoutManager manager;
    private StandardSectionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_suspend);

        recyclerView = findViewById(R.id.recyclerView);
        ll_header = findViewById(R.id.ll_header);

        tv_header = findViewById(R.id.tv_header);

        initView();
    }

    private void initView() {

        final List<SectionItemEntry> sectionData = getSectionData();

        tv_header.setText(sectionData.get(0).header);

        adapter = new StandardSectionAdapter(R.layout.item_type_1, R.layout.item_section_header, sectionData);

        manager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        //添加条目点击事件
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SectionItemEntry item = (SectionItemEntry) adapter.getItem(position);
                if (item != null) {
                    if (item.isHeader) {
                        Toast.makeText(SectionSuspendActivity.this, item.header, Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(SectionSuspendActivity.this, item.t.getName(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(SectionSuspendActivity.this, "加载更多：" + position, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.b_item_type:
                        Toast.makeText(SectionSuspendActivity.this, "点我：" + position, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mSuspendHeight = ll_header.getHeight();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View view = manager.findViewByPosition(getNextHeaderPosition(adapter.getData().size(), mCurrentPosition));
                if (view != null) {
                    if (view.getTop() <= mSuspendHeight) {
                        ll_header.setY(view.getTop() - mSuspendHeight);
                    } else {
                        ll_header.setY(0);
                    }
                }

                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
                if (adapter.getData().get(firstVisibleItemPosition).isHeader) {

                    if (mCurrentPosition != firstVisibleItemPosition) {
                        mCurrentPosition = firstVisibleItemPosition;
                        ll_header.setY(0);
                        tv_header.setText(adapter.getData().get(firstVisibleItemPosition).header);
                    }
                }


            }
        });
    }

    private int getNextHeaderPosition(int childCount, int currentPosition) {
        currentPosition++;
        if (currentPosition <= childCount) {
            SectionItemEntry sectionItemEntry = adapter.getData().get(currentPosition);
            if (!sectionItemEntry.isHeader) {
                return getNextHeaderPosition(childCount, currentPosition);
            } else {
                return currentPosition;
            }
        }
        return -1;
    }

    private List<SectionItemEntry> getSectionData() {
        List<SectionItemEntry> data = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
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

            data.add(new SectionItemEntry(R.mipmap.ic_launcher, true, "福建省", false));
            data.add(new SectionItemEntry(new City("福州", "", 0)));
            data.add(new SectionItemEntry(new City("厦门", "", 0)));
            data.add(new SectionItemEntry(new City("泉州", "", 0)));
            data.add(new SectionItemEntry(new City("宁德", "", 0)));

            data.add(new SectionItemEntry(R.mipmap.ic_launcher, true, "安徽省", false));
            data.add(new SectionItemEntry(new City("合肥", "", 0)));
            data.add(new SectionItemEntry(new City("芜湖", "", 0)));
        }

        return data;
    }
}
