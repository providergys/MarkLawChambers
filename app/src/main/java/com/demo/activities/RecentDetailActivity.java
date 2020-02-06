package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.ActivityRecentDetailBinding;

public class RecentDetailActivity extends AppCompatActivity {
    ActivityRecentDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recent_detail);
        binding.detailText.setText(getIntent().getStringExtra("content"));
        Glide.with(this).load(getIntent().getStringExtra("image")).into(binding.detailImage);
    }
    public void recentback(View view){
        finish();
    }
}
