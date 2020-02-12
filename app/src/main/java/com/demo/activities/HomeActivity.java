package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.demo.adapter.RecentActivityAdapter;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.ActivityHomeBinding;
import com.demo.model.RecentResponse;
import com.demo.retroutility.MainApplication;
import com.demo.utility.Constants;
import com.demo.utility.UserSharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    Activity ac;
    ActivityHomeBinding binding;
    UserSharedPreferences mSharedPref;
    RecentActivityAdapter recentActivityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        init();
    }

    private void init() {
        ac = HomeActivity.this;
        mSharedPref = new UserSharedPreferences(ac);
        if (mSharedPref.getString(Constants.USER_TYPE).equals("Client")) {
            binding.withCaseRel.setVisibility(View.VISIBLE);
        } else {
            binding.withoutCaseRel.setVisibility(View.VISIBLE);
        }
          getActivities();
        binding.practiceLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ac,OurPracticeActivity.class));
            }
        });

        binding.practiceLnwithout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ac,OurPracticeActivity.class));
            }
        });

        binding.ourTeamLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ac,OurTeamActivity.class));
            }
        });

        binding.ourTeamwithoutLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ac,OurTeamActivity.class));
            }
        });

   }

   public void caseUpdateClick(View view){
        startActivity(new Intent(ac,CaseUpdatesActivity.class));

   }

   public void visitWebsiteText(View view){
       Intent intent = new Intent();
       intent.setAction(Intent.ACTION_VIEW);
       intent.addCategory(Intent.CATEGORY_BROWSABLE);
       intent.setData(Uri.parse("https://mark-lawchambers.com"));
       startActivity(intent);
   }
    private void getActivities() {
        MainApplication.getApiService().recentActivityMethod("application/json").enqueue(new Callback<RecentResponse>() {
            @Override
            public void onResponse(Call<RecentResponse> call, Response<RecentResponse> response) {
                if(response.isSuccessful() && response.body()!=null){
                    if(  response.body().getRespCode().equals("1003")){
                        binding.recentActivityRecycler.setLayoutManager(new LinearLayoutManager(ac, LinearLayoutManager.HORIZONTAL, false));
                        recentActivityAdapter = new RecentActivityAdapter(ac, response.body().getPosts_data());
                        binding.recentActivityRecycler.setAdapter(recentActivityAdapter);
                    }
                    else{
                        Toast.makeText(ac,response.body().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RecentResponse> call, Throwable t) {
                Toast.makeText(ac,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
