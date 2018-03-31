package com.net.work.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//无MVP的Fragment基类
public abstract class BaseFragment<A extends Activity> extends Fragment {

    protected A mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (A) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);//获取布局Id
        return view;
    }

    protected abstract int getLayoutId();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewCreatedInit();//在onViewCreated方法中可进行布局的初始化操作
    }

    protected abstract void onViewCreatedInit();

    @Override
    public void onStart() {
        super.onStart();
        onStartInit();//在onStart方法中可进行网络请求操作
    }

    protected abstract void onStartInit();
}
