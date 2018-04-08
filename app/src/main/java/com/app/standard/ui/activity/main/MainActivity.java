package com.app.standard.ui.activity.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.standard.R;
import com.app.standard.base.activity.mvp.BaseMvpActivity;
import com.app.standard.modle.helper.ExceptionHelper;
import com.app.standard.modle.http.exception.AppException;
import com.app.standard.ui.view.custom.CustomToast;
import com.app.standard.ui.view.recycler.adapter.StandardAdapter;
import com.app.standard.ui.view.recycler.decoration.ColorItemDecoration;
import com.app.standard.ui.view.recycler.manager.CustomLayoutManager;
import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.frag_recyclerView)
    RecyclerView frag_recyclerView;

    private StandardAdapter<String> adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onCreateInitPageAndData() {
        adapter = new StandardAdapter<String>(R.layout.item_main, null) {
            @Override
            protected void convert(BaseViewHolder holder, String item) {
                holder.setText(R.id.tv_content, item);
            }
        };
        CustomLayoutManager manager = new CustomLayoutManager(CustomLayoutManager.HORIZONTAL);
        frag_recyclerView.setLayoutManager(manager);
        frag_recyclerView.setAdapter(adapter);

        for (int i = 0; i < 20; i++) {
            adapter.getData().add("aaa---" + i);
        }
        adapter.notifyDataSetChanged();
    }

    //展示网络获取成功后的数据
    @Override
    public void showData() {

    }

    //展示网络获取失败的错误信息
    @Override
    public void showErrorMsg(AppException e) {
        ExceptionHelper.handleException(this,e);
    }

    @Override
    public void onBackPressed() {
        if (isTaskRoot()) {//判断是否是根
            moveTaskToBack(false);//将app 移动到后台  不关闭主页面，等再次打开app,实现暖启动
        } else {
            super.onBackPressed();
        }
    }
}
