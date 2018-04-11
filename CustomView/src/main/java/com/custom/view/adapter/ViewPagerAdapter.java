package com.custom.view.adapter;

//  Created by ruibing.han on 2018/4/11.

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.custom.view.bean.FragmentTitleBean;

import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<FragmentTitleBean> mData;

    public ViewPagerAdapter(FragmentManager fm, @NonNull List<FragmentTitleBean> data) {
        super(fm);
        this.mData = data;
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mData.get(position).getTitle();
    }
}
