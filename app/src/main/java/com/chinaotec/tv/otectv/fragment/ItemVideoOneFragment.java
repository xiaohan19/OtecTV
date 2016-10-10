package com.chinaotec.tv.otectv.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinaotec.tv.otectv.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemVideoOneFragment extends Fragment {

    public static ItemVideoOneFragment newInstance() {

        Bundle args = new Bundle();

        ItemVideoOneFragment fragment = new ItemVideoOneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ItemVideoOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_video_one, container, false);
    }

}
