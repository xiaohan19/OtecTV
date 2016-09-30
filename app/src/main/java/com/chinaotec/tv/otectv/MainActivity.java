package com.chinaotec.tv.otectv;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.chinaotec.tv.otectv.activity.HighDefinitionActivity;
import com.chinaotec.tv.otectv.custom.MyHorizontalScrollView;
import com.chinaotec.tv.otectv.custom.ShadowView;
import com.chinaotec.tv.otectv.util.Logger;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {
    static {
        System.loadLibrary("tvplay");
    }

    private SimpleDraweeView main_tv;
    private boolean isWatch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        MyHorizontalScrollView scrollView = (MyHorizontalScrollView) findViewById(R.id.main_scroll_view);
        scrollView.setFadingEdge((int) getResources().getDimension(R.dimen.w_64));
        main_tv = (SimpleDraweeView) findViewById(R.id.main_tv);
        final ViewGroup view = (ViewGroup) findViewById(android.R.id.content);
        FrameLayout relativeLayout = (FrameLayout) findViewById(R.id.main_fl);
        final ShadowView shadowView = (ShadowView) findViewById(R.id.shadow);
        shadowView.setDrawable(getResources().getDrawable(R.drawable.main_select_true));
        shadowView.setShadow(getResources().getDrawable(R.drawable.item_shadow));
        for (int i = 0; i < relativeLayout.getChildCount(); i++) {
            relativeLayout.getChildAt(i).setFocusable(true);
            relativeLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.main_hd:
                            Intent intent = new Intent();
                            startActivity(intent.setClass(MainActivity.this, HighDefinitionActivity.class));
                            break;
                        default:
                            Toast.makeText(MainActivity.this, v.hashCode() + "", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }
        main_tv.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            private Thread thread;

            @Override
            public void onGlobalLayout() {
                if (isWatch) {
                    Rect rect = new Rect();
                    view.offsetDescendantRectToMyCoords(main_tv, rect);
                    SetWin(rect.left + 4, rect.top + 4, main_tv.getWidth() - 8, main_tv.getHeight() - 8);
                    Logger.e(rect.left + "|" + rect.top + "|" + main_tv.getWidth() + "|" + main_tv.getHeight());
                    thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Logger.e("执行播放");
                            PlayFreqLow();
                        }
                    });
                    thread.start();
                    isWatch = !isWatch;
                }
            }
        });
        relativeLayout.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                if (newFocus != null) {
                    newFocus.bringToFront();
                }
                shadowView.setFocusView(newFocus, oldFocus, 1.0f);
            }
        });
    }

    @Override
    protected void onDestroy() {
        MediaExit();
        super.onDestroy();
    }

    public native String PlayFreqHigh();

    public native String PlayFreqMed();

    public native String PlayFreqLow();

    public native String MediaExit();

    public native String SetWin(int x, int y, int w, int h);


}
