package com.chinaotec.tv.otectv.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chinaotec.tv.otectv.R;
import com.chinaotec.tv.otectv.fragment.VideoSelectFragment;

import java.util.ArrayList;
import java.util.List;

public class VideoSelectActivity extends AppCompatActivity {
    private String[] lookBackStrArray = {"推荐","高清回看","标清回看","标清央视","标清外地"};
    private String[] hdStrArray = {"高清频道","高清回看","高清点播","3D点播"};
    private String[] playStrArray = {"最新推荐","院线大片","欧美","港台","动作","科幻","爱情","推理"};
    private List<Fragment> fragments = new ArrayList<>();
    private Fragment showFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_select);
        init();
    }

    private void init() {
        String type = getIntent().getStringExtra("type");
        RadioGroup radioGroup =  (RadioGroup) findViewById(R.id.radioGroup);
        switch (type){
            case "hd":
                for (int i = 0; i < hdStrArray.length; i++) {
                    radioGroup.addView(newRadioRutton(hdStrArray[i]));
                }
                break;
            case "lookBack":
                for (int i = 0; i < lookBackStrArray.length; i++) {
                    radioGroup.addView(newRadioRutton(lookBackStrArray[i]));
                }
                break;
            case  "play":
                for (int i = 0; i < playStrArray.length; i++) {
                    radioGroup.addView(newRadioRutton(playStrArray[i]));
                }
                break;
        }
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            final int finalI = i;
            ((RadioButton) radioGroup.getChildAt(i)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        buttonView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.w_60));
                        buttonView.setTextColor(getResources().getColor(R.color.white));
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        if (fragments.size()< finalI+1) {
                            VideoSelectFragment videoSelectFragment = VideoSelectFragment.newInstance(buttonView.getText().toString());
                            fragmentTransaction.add(R.id.video_select_activity_fragment,videoSelectFragment);
                            fragments.add(videoSelectFragment);
                        }else {
                            fragmentTransaction.show(fragments.get(finalI));
                        }
                        if(showFragment!=null){
                            fragmentTransaction.hide(showFragment);
                        }
                        showFragment = fragments.get(finalI);
                        fragmentTransaction.commit();
                    }else {
                        buttonView.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.w_40));
                        buttonView.setTextColor(getResources().getColor(R.color.titleSelected_gray));
                    }
                }
            });
        }
    }

    private RadioButton newRadioRutton(String str){
        RadioButton radioButton = new RadioButton(this);
        radioButton.setText(str);
        radioButton.setButtonDrawable(null);
        radioButton.setPadding(0,0,getResources().getDimensionPixelOffset(R.dimen.w_30),0);
        radioButton.setTextColor(getResources().getColor(R.color.titleSelected_gray));
        radioButton.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.w_40));
        radioButton.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((RadioButton) v).setChecked(true);
                }
                v.setBackgroundResource(R.color.transparent);
            }
        });
        return  radioButton;
    }


}
