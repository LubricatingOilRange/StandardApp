package com.app.recycler.view.decoration;

public interface SuspendItemListener {

    String getGroupName(int position);

    void onGroupClick(int position);
}
