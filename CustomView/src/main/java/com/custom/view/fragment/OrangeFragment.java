package com.custom.view.fragment;

//  Created by ruibing.han on 2018/4/11.

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.custom.view.R;
import com.custom.view.util.Logger;

public class OrangeFragment extends Fragment {
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logger.i("setUserVisibleHint  ---- 333");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Logger.i("onAttach             ---- 333");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i("onCreate             ---- 333");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.i("onCreateView          ---- 333");
        return inflater.inflate(R.layout.frag_apple,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.i("onViewCreated          ---- 333");
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.i("onStart                 ---- 333");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.i("onPause                 ---- 333");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.i("onResume                ---- 333");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.i("onStop                  ---- 333");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.i("onDestroyView            ---- 333");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.i("onDestroy                ---- 333");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.i("onDetach                ---- 333");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Logger.i("onHiddenChanged         ---- 333");
    }
}
