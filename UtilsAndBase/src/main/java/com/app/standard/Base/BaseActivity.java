package com.app.standard.Base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

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

    protected abstract int getLayoutId();//获取布局ID

    protected abstract void onCreateInit();//在onCreate方法初始化页面

    //基本的页面跳转 ---------------------startActivity----------------------
    protected void gotoActivity(Class<? extends Activity> clazz) {
        startActivity(new Intent(this, clazz));
    }

    protected void gotoActivityForResult(Class<? extends Activity> clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(BUNDLE_NAME, bundle);
        startActivityForResult(intent, requestCode);
    }

    //基本的页面跳转 ---------------------startActivityForResult--------------------------
    protected void gotoActivityForResult(Class<? extends Activity> clazz, int requestCode) {
        startActivityForResult(new Intent(this, clazz), requestCode);
    }

    //基本的页面跳转 带参数
    protected void gotoActivity(Class<? extends Activity> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(BUNDLE_NAME, bundle);
        startActivity(intent);
    }
}
