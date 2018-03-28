package com.app.standard.fragment;

import android.view.View;
import android.widget.TextView;

import com.app.standard.Base.BaseFragment;
import com.app.standard.MainActivity;
import com.app.standard.R;

import butterknife.BindView;

public class OrangeFragment extends BaseFragment<MainActivity> {
    @BindView(R.id.tv_orange)
    TextView tv_orange;
    @Override
    public int getLayoutId() {
        return R.layout.frag_orange;
    }

    @Override
    public void onViewCreatedInit() {
        tv_orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.replaceFragment("PearFragment",
                        true,true,false);
            }
        });
    }

    @Override
    public void onStartInit() {

    }

}
