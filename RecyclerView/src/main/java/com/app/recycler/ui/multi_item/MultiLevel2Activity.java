package com.app.recycler.ui.multi_item;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.recycler.R;
import com.app.recycler.adapter.StandardMultiTwoAdapter;
import com.app.recycler.entry.MultiLevelOneEntry;
import com.app.recycler.entry.MultiLevelTwoEntry;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import java.util.ArrayList;

public class MultiLevel2Activity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_level2);
        recyclerView = findViewById(R.id.recyclerView);

        initView();
    }

    private StandardMultiTwoAdapter<MultiLevelOneEntry, MultiLevelTwoEntry> adapter;

    private void initView() {
        adapter = new StandardMultiTwoAdapter<MultiLevelOneEntry, MultiLevelTwoEntry>(null) {
            @NonNull
            @Override
            public int[] getLayoutArray() {
                return new int[]{R.layout.item_expandable_lv0,R.layout.item_expandable_lv1};
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

        ArrayList<MultiItemEntity> res = new ArrayList<>();
        for (int i = 0; i < lv0Count; i++) {
            MultiLevelOneEntry entry0 = new MultiLevelOneEntry("This is " + i + "th item in Level 0", "subtitle of " + i);
            for (int j = 0; j < lv1Count; j++) {
                MultiLevelTwoEntry entry1 = new MultiLevelTwoEntry("Level 1 item: " + j, "(no animation)");
                entry0.addSubItem(entry1);
            }
            res.add(entry0);
        }
        res.add(new  MultiLevelOneEntry("This is " + lv0Count + "th item in Level 0", "subtitle of " + lv0Count));
        return res;
    }
}
