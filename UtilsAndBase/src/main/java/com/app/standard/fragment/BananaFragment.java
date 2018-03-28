package com.app.standard.fragment;

import android.view.View;
import android.widget.TextView;

import com.app.standard.Base.BaseFragment;
import com.app.standard.MainActivity;
import com.app.standard.R;

import butterknife.BindView;


public class BananaFragment extends BaseFragment<MainActivity> {
    @BindView(R.id.tv_banana)
    TextView tv_banana;
    @Override
    public int getLayoutId() {
        return R.layout.frag_banana;
    }

    @Override
    public void onViewCreatedInit() {
        tv_banana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.replaceFragment("OrangeFragment",true,true,false);
            }
        });
    }

    @Override
    public void onStartInit() {

    }
}
