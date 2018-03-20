package com.app.recycler.ui.multi_item;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.recycler.R;
import com.app.recycler.adapter.StandardMultiThreeAdapter;
import com.app.recycler.entry.MultiLevelOneEntry;
import com.app.recycler.entry.MultiLevelThreeEntry;
import com.app.recycler.entry.MultiLevelTwoEntry;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import java.util.ArrayList;
import java.util.Random;

public class MultiLevel3Activity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_level2);
        recyclerView = findViewById(R.id.recyclerView);

        initView();
    }

    private StandardMultiThreeAdapter<MultiLevelOneEntry, MultiLevelTwoEntry, MultiLevelThreeEntry> adapter;

    private void initView() {
        adapter = new StandardMultiThreeAdapter<MultiLevelOneEntry, MultiLevelTwoEntry, MultiLevelThreeEntry>(null) {
            @NonNull
            @Override
            public int[] getLayoutArray() {
                return new int[]{R.layout.item_expandable_lv0,R.layout.item_expandable_lv1,R.layout.item_expandable_lv2};
            }

            @Override
            public void convertLevelOne(final BaseViewHolder holder, final MultiLevelOneEntry entry) {
                holder.setText(R.id.tv_lv0_title, entry.getTitle());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = holder.getAdapterPosition();
                        if (entry.isExpanded()) {
                            collapse(position);
                        } else {
                            expand(position);
                        }
                    }
                });
            }

            @Override
            public void convertLevelTwo(final BaseViewHolder holder, final MultiLevelTwoEntry entry) {
                holder.setText(R.id.tv_lv1_title, entry.getTitle());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = holder.getAdapterPosition();
                        if (entry.isExpanded()) {
                            collapse(position);
                        } else {
                            expand(position);
                        }
                    }
                });
            }

            @Override
            public void convertLevelThree(BaseViewHolder holder, MultiLevelThreeEntry entry) {
                holder.setText(R.id.tv_lv2_title, entry.getTitle());
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ArrayList<MultiItemEntity> multiItemEntities = generateData();
        adapter.addData(multiItemEntities);
    }

    private ArrayList<MultiItemEntity> generateData() {
        int lv0Count = 5;
        int lv1Count = 3;
        int personCount = 5;

        String[] nameList = {"鸡蛋", "鸭蛋", "鹅蛋", "狗蛋", "傻蛋"};
        Random random = new Random();

        ArrayList<MultiItemEntity> res = new ArrayList<>();
        for (int i = 0; i < lv0Count; i++) {
            MultiLevelOneEntry entry0 = new MultiLevelOneEntry("level--" + i, "subtitle of " + i);
            for (int j = 0; j < lv1Count; j++) {
                MultiLevelTwoEntry entry1 = new MultiLevelTwoEntry("level--" + i + "  item--" + j, "(no animation)");
                for (int k = 0; k < personCount; k++) {
                    entry1.addSubItem(new MultiLevelThreeEntry(nameList[k]));
                }
                entry0.addSubItem(entry1);
            }
            res.add(entry0);
        }
        res.add(new  MultiLevelOneEntry("Come Over", " 你挂了"  ));
        return res;
    }
}
