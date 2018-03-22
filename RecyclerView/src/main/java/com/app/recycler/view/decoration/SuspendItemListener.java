package com.app.recycler.view.decoration;

/**
 * Created by ruibing.han on 2018/3/20.
 */

public interface SuspendItemListener {

    String getGroupName(int position);

    void onGroupClick(int position);
}
