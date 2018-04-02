package com.app.recycler.ui.complex;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.app.recycler.R;
import com.app.recycler.adapter.StandardComplexAdapter;
import com.app.recycler.entry.ComplexItem;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

//条目复杂布局
public class ComplexItemActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_item);
        recyclerView = findViewById(R.id.recyclerView);

        intView();
    }

    private StandardComplexAdapter adapter;
    private void intView() {
        adapter = new StandardComplexAdapter(getMultipleItemData()) {
            @NonNull
            @Override
            protected int[] getLayoutArray() {
                return new int[]{R.layout.item_type_1,R.layout.item_type_2,R.layout.item_type_3,R.layout.item_type_4,R.layout.item_type_5};
            }

            @Override
            protected void convert(BaseViewHolder helper, ComplexItem item) {
                switch (helper.getItemViewType()) {
                    case ComplexItem.ITEM_TYPE_1:
                        helper.setText(R.id.tv_item_type, item.getContent());
                        break;
                    case ComplexItem.ITEM_TYPE_2:
                        helper.setText(R.id.tv_item_type, item.getContent());
                        break;
                    case ComplexItem.ITEM_TYPE_3:
                        helper.setText(R.id.tv_item_type, item.getContent());
                        break;
                    case ComplexItem.ITEM_TYPE_4:
                        helper.setText(R.id.tv_item_type, item.getContent());
                        break;
                    case ComplexItem.ITEM_TYPE_5:
                        switch (item.getSpanSize()) {
                            case ComplexItem.SPAN_SIZE_3:
                                helper.setText(R.id.tv_item_type, item.getContent());
                                break;
                            case ComplexItem.SPAN_SIZE_1:
                                helper.setText(R.id.tv_item_type, item.getContent());
                                break;
                        }
                        break;
                }
            }
        };

        final GridLayoutManager manager = new GridLayoutManager(this, 4);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this,R.drawable.shape_line_dp2));
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(itemDecoration);
        adapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return adapter.getData().get(position).getSpanSize();
            }
        });
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ComplexItem complexItem = (ComplexItem) adapter.getData().get(position);
                Toast.makeText(ComplexItemActivity.this,complexItem.getContent(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static List<ComplexItem> getMultipleItemData() {
        List<ComplexItem> list = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            list.add(new ComplexItem(ComplexItem.ITEM_TYPE_1, ComplexItem.SPAN_SIZE_1,"ITEM_TYPE_1---" + i));
            list.add(new ComplexItem(ComplexItem.ITEM_TYPE_2, ComplexItem.SPAN_SIZE_2,"ITEM_TYPE_2---" + i));
            list.add(new ComplexItem(ComplexItem.ITEM_TYPE_3, ComplexItem.SPAN_SIZE_1,"ITEM_TYPE_3---" + i));
            list.add(new ComplexItem(ComplexItem.ITEM_TYPE_4, ComplexItem.SPAN_SIZE_4,"ITEM_TYPE_4---" + i));
            list.add(new ComplexItem(ComplexItem.ITEM_TYPE_5, ComplexItem.SPAN_SIZE_3,"ITEM_TYPE_5---" + i));
            list.add(new ComplexItem(ComplexItem.ITEM_TYPE_5, ComplexItem.SPAN_SIZE_1,"ITEM_TYPE_5---" + i));
        }
        return list;
    }
}
