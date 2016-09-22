package com.chinaotec.tv.otectv;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by linhao on 2016/9/21.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
