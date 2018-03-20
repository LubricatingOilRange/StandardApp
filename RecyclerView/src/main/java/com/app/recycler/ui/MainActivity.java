package com.app.recycler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.app.recycler.R;
import com.app.recycler.ui.anim.AnimationActivity;
import com.app.recycler.ui.multi_item.MultiLevel2Activity;
import com.app.recycler.ui.multi_item.MultiLevel3Activity;
import com.app.recycler.ui.refresh.RefreshActivity;
import com.app.recycler.ui.decoration.SuspendDecorationActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_two).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MultiLevel2Activity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.tv_three).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MultiLevel3Activity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.tv_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RefreshActivity.class);
                startActivity(intent);
            }
        });

        //RecyclerView条目展示动画效果
        findViewById(R.id.tv_item_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AnimationActivity.class);
                startActivity(intent);
            }
        });

        //RecyclerView实现顶部悬浮方式一:分割线
        findViewById(R.id.tv_suspend_decoration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SuspendDecorationActivity.class);
                startActivity(intent);
            }
        });

        //RecyclerView实现顶部悬浮方式一:分割线
        findViewById(R.id.tv_suspend_toolBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RefreshActivity.class);
                startActivity(intent);
            }
        });
    }

}
