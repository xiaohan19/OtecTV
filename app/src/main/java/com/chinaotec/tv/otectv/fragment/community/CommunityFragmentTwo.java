package com.chinaotec.tv.otectv.fragment.community;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chinaotec.tv.otectv.R;


public class CommunityFragmentTwo extends Fragment {

    public CommunityFragmentTwo() {
    }

    public static CommunityFragmentTwo newInstance() {
        Bundle args = new Bundle();
        CommunityFragmentTwo fragment = new CommunityFragmentTwo();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_community_two, container, false);
        init(inflate);
        return inflate;
    }

    private void init(View inflate) {
        ViewGroup viewGroup = (ViewGroup) inflate.findViewById(R.id.community_fragment_two);
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
