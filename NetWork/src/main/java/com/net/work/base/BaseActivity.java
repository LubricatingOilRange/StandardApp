package com.net.work.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

//无MVP的Activity基类
public abstract class BaseActivity extends AppCompatActivity {

    public static final String BUNDLE_NAME = "afferentName";//activity页面传入的数据Bundle的name

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        onCreateInit();//在onCreate方法初始化页面
    }

    protected abstract void onCreateInit();

    protected abstract int getLayoutId();
}
