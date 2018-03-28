package com.app.standard.impl;

//  Created by ruibing.han on 2018/3/28.

import android.app.Activity;
import android.os.Bundle;

public interface ActivityImpl {

    int getLayoutId();//获取布局ID

    void onCreateInit();//在onCreate方法初始化页面

    //基本的页面跳转 ---------------------startActivity----------------------
    void gotoActivity(Class<? extends Activity> clazz);

    //基本的页面跳转 带参数
    void gotoActivity(Class<? extends Activity> clazz, Bundle bundle);

    //带返回的页面跳转 ---------------------startActivityForResult--------------------------
    void gotoActivityForResult(Class<? extends Activity> clazz, int requestCode);

    void gotoActivityForResult(Class<? extends Activity> clazz, Bundle bundle, int requestCode);
}
