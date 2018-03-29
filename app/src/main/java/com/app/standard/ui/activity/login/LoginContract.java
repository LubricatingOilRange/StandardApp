package com.app.standard.ui.activity.login;

//  Created by ruibing.han on 2018/3/29.

import com.app.standard.base.mvp.BasePresenter;
import com.app.standard.base.mvp.BaseView;

public interface LoginContract {

    interface View extends BaseView {

        void showData();
    }

    interface LoginPresenter extends BasePresenter<View> {

        void getData();
    }
}
