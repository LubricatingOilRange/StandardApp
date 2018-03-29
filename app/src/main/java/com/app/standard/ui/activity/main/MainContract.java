package com.app.standard.ui.activity.main;

//  Created by ruibing.han on 2018/3/29.

import com.app.standard.base.mvp.BasePresenter;
import com.app.standard.base.mvp.BaseView;

public interface MainContract {

    interface View extends BaseView {

       void showData();//展示网络获取的数据
    }

    interface Presenter extends BasePresenter<View> {

        void getData();//获取网络数据
    }
}
