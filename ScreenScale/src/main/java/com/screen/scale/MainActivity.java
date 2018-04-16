package com.screen.scale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScreenUtil.getInstance().init(this);
        ScreenUtil.getInstance().scaleViewSize(findViewById(R.id.content));
    }
}
