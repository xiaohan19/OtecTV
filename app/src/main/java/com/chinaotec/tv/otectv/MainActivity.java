package com.chinaotec.tv.otectv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        init();
    }

    private void init() {
        FrameLayout relativeLayout = (FrameLayout) findViewById(R.id.main_fl);
        for (int i = 0; i < relativeLayout.getChildCount(); i++) {
            relativeLayout.getChildAt(i).setFocusable(true);
            relativeLayout.getChildAt(i).setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        v.setBackgroundResource(R.drawable.main_select_true);
                    }else {
                        v.setBackgroundResource(R.color.transparent);
                    }
                }
            });
        }
    }

}
