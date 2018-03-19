package com.app.recycler.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.recycler.R;
import com.app.recycler.adapter.StandardAdapter;
import com.app.recycler.anim.MyAnimation;
import com.app.recycler.view.CustomItemDecoration;
import com.app.recycler.view.CustomRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class AnimationActivity extends AppCompatActivity {

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_alpha://透明
                    adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                    break;
                case R.id.tv_Scale://缩放
                    adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
                    break;
                case R.id.tv_shake://抖动效果(自定义动画)
                    adapter.openLoadAnimation(new MyAnimation());
                    break;
                case R.id.tv_slide_left://条目左边弹出
                    adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                    break;
                case R.id.tv_slide_right://条目右边边弹出
                    adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
                    break;
                case R.id.tv_slide_bottom://条目底部弹出
                    adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        
        findViewById(R.id.tv_alpha).setOnClickListener(listener);
        findViewById(R.id.tv_Scale).setOnClickListener(listener);
        findViewById(R.id.tv_shake).setOnClickListener(listener);
        findViewById(R.id.tv_slide_left).setOnClickListener(listener);
        findViewById(R.id.tv_slide_right).setOnClickListener(listener);
        findViewById(R.id.tv_slide_bottom).setOnClickListener(listener);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        initRecyclerView(recyclerView);
    }

    private StandardAdapter<String> adapter;

    private void initRecyclerView(RecyclerView recyclerView) {
        adapter = new StandardAdapter<String>(R.layout.item_expandable_lv0, null) {
            @Override
            protected void convert(BaseViewHolder holder, String item) {
                holder.setText(R.id.tv_lv0_title, item);
            }
        };
        adapter.setNotDoAnimationCount(3);//设置当数据小于3时不展示动画效果
        adapter.isFirstOnly(false);
        CustomItemDecoration itemDecoration = new CustomItemDecoration(this, CustomItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_line_dp2));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(itemDecoration);//添加分割线
        recyclerView.setAdapter(adapter);

        adapter.addData(getData());
    }

    private int index;

    private List<String> getData() {
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            dataList.add("山上有 " + (index + i) + "和尚");
        }
        index += dataList.size();
        return dataList;
    }
}
