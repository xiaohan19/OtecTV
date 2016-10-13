package com.chinaotec.tv.otectv.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chinaotec.tv.otectv.R;
import com.chinaotec.tv.otectv.bean.AppBean;
import com.chinaotec.tv.otectv.util.GetSysUtil;
import com.chinaotec.tv.otectv.util.Logger;

import java.util.List;

public class AppStoreActivity extends AppCompatActivity {

    private RelativeLayout relative_top_app_store;
    private View view_line;
    private FrameLayout app_ott_video;
    private RelativeLayout relative_app;
    private Context mContext;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_store);
        initView();
        //初始化参数
        mContext = this;
        mActivity = this;
        //跳转到该页面ott应用图片获取焦点
        app_ott_video.requestFocus();
        iniListener();
    }

    private void initView() {
        relative_top_app_store = (RelativeLayout) findViewById(R.id.relative_top_app_store);
        relative_app = (RelativeLayout) findViewById(R.id.relative_app);
        app_ott_video = (FrameLayout) findViewById(R.id.app_ott_video);
    }

    private void iniListener() {
        relative_app.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                if (newFocus != null) {
                    newFocus.animate().scaleX(1.1f).scaleY(1.1f).setDuration(300).start();
                    newFocus.setBackgroundResource(R.drawable.main_select_shadow);
                }
                if (oldFocus != null) {
                    oldFocus.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start();
                    oldFocus.setBackgroundResource(R.drawable.main_shadow);
                }
            }
        });

        for (int i = 0; i < relative_app.getChildCount(); i++) {
            View childAt = relative_app.getChildAt(i);
            childAt.setClickable(true);
            childAt.setFocusable(true);
            childAt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    List<AppBean> appList = GetSysUtil.getAllAppInfo(mActivity);
                    switch (id) {
                        //打开游戏大厅app
                        case R.id.app_game_hall:
                            for (int i = 0; i < appList.size(); i++) {
                                if ("com.chinaotec.platformotec".equals(appList.get(i).packgeName)) {
                                    try {
                                        Intent intent = mActivity.getPackageManager().getLaunchIntentForPackage(appList.get(i).packgeName);
                                        mContext.startActivity(intent);
                                    } catch (Exception e) {
                                        Toast.makeText(mContext, "打开应用程序失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            break;
                        //打开应用商店
                        case R.id.app_shop:
                            for (int i = 0; i < appList.size(); i++) {
                                if ("com.shafa.market".equals(appList.get(i).packgeName)) {
                                    try {
                                        Intent intent = mActivity.getPackageManager().getLaunchIntentForPackage(appList.get(i).packgeName);
                                        mContext.startActivity(intent);
                                    } catch (Exception e) {
                                        Toast.makeText(mContext, "打开应用程序失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            break;
                        //打开智能医疗
                        case R.id.app_intelligent_medical:
                            for (int i = 0; i < appList.size(); i++) {
                                if ("cn.dxy.android.aspirin.tv".equals(appList.get(i).packgeName)) {
                                    Logger.i("丁香医生的包名" + appList.get(i).packgeName);
                                    try {
                                        Intent intent = mActivity.getPackageManager().getLaunchIntentForPackage(appList.get(i).packgeName);
                                        mContext.startActivity(intent);
                                    } catch (Exception e) {
                                        Toast.makeText(mContext, "打开应用程序失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            break;
                        default:
                            Toast.makeText(mContext, "此应用尚未开发", Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            });
        }
    }
}
