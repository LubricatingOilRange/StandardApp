package com.app.recycler.entry;

//  Created by ruibing.han on 2018/4/3.

import com.chad.library.adapter.base.entity.SectionEntity;

public class SectionItemEntry extends SectionEntity<City> {
    private int icon;//头部图片
    private boolean isMore;//是否显示更多

    public SectionItemEntry(int icon, boolean isHeader, String header, boolean isMore) {
        super(isHeader, header);
        this.icon = icon;
        this.isMore = isMore;
    }

    public SectionItemEntry(City city, int icon, boolean isMore) {
        super(city);
        this.icon = icon;
        this.isMore = isMore;
    }

    public SectionItemEntry(City t) {
        super(t);
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean mroe) {
        isMore = mroe;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
