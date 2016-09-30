package com.chinaotec.tv.otectv.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.chinaotec.tv.otectv.R;
import com.chinaotec.tv.otectv.adapter.MyPagerStateAdapter;
import com.chinaotec.tv.otectv.custom.ShadowView;
import com.chinaotec.tv.otectv.fragment.HdPage3DOnDemandFragment;
import com.chinaotec.tv.otectv.fragment.HdPageChannelFragment;
import com.chinaotec.tv.otectv.fragment.HdPageLookBackFragment;
import com.chinaotec.tv.otectv.fragment.HdPageonDemandFragment;

import java.util.ArrayList;
import java.util.List;

public class HighDefinitionActivity extends FragmentActivity {

    private RelativeLayout relativeLayout_btn;
    private ViewPager viewpager;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<RadioButton> viewList = new ArrayList<>();

    private ShadowView shadowView;
    private RadioGroup radioGroup;


    public ShadowView getShadowView() {
        return shadowView;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_definiton);
        //初始化布局
        initView();
        //初始化数据
        iniData();
        //初始化逻辑
        iniListener();
        //设置适配器
        MyPagerStateAdapter myPagerAdapter = new MyPagerStateAdapter(getSupportFragmentManager(), fragmentList);
        viewpager.setAdapter(myPagerAdapter);
        //页面第一个按钮被选中
        viewList.get(0).setChecked(true);
    }

    private void initView() {

        relativeLayout_btn = (RelativeLayout) findViewById(R.id.relativeLayout_btn);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

    }

    private void iniData() {
        //初始化Fragment数据源集合
        fragmentList.add(HdPageChannelFragment.newInstance());
        fragmentList.add(HdPageLookBackFragment.newInstance());
        fragmentList.add(HdPageonDemandFragment.newInstance());
        fragmentList.add(HdPage3DOnDemandFragment.newInstance());
        //初始化RadioButton数据源集合
        viewList.add((RadioButton) findViewById(R.id.btn_hd_channel));
        viewList.add((RadioButton) findViewById(R.id.btn_hd_back_look));
        viewList.add((RadioButton) findViewById(R.id.btn_hd_on_demand));
        viewList.add((RadioButton) findViewById(R.id.btn_hd_3D_on_demand));
    }

    private void iniListener() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        shadowView = (ShadowView) findViewById(R.id.ShadowView);

        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            View childAt = radioGroup.getChildAt(i);
            final int finalI = i;
            childAt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        viewpager.setCurrentItem(finalI);
                        RadioButton v1 = (RadioButton) v;
                        //设置按钮背景色为透明,去除单选按钮被选中后的大框框.
                        v1.setBackgroundResource(R.color.transparent);
                        v1.setChecked(true);
                        shadowView.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int buttonIdChecked = group.getCheckedRadioButtonId();
                for (int i = 0; i < viewList.size(); i++) {
                    RadioButton radioButton = viewList.get(i);
                    //处理3D点播页面遥控器向下跳转,不能跳到该页面第一个组件的问题
                    if (buttonIdChecked == R.id.btn_hd_3D_on_demand) {
                        radioButton.setNextFocusDownId(R.id.image_hd_page01);
                    }
                    if (buttonIdChecked == radioButton.getId()) {
                        radioButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title_text_size_selected));
                    } else {
                        radioButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.title_text_size_normal));
                    }
                }
            }
        });
        //处理阴影
        shadowView.setDrawable(getResources().getDrawable(R.drawable.main_select_true));
        shadowView.setShadow(getResources().getDrawable(R.drawable.item_shadow));
    }
}
