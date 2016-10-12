package com.chinaotec.tv.otectv.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.chinaotec.tv.otectv.R;
import com.chinaotec.tv.otectv.custom.WeatherView;

public abstract class BaseActivity extends AppCompatActivity {
    private static WeatherView weatherView;
    private RelativeLayout childAt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (weatherView == null) {
            weatherView = new WeatherView(this);
        }
        ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
        childAt = (RelativeLayout) viewGroup.getChildAt(0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(getResources().getDimensionPixelOffset(R.dimen.w_1480), getResources().getDimensionPixelOffset(R.dimen.w_43), getResources().getDimensionPixelOffset(R.dimen.w_60), getResources().getDimensionPixelOffset(R.dimen.w_944));
        childAt.addView(weatherView, layoutParams);
    }

    @Override
    protected void onPause() {
        super.onPause();
        childAt.removeView(weatherView);
    }
}
