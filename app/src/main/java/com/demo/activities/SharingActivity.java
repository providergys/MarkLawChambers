package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.demo.adapter.RecentActivityAdapter;
import com.demo.adapter.SharingPosdCastAdapter;
import com.demo.adapter.ViewPagerAdapter;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.ActivitySharingBinding;
import com.demo.model.PodCastList;
import com.demo.model.RecentResponse;
import com.demo.retroutility.MainApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SharingActivity extends AppCompatActivity {
    ActivitySharingBinding binding;
    Activity ac;

   ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sharing);
        ac = SharingActivity.this;
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        binding.viewPager.setAdapter(viewPagerAdapter);
        binding.tabs.setupWithViewPager(binding.viewPager);
    }


    public  void back(View view){
        startActivity(new Intent(SharingActivity.this,HomeActivity.class));
    }

}
