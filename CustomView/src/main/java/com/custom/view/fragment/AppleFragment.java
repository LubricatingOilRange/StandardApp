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
import android.widget.LinearLayout;

import com.custom.view.R;
import com.custom.view.util.Logger;
import com.custom.view.view.ScrollerView;

public class AppleFragment extends Fragment {

    public AppleFragment() {
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logger.i("setUserVisibleHint  ---- 111");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Logger.i("onAttach             ---- 111");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.i("onCreate             ---- 111");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logger.i("onCreateView          ---- 111");
        View view = inflater.inflate(R.layout.frag_apple, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
       final ScrollerView scrollerView =  view.findViewById(R.id.scrollView);
       final LinearLayout ll_layout =  view.findViewById(R.id.ll_layout);
//        ll_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                scrollerView.startScroll();
//            }
//        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.i("onViewCreated          ---- 111");
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.i("onStart                 ---- 111");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.i("onPause                 ---- 111");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.i("onResume                ---- 111");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.i("onStop                  ---- 111");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.i("onDestroyView            ---- 111");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.i("onDestroy                ---- 111");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.i("onDetach                ---- 111");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Logger.i("onHiddenChanged         ---- 111");
    }
}
