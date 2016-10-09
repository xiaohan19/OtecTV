package com.chinaotec.tv.otectv.fragment.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinaotec.tv.otectv.R;
import com.chinaotec.tv.otectv.activity.VideoActivity;
import com.chinaotec.tv.otectv.util.Logger;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainOneFragment extends Fragment {

    //    private boolean isVisibleToUser= true;
//    private boolean isPlay = false;
    private View inflate;
    private View lookBackImg;

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
        inflate = inflater.inflate(R.layout.fragment_main_one, container, false);
        init();
        return inflate;
    }

    private void init() {
        lookBackImg = inflate.findViewById(R.id.main_look_back);
//        final View mainTV = inflate.findViewById(R.id.main_tv_tv);
//        final ViewGroup view = (ViewGroup)getActivity().findViewById(android.R.id.content);
//        mainTV.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                if (!isPlay&&isVisibleToUser) {
//                    Rect rect = new Rect();
//                    view.offsetDescendantRectToMyCoords(mainTV,rect);
//                    MainActivity.SetWin(rect.left,rect.top,mainTV.getWidth(),mainTV.getHeight());
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Logger.d("耗时操作开始------》》");
//                            MainActivity.PlayFreqLow();
//                            Logger.d("《《-------耗时操作完成");
//                            isPlay=true;
//                        }
//                    }).start();
//                }
//            }
//        });
        ViewGroup viewGroup = (ViewGroup) inflate.findViewById(R.id.main_one_fragment);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    switch (v.getId()) {
                        case R.id.main_hd:
                            intent = new Intent(getContext(), VideoActivity.class);
                            intent.putExtra("type", "hd");
                            startActivity(intent);
                            break;
                        case R.id.main_look_back:
                            intent = new Intent(getContext(), VideoActivity.class);
                            intent.putExtra("type", "lookBack");
                            startActivity(intent);
                            break;
                        case R.id.main_play:
                            intent = new Intent(getContext(), VideoActivity.class);
                            intent.putExtra("type", "play");
                            startActivity(intent);
                            break;
                    }
                }
            });
        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser) {
            Logger.d("显示 ");
            if (lookBackImg != null) {
                lookBackImg.requestFocus();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        MainActivity.PlayFreqLow();
//                        isPlay = true;
//                    }
//                }).start();
            }
        } else {
            Logger.d("隐藏 ");
//            if (isPlay) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        MainActivity.MediaExit();
//                        isPlay = false;
//                    }
//                }).start();
//            }
        }
    }

}
