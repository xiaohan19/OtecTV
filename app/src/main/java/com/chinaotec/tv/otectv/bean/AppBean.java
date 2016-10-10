package com.chinaotec.tv.otectv.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class AppBean {
    public String packgeName;
    public Drawable appIcon;
    public String appName;
    public AppBean() {
        super();
    }

    public AppBean(String packgeName,Drawable appIcon,String appName) {
        super();
        this.packgeName = packgeName;
        this.appIcon = appIcon;
        this.appName = appName;
    }
}
