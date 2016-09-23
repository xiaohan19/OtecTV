package com.chinaotec.tv.otectv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.chinaotec.tv.otectv.custom.MyHorizontalScrollView;
import com.chinaotec.tv.otectv.custom.ShadowView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        MyHorizontalScrollView scrollView = (MyHorizontalScrollView) findViewById(R.id.scrollView);
        scrollView.setFadingEdge((int) getResources().getDimension(R.dimen.w_100));
        FrameLayout relativeLayout = (FrameLayout) findViewById(R.id.main_fl);
        final ShadowView shadowView = (ShadowView) findViewById(R.id.shadow);
        shadowView.setDrawable(getResources().getDrawable(R.drawable.main_select_true));
        shadowView.setShadow(getResources().getDrawable(R.drawable.item_shadow));
        for (int i = 0; i < relativeLayout.getChildCount(); i++) {
            relativeLayout.getChildAt(i).setFocusable(true);
            relativeLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, v.getId() + "", Toast.LENGTH_SHORT).show();
                }
            });
        }
        relativeLayout.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                if (newFocus != null) {
                    newFocus.bringToFront();
                }
                shadowView.setFocusView(newFocus, oldFocus, 1.1f);
            }
        });
    }


}
