package com.chinaotec.tv.otectv.fragment.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinaotec.tv.otectv.R;
import com.chinaotec.tv.otectv.activity.community.CommunityActivity;
import com.chinaotec.tv.otectv.util.Logger;

/**
 * Created by linhao on 2016/10/9.
 */
public class MainTwoFragment extends Fragment {

    public MainTwoFragment() {
        // Required empty public constructor
    }

    public static MainTwoFragment newInstance() {
        Logger.i();
        Bundle args = new Bundle();
        MainTwoFragment fragment = new MainTwoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_two, container, false);
        // Inflate the layout for this fragment
        init(view);
        return view;
    }

    private void init(View view) {
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.main_two_fragment);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setClickable(true);
            viewGroup.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    switch (v.getId()) {
                        //跳转社区页面
                        case R.id.main_community:
                            intent = new Intent(getContext(), CommunityActivity.class);
                            startActivity(intent);
                            break;
                    }
                }
            });
        }
    }

}
