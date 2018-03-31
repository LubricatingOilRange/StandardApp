package com.net.work;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.net.work.app.MyApplication;
import com.net.work.modle.dagger.component.ActivityComponent;
import com.net.work.modle.dagger.component.DaggerActivityComponent;
import com.net.work.modle.dagger.module.ActivityModule;
import com.net.work.modle.http.response.BaseResponse;
import com.net.work.modle.rxjava.RxUtil;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subscribers.ResourceSubscriber;

public class MainActivity extends AppCompatActivity {

    protected final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // getActivityComponent().inject(this);
        initView();
    }

    //将当前activity 添加到activityComponent容器中
    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(MyApplication.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    private void initView() {
        Flowable<BaseResponse<String>> login = MyApplication.getAppComponent().getRetrofitHelper().login(null);
        mCompositeDisposable.add(login
                .compose(RxUtil.<BaseResponse<String>>rxSchedulerHelper())
                .compose(RxUtil.<String>handleBaseResponseResult())
                .subscribeWith(new ResourceSubscriber<String>() {
                    @Override
                    public void onNext(String o) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

//    private void requestPermissions() {
//        rxPermissions
//                .requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.READ_CALENDAR,
//                        Manifest.permission.READ_CALL_LOG,
//                        Manifest.permission.READ_CONTACTS,
//                        Manifest.permission.READ_PHONE_STATE,
//                        Manifest.permission.READ_SMS,
//                        Manifest.permission.RECORD_AUDIO,
//                        Manifest.permission.CAMERA,
//                        Manifest.permission.CALL_PHONE,
//                        Manifest.permission.SEND_SMS)
//                .subscribe(new Consumer<Permission>() {
//                    @Override
//                    public void accept(Permission permission) throws Exception {
//                        if (permission.granted) {
//                            // 用户已经同意该权限
//                        } else if (permission.shouldShowRequestPermissionRationale) {
//                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
//                        } else {
//                            // 用户拒绝了该权限，并且选中『不再询问』
//                        }
//                    }
//                });
//
//
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}
