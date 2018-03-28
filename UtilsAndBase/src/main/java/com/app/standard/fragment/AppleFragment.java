package com.app.standard.fragment;

import android.view.View;
import android.widget.TextView;

import com.app.standard.Base.BaseFragment;
import com.app.standard.MainActivity;
import com.app.standard.R;

import butterknife.BindView;

public class AppleFragment extends BaseFragment<MainActivity> {
    @BindView(R.id.tv_apple)
    TextView tv_apple;
    @Override
    public int getLayoutId() {
        return R.layout.frag_apple;
    }

    @Override
    public void onViewCreatedInit() {
        tv_apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.replaceFragment("BananaFragment",
                        true,true,false);
            }
        });
    }

    @Override
    public void onStartInit() {

    }
}
