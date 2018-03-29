package com.app.standard.ui.activity.login;

import android.content.Intent;
import android.widget.TextView;

import com.app.standard.R;
import com.app.standard.app.MyApplication;
import com.app.standard.base.activity.BaseActivity;
import com.app.standard.modle.helper.RetrofitHelper;
import com.app.standard.modle.rxjava.RxBus;
import com.app.standard.ui.activity.main.MainActivity;
import com.app.standard.ui.view.custom.CustomToast;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class LoginActivity extends BaseActivity {

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
    }

    @Override
    public void onCreateInit() {

        RxBus.getDefault().toDefaultFlowAble(Object.class, new Consumer<Object>() {
            @Override
            public void accept(Object s) throws Exception {
                if (s instanceof String) {
                    tv_accept_message.setText((String) s);
                } else if (s instanceof Boolean) {
                    tv_accept_message.setText(String.valueOf(s));
                }
            }
        });
    }
}
