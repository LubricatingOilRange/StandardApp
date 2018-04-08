package com.app.standard.ui.activity.login;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.app.standard.R;
import com.app.standard.base.activity.dagger2.BaseDaggerActivity;
import com.app.standard.modle.rxjava.RxBus;
import com.app.standard.ui.activity.main.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class LoginActivity extends BaseDaggerActivity {

    @BindView(R.id.tv_accept_message)
    TextView tv_accept_message;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.tv_send)
    void onClick(TextView textView) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onActivityInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onInitPageAndData() {
    }
}
