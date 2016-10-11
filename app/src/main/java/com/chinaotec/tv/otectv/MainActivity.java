package com.chinaotec.tv.otectv;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;

import com.chinaotec.tv.otectv.activity.BaseActivity;
import com.chinaotec.tv.otectv.fragment.main.MainOneFragment;
import com.chinaotec.tv.otectv.fragment.main.MainTwoFragment;
import com.chinaotec.tv.otectv.util.Logger;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    static {
        System.loadLibrary("tvplay");
    }

    private List<Fragment> fragments;

    public static native String PlayFreqHigh();

    public static native String PlayFreqMed();

    public static native String PlayFreqLow();

    public static native String MediaExit();

    public static native String SetWin(int x, int y, int w, int h);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.i();
        init();
    }

    private void init() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        fragments = new ArrayList<>();
        fragments.add(MainOneFragment.newInstance());
        fragments.add(MainTwoFragment.newInstance());
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setPageMargin(0);
        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                if (newFocus != null) {
                    if (newFocus.getId() == R.id.main_tv) {
                        newFocus.setBackgroundResource(R.drawable.main_select_shadow_tv);
                    } else {
                        newFocus.animate().scaleX(1.1f).scaleY(1.1f).setDuration(300).start();
                        newFocus.setBackgroundResource(R.drawable.main_select_shadow);
                    }
                }
                if (oldFocus != null) {
                    oldFocus.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start();
                    oldFocus.setBackgroundResource(R.drawable.main_shadow);
                }
            }
        });
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter() {
            super(getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


}
