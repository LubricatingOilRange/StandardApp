package com.app.standard.fragment;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.standard.Base.BaseFragment;
import com.app.standard.MainActivity;
import com.app.standard.R;

import butterknife.BindView;

public class PearFragment extends BaseFragment<MainActivity> {
    @BindView(R.id.tv_pear)
    TextView tv_pear;

    @Override
    public int getLayoutId() {
        return R.layout.frag_pear;
    }

    @Override
    public void onViewCreatedInit() {
        tv_pear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mActivity,"欢迎来吃香梨:" ,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStartInit() {

    }

}
