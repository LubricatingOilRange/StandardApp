package com.custom.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewConfiguration;
import android.widget.Toast;

import com.custom.view.adapter.ViewPagerAdapter;
import com.custom.view.bean.FragmentTitleBean;
import com.custom.view.fragment.AppleFragment;
import com.custom.view.fragment.BananaFragment;
import com.custom.view.fragment.OrangeFragment;
import com.custom.view.fragment.PearFragment;
import com.custom.view.util.Logger;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<FragmentTitleBean> mFragList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // mTabLayout = findViewById(R.id.tabLayout);
       // mViewPager = findViewById(R.id.viewPager);

        //intView();
    }

    private void intView() {
        mFragList.add(new FragmentTitleBean(new AppleFragment(), "苹果"));
        mFragList.add(new FragmentTitleBean(new BananaFragment(), "香蕉"));
        mFragList.add(new FragmentTitleBean(new OrangeFragment(), "橘子"));
        mFragList.add(new FragmentTitleBean(new PearFragment(), "香梨"));

      final  ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragList);
      //  mViewPager.setAdapter(adapter);
       // mTabLayout.setupWithViewPager(mViewPager);
    }
}
