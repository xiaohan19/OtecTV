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
public class ItemVideoTwoFragment extends Fragment {

    private View inflate;
    private View view;

    public ItemVideoTwoFragment() {
        // Required empty public constructor
    }

    public static ItemVideoTwoFragment newInstance() {

        Bundle args = new Bundle();

        ItemVideoTwoFragment fragment = new ItemVideoTwoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_item_video_two, container, false);
        init();
        return inflate;
    }

    private void init() {
        view = inflate.findViewById(R.id.video_tv5);
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
}
