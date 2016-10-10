package com.chinaotec.tv.otectv.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chinaotec.tv.otectv.R;
import com.chinaotec.tv.otectv.bean.AppBean;
import com.chinaotec.tv.otectv.util.GetSysUtil;

import java.util.List;

public class AppStoreActivity extends AppCompatActivity {

    private ImageView app_store_logo;
    private RelativeLayout relative_top;
    private View view_line;
    private FrameLayout app_ott_video;
    private FrameLayout app_video_communications;
    private FrameLayout app_intelligent_medical;
    private FrameLayout app_shop;
    private FrameLayout app_game_hall;
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
        app_store_logo = (ImageView) findViewById(R.id.app_store_logo);
        relative_top = (RelativeLayout) findViewById(R.id.relative_top);
        view_line = (View) findViewById(R.id.view_line);
        app_ott_video = (FrameLayout) findViewById(R.id.app_ott_video);
        app_video_communications = (FrameLayout) findViewById(R.id.app_video_communications);
        app_intelligent_medical = (FrameLayout) findViewById(R.id.app_intelligent_medical);
        app_shop = (FrameLayout) findViewById(R.id.app_shop);
        app_game_hall = (FrameLayout) findViewById(R.id.app_game_hall);
        relative_app = (RelativeLayout) findViewById(R.id.relative_app);
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
                    switch (id) {
                        //打开游戏大厅app
                        case R.id.app_game_hall:
                            List<AppBean> appList = GetSysUtil.getAllAppInfo(mActivity);
                            for (int i = 0; i < appList.size(); i++) {
                                if ("com.chinaotec.platformotec".equals(appList.get(i).packgeName)) {
                                    try {
                                        Intent intent = mActivity.getPackageManager().getLaunchIntentForPackage(appList.get(i).packgeName);
                                        mContext.startActivity(intent);
                                    } catch (Exception e) {
                                        Toast.makeText(mContext, "打开游戏大厅失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                    }
                }
            });
        }
    }
}
