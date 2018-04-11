package com.custom.view.bean;

//  Created by ruibing.han on 2018/4/11.

import android.support.v4.app.Fragment;

public class FragmentTitleBean {
    private String title;

    private Fragment fragment;

    public FragmentTitleBean(Fragment fragment, String title) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
