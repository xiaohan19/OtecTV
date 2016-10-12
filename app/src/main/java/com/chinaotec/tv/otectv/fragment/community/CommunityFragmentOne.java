package com.chinaotec.tv.otectv.fragment.community;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chinaotec.tv.otectv.R;


public class CommunityFragmentOne extends Fragment {

    public CommunityFragmentOne() {
    }

    public static CommunityFragmentOne newInstance() {
        Bundle args = new Bundle();
        CommunityFragmentOne fragment = new CommunityFragmentOne();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_community_one, container, false);
        init(inflate);
        return inflate;
    }

    private void init(View inflate) {
        ViewGroup viewGroup = (ViewGroup) inflate.findViewById(R.id.community_fragment_one);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setFocusable(true);
            viewGroup.getChildAt(i).setClickable(true);
            viewGroup.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        default:
                            Toast.makeText(getContext(), "此功能尚未开发,敬请你的期待", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
