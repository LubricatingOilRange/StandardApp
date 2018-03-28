package com.app.standard.Base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.standard.impl.FragmentImpl;

import butterknife.ButterKnife;
import butterknife.Unbinder;

//无MVP的Fragment基类
public abstract class BaseFragment<A extends Activity> extends Fragment implements FragmentImpl {

    protected A mActivity;
    private Unbinder mUnBinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (A) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);//获取布局Id
        mUnBinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewCreatedInit();//在onViewCreated方法中可进行布局的初始化操作
    }

    @Override
    public void onStart() {
        super.onStart();
        onStartInit();//在onStart方法中可进行网络请求操作
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }
}
