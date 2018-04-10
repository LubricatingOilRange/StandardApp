package com.optimize.launcher;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewStub;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private HandlerThread mHandlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewStub viewStub = findViewById(R.id.viewStub);
        viewStub.inflate();

        mHandlerThread = new HandlerThread("test");
        mHandlerThread.setPriority(1);
        mHandlerThread.start();

       final Handler handler = new Handler(mHandlerThread.getLooper(), new Handler.Callback() {
           //该方法运行在子线程
           @Override
           public boolean handleMessage(Message message) {
               return false;
           }
       });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandlerThread.quit();
    }
}
