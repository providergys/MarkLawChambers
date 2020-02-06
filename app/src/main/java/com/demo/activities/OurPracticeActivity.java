package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.demo.marklaw.R;
import com.demo.marklaw.databinding.ActivityOurPracticeBinding;

public class OurPracticeActivity extends AppCompatActivity {
    ActivityOurPracticeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_our_practice);
    }

    public void back(View view) {
        finish();

    }
}
