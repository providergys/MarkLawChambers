package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.ActivityTeamDetailBinding;

public class TeamDetail extends AppCompatActivity {
    ActivityTeamDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_team_detail);
        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("teamImage")).into(binding.teamImage);
        binding.teamContent.setText(getIntent().getStringExtra("Description"));
    }
    public void back(View view){
        finish();
    }
}
