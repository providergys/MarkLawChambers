package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.ActivityTeamDetailBinding;

import java.util.StringTokenizer;

public class TeamDetail extends AppCompatActivity {
    ActivityTeamDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_team_detail);

        RequestOptions myOptions = new RequestOptions()
                .override(500, 500);
        Glide.with(this)
                .asBitmap()
                .apply(myOptions)
                .load(getIntent().getStringExtra("teamImage"))
                .into(binding.teamImage);


      //  Glide.with(getApplicationContext()).load(getIntent().getStringExtra("teamImage")).into(binding.teamImage);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.teamContent.setText(Html.fromHtml((getIntent().getStringExtra("Description")), Html.FROM_HTML_MODE_COMPACT));
        }

    }


    public void back(View view){
        finish();
    }
}
