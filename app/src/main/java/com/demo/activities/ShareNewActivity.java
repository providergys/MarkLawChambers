package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.demo.adapter.ViewPagerAdapter;
import com.demo.marklaw.R;
import com.google.android.material.tabs.TabLayout;

public class ShareNewActivity extends AppCompatActivity {
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_new);
        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
