package com.chinaotec.tv.otectv.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinaotec.tv.otectv.R;
import com.chinaotec.tv.otectv.custom.MyViewPager;
import com.chinaotec.tv.otectv.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linhao on 2016/10/9.
 */
public class VideoSelectFragment extends BaseFragment {

    private View inflate;
    private List<BaseFragment> fragments;
    private MyViewPager viewPager;

    public VideoSelectFragment() {
        // Required empty public constructor
    }

    public static VideoSelectFragment newInstance(String str) {
        Logger.i();
        Bundle args = new Bundle();
        args.putString("type", str);
        VideoSelectFragment fragment = new VideoSelectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_video_select, container, false);
        init();
        return inflate;
    }

    private void init() {
        TextView textView = (TextView) inflate.findViewById(R.id.type_txt);
        textView.setText(getArguments().getString("type"));
        viewPager = (MyViewPager) inflate.findViewById(R.id.video_select_viewPager);
//        viewPager.setOnKeyInterceptListener(new MyViewPager.OnKeyInterceptListener() {
//            @Override
//            public boolean OnKeyIntercept(ViewGroup viewGroup, int keyCode, KeyEvent event) {
//                return executeKeyEvent(event);
//            }
//        });

        viewPager.setOnKeyDownInterceptListener(new MyViewPager.OnKeyDownInterceptListener() {
            @Override
            public boolean OnKeyDownIntercept(ViewGroup viewGroup, int keyCode, KeyEvent event) {
                return executeKeyEvent(event);
            }
        });
        fragments = new ArrayList<>();
        fragments.add(ItemVideoOneFragment.newInstance());
        fragments.add(ItemVideoTwoFragment.newInstance());
        viewPager.setAdapter(new MyPagerAdapter());
    }

    @Override
    public boolean onFocusChange(boolean hasFocus) {
        if (hasFocus) {
            fragments.get(viewPager.getCurrentItem()).setFocus(hasFocus);
        }
        return true;
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter() {
            super(getChildFragmentManager());
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
