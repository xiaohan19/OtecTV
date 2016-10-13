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
public class ItemVideoOneFragment extends BaseFragment {

    private View inflate;
    private View view;

    public ItemVideoOneFragment() {
        // Required empty public constructor
    }

    public static ItemVideoOneFragment newInstance() {

        Bundle args = new Bundle();

        ItemVideoOneFragment fragment = new ItemVideoOneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_item_video_one, container, false);
        init();
        return inflate;
    }

    private void init() {
        view = inflate.findViewById(R.id.video_big_tv2);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (view != null) {
                view.requestFocus();
            }
        }
    }

    @Override
    public boolean onFocusChange(boolean hasFocus) {
        inflate.findViewById(R.id.video_big_tv1).requestFocus();
        return true;
    }
}
