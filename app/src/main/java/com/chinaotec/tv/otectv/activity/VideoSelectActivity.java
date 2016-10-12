package com.chinaotec.tv.otectv.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chinaotec.tv.otectv.R;
import com.chinaotec.tv.otectv.activity.base.BaseActivity;
import com.chinaotec.tv.otectv.fragment.VideoSelectFragment;

import java.util.HashMap;
import java.util.Map;

public class VideoSelectActivity extends BaseActivity {
    private String[] lookBackStrArray = {"推荐", "高清回看", "标清回看", "标清央视", "标清外地"};
    private String[] hdStrArray = {"高清频道", "高清回看", "高清点播", "3D点播"};
    private String[] playStrArray = {"最新推荐", "院线大片", "欧美", "港台", "动作", "科幻", "爱情", "推理"};
    private Map<Integer, Fragment> fragments = new HashMap<>();
    private Fragment showFragment;
    private RadioGroup radioGroup;
    private boolean isDestroy = true;//防止radioButton回调因为activity已经销毁继续执行fragmentTransaction.commitAllowingStateLoss()的异常；

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_select);
        isDestroy = false;
        init();
    }

    private void init() {
        String type = getIntent().getStringExtra("type");
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        switch (type) {
            case "hd":
                for (int i = 0; i < hdStrArray.length; i++) {
                    radioGroup.addView(newRadioRutton(hdStrArray, i), new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                }
                break;
            case "lookBack":
                for (int i = 0; i < lookBackStrArray.length; i++) {
                    radioGroup.addView(newRadioRutton(lookBackStrArray, i), new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                }
                break;
            case "play":
                for (int i = 0; i < playStrArray.length; i++) {
                    radioGroup.addView(newRadioRutton(playStrArray, i), new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                }
                break;
        }
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            ((RadioButton) radioGroup.getChildAt(i)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        buttonView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.w_60));
                        buttonView.setTextColor(getResources().getColor(R.color.white));
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        if (fragments.get(buttonView.getId()) == null) {
                            VideoSelectFragment videoSelectFragment = VideoSelectFragment.newInstance(buttonView.getText().toString());
                            fragmentTransaction.add(R.id.video_select_activity_fragment, videoSelectFragment);
                            fragments.put(buttonView.getId(), videoSelectFragment);
                        } else {
                            fragmentTransaction.show(fragments.get(buttonView.getId()));
                        }
                        if (showFragment != null) {
                            fragmentTransaction.hide(showFragment);
                        }
                        showFragment = fragments.get(buttonView.getId());
                        if (!isDestroy) {
                            fragmentTransaction.commitAllowingStateLoss();
                        }
                    } else {
                        buttonView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.w_40));
                        buttonView.setTextColor(getResources().getColor(R.color.titleSelected_gray));
                    }
                }
            });
        }
        radioGroup.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                if (newFocus != null) {
                    if (newFocus instanceof CompoundButton) {
                    } else {
                        ((View) newFocus.getParent()).bringToFront();
                        newFocus.bringToFront();
                        newFocus.animate().scaleX(1.1f).scaleY(1.1f).setDuration(300).start();
                        newFocus.setBackgroundResource(R.drawable.main_select_shadow);
                    }
                }
                if (oldFocus != null) {
                    if (oldFocus instanceof CompoundButton) {
                    } else {
                        oldFocus.animate().scaleX(1.0f).scaleY(1.0f).setDuration(300).start();
                        oldFocus.setBackgroundResource(R.drawable.main_shadow);
                    }
                }
            }
        });
    }

    private RadioButton newRadioRutton(final String[] strings, final int position) {
        RadioButton radioButton = new RadioButton(this);
        radioGroup.setClipChildren(true);
        radioButton.setText(strings[position]);
        radioButton.setButtonDrawable(null);
        radioButton.setPadding(0, 0, getResources().getDimensionPixelOffset(R.dimen.w_30), 0);
        radioButton.setId(radioButton.hashCode());
        radioButton.setGravity(Gravity.BOTTOM);
        radioButton.setTextColor(getResources().getColor(R.color.titleSelected_gray));
        radioButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.w_40));
        radioButton.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((RadioButton) v).setChecked(true);
                }
                v.setBackgroundResource(R.color.transparent);
            }
        });
        radioButton.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((position == 0 && keyCode == KeyEvent.KEYCODE_DPAD_LEFT) || (position == strings.length - 1 && keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)) {
                    return true;
                }
                return false;
            }
        });

        return radioButton;
    }

    @Override
    protected void onDestroy() {
        isDestroy = true;
        super.onDestroy();
    }
}
