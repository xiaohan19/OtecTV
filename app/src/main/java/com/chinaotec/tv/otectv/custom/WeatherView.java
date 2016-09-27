package com.chinaotec.tv.otectv.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinaotec.tv.otectv.R;

import java.text.SimpleDateFormat;

/**
 * Created by linhao on 2016/9/26.
 */
public class WeatherView extends LinearLayout {
    private boolean isColon;

    public WeatherView(Context context) {
        super(context);
        init(context, null);
    }

    public WeatherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public WeatherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.weather_view_linear, this, true);
        final TextView timeTxt = (TextView) inflate.findViewById(R.id.time);
        timeTxt.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat sDateFormat;
                if (isColon)
                    sDateFormat = new SimpleDateFormat("HH:mm  yyyy-MM-dd");
                else
                    sDateFormat = new SimpleDateFormat("HH mm  yyyy-MM-dd");
                String date = sDateFormat.format(new java.util.Date());
                timeTxt.setText(date);
                isColon = !isColon;
                timeTxt.postDelayed(this, 500);
            }
        }, 500);
    }


}
