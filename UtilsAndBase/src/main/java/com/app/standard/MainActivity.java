package com.app.standard;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.app.standard.Base.BaseFragment;
import com.app.standard.Base.BaseFragmentActivity;
import com.app.standard.fragment.AppleFragment;
import com.app.standard.fragment.BananaFragment;
import com.app.standard.fragment.OrangeFragment;
import com.app.standard.fragment.PearFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseFragmentActivity {

    @BindView(R.id.tv_replace)
    TextView tv_replace;
    @BindView(R.id.tv_add)
    TextView tv_add;

    private int type = 1;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getContainerViewId() {
        return R.id.fl_content;
    }

    @OnClick({ R.id.tv_replace, R.id.tv_add })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_replace:
                type = 1;
                break;
            case R.id.tv_add:
                type = 0;
                break;
        }
    }

    @Override
    public void onCreateInit() {
        replaceFragment("AppleFragment", true, false, false);
    }




    /**
     * @param nextFragTag Fragment的类名 如"BaseFragment","BaseActivity"
     * @return 返回类名对应得对象
     */
    @NonNull
    @Override
    public BaseFragment getNextFragment(String nextFragTag) {
        BaseFragment fragment;
        switch (nextFragTag) {
            case "AppleFragment":
                fragment = new AppleFragment();
                break;

            case "BananaFragment":
                fragment = new BananaFragment();
                break;

            case "OrangeFragment":
                fragment = new OrangeFragment();
                break;

            case "PearFragment":
                fragment = new PearFragment();
                break;
            default:
                fragment = new AppleFragment();
                break;
        }
        return fragment;
    }

    @Override
    public void onBackPressed() {
        if (type == 0) {
            super.onBackPressed();
        } else {
            onFragmentBackPressed("BananaFragment", 1);
        }
    }
}
