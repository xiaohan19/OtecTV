package com.chinaotec.tv.otectv.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chinaotec.tv.otectv.R;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        init();
    }

    private void init() {
        String type = getIntent().getStringExtra("type");

    }


}
