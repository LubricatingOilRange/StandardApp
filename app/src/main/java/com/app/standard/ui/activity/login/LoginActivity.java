package com.app.standard.ui.activity.login;

import android.widget.TextView;

import com.app.standard.R;
import com.app.standard.base.activity.dagger2.BaseDaggerActivity;
import com.app.standard.component.annotation.Fruit;
import com.app.standard.component.annotation.FruitInject;
import com.app.standard.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseDaggerActivity {

    private Fruit fruit;

    @BindView(R.id.tv_accept_message)
    TextView tv_accept_message;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.tv_send)
    void onClick(TextView textView) {
//        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//        startActivity(intent);
//        finish();
//        fruit = new Fruit();
//        FruitInject.inject(fruit);
//        ToastUtil.showShort(LoginActivity.this,fruit.toString());
    }

    @Override
    protected void onActivityInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onInitPageAndData() {

    }
}
