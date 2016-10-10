package com.chinaotec.tv.otectv.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chinaotec.tv.otectv.R;
import com.chinaotec.tv.otectv.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linhao on 2016/10/9.
 */
public class VideoSelectFragment extends Fragment {

    private View inflate;
    private List<Fragment> fragments;

    public static VideoSelectFragment newInstance(String str) {
        Logger.i();
        Bundle args = new Bundle();
        args.putString("type",str);
        VideoSelectFragment fragment = new VideoSelectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public VideoSelectFragment() {
        // Required empty public constructor
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
        TextView textView = (TextView)inflate.findViewById(R.id.type_txt);
        textView.setText(getArguments().getString("type"));
        ViewPager viewPager = (ViewPager) inflate.findViewById(R.id.video_select_viewPager);
        fragments = new ArrayList<>();
        fragments.add(ItemVideoOneFragment.newInstance());
        fragments.add(ItemVideoTwoFragment.newInstance());
        viewPager.setAdapter(new MyPagerAdapter());
    }

    class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter() {
            super(getFragmentManager());
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
