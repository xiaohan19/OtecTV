package com.chinaotec.tv.otectv.activity.community;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;

import com.chinaotec.tv.otectv.R;
import com.chinaotec.tv.otectv.activity.base.BaseActivity;
import com.chinaotec.tv.otectv.adapter.MyPagerStateAdapter;
import com.chinaotec.tv.otectv.fragment.community.CommunityFragmentOne;
import com.chinaotec.tv.otectv.fragment.community.CommunityFragmentTwo;

import java.util.ArrayList;
import java.util.List;

public class CommunityActivity extends BaseActivity {

    List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        init();
    }

    private void init() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.community_page);
        fragmentList = new ArrayList<>();
        fragmentList.add(CommunityFragmentOne.newInstance());
        fragmentList.add(CommunityFragmentTwo.newInstance());
        viewPager.setOffscreenPageLimit(fragmentList.size());
        viewPager.setPageMargin(0);
        viewPager.setAdapter(new MyPagerStateAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
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
    }
}
