package com.example.sample.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.sample.R;
import com.example.sample.adapter.SamplePagerAdapter;

public class TabFragmentActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_fragment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.tab_layout));

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        SamplePagerAdapter pageAdapter = new SamplePagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(pageAdapter);

        tabLayout.setupWithViewPager(viewpager);
        tabLayout.setTabTextColors(getResources().getColorStateList(R.color.textColorPrimary));

    }
}
