package com.chinaotec.tv.otectv.fragment.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinaotec.tv.otectv.R;
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_two, container, false);
    }

}
