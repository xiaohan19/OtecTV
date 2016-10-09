package com.chinaotec.tv.otectv.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.chinaotec.tv.otectv.R;
import com.chinaotec.tv.otectv.activity.HighDefinitionActivity;
import com.chinaotec.tv.otectv.custom.MyHorizontalScrollView;
import com.chinaotec.tv.otectv.custom.ShadowView;

/**
 * Created by Administrator on 2016/9/22 0022.
 */
public class HdPageChannelFragment extends Fragment {

    private FrameLayout frameLayout;

    public HdPageChannelFragment() {
    }

    /*
    * 跳转页面后接收传递过来的数据
    * 通过bundle对象
    */
    public static HdPageChannelFragment newInstance() {
        Bundle bundle = new Bundle();
        HdPageChannelFragment hdPageChannelFragment = new HdPageChannelFragment();
        hdPageChannelFragment.setArguments(bundle);
        return hdPageChannelFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hd_page, container, false);
        //初始化逻辑
        iniListener(view);
        //初始化阴影
        iniShadow(view);
        return view;
    }

    private void iniShadow(View view) {
        final ShadowView shadowView = ((HighDefinitionActivity) getActivity()).getShadowView();
        for (int i = 0; i < frameLayout.getChildCount(); i++) {
            frameLayout.getChildAt(i).setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        v.bringToFront();
                        shadowView.setFocusView(v, 1.1f);
                    } else {
                        shadowView.setUnFocusView(v);
                    }
                }
            });
        }
        //处理页面焦点乱跳
        MyHorizontalScrollView horizontalScrollView = (MyHorizontalScrollView) view.findViewById(R.id.hd_scroll_view);
//        horizontalScrollView.
    }

    private void iniListener(View view) {
        frameLayout = (FrameLayout) view.findViewById(R.id.hd_frame_out);
        for (int i = 0; i < frameLayout.getChildCount(); i++) {
            View focusView = frameLayout.getChildAt(i);
            focusView.setFocusable(true);
            focusView.setClickable(true);
            if (focusView.getId() == R.id.image_hd_page01) {
                focusView.setNextFocusUpId(R.id.btn_hd_channel);
            } else if (focusView.getId() == R.id.image_hd_page02) {
                focusView.setNextFocusRightId(R.id.image_hd_item03_up);
                focusView.setNextFocusUpId(R.id.btn_hd_channel);
            } else if (focusView.getId() == R.id.image_hd_item03_up) {
                focusView.setNextFocusUpId(R.id.btn_hd_channel);
            }
        }
    }
}
