package com.app.standard.base.mvp;

//  Created by ruibing.han on 2018/3/29.

import com.app.standard.modle.http.exception.AppException;

public interface BaseView {

    void showErrorMsg(AppException e);
}
