package com.chinaotec.tv.otectv.fragment.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinaotec.tv.otectv.R;
import com.chinaotec.tv.otectv.util.Logger;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainOneFragment extends Fragment {

    public MainOneFragment() {
        // Required empty public constructor
    }

    public static MainOneFragment newInstance() {
        Logger.i();
        Bundle args = new Bundle();
        MainOneFragment fragment = new MainOneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_one, container, false);
    }

}
