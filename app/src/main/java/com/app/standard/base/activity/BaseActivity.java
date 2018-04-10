package com.app.standard.base.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.app.standard.base.impl.ActivityImpl;

import butterknife.ButterKnife;
import butterknife.Unbinder;

//无MVP的Activity基类
public abstract class BaseActivity extends AutoLayoutActivity implements ActivityImpl {

    public static final String BUNDLE_NAME = "afferentName";//activity页面传入的数据Bundle的name
    private Unbinder mUnBind;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mUnBind = ButterKnife.bind(this);
        onCreateInit();//在onCreate方法初始化页面
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBind.unbind();
    }

    //基本的页面跳转 ---------------------startActivity----------------------
    @Override
    public void gotoActivity(Class<? extends Activity> clazz) {
        startActivity(new Intent(this, clazz));
    }

    //基本的页面跳转 带参数
    @Override
    public void gotoActivity(Class<? extends Activity> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(BUNDLE_NAME, bundle);
        startActivity(intent);
    }

    //基本的页面跳转 ---------------------startActivityForResult--------------------------
    @Override
    public void gotoActivityForResult(Class<? extends Activity> clazz, int requestCode) {
        startActivityForResult(new Intent(this, clazz), requestCode);
    }

    @Override
    public void gotoActivityForResult(Class<? extends Activity> clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(BUNDLE_NAME, bundle);
        startActivityForResult(intent, requestCode);
    }
}
