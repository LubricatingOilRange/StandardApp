package com.app.recycler.adapter;

//  Created by ruibing.han on 2018/4/3.

import com.app.recycler.R;
import com.app.recycler.entry.City;
import com.app.recycler.entry.SectionItemEntry;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class StandardSectionAdapter extends BaseSectionQuickAdapter<SectionItemEntry, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public StandardSectionAdapter(int layoutResId, int sectionHeadResId, List<SectionItemEntry> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SectionItemEntry item) {
        helper
                .setVisible(R.id.tv_more, item.isMore())
                .setVisible(R.id.tv_header, item.isHeader)
                .setText(R.id.tv_header, item.header);
        helper.addOnClickListener(R.id.tv_more);//添加点击事件
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionItemEntry item) {
        City city = item.t;

        helper.setText(R.id.tv_item_type, city.getName());
        helper.addOnClickListener(R.id.b_item_type);//添加点击事件
    }
}
