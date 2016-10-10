package com.chinaotec.tv.otectv.util;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.chinaotec.tv.otectv.bean.AppBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/10 0010.
 */
public class GetSysUtil {
    //获取当前系统的app信息
    public static List<AppBean> getAllAppInfo(Activity act) {
        List<AppBean> appList = new ArrayList<AppBean>();
        PackageManager packageManager = act.getPackageManager();
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        for (PackageInfo packageInfo : packageInfoList) {
            AppBean appBean = new AppBean();
            // 设置包名
            appBean.packgeName = packageInfo.packageName;
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            // 获得app图标
            appBean.appIcon = applicationInfo.loadIcon(packageManager);
            // 获得app名称
            appBean.appName = applicationInfo.loadLabel(packageManager).toString();
            appList.add(appBean);
        }
        return appList;
    }
}
