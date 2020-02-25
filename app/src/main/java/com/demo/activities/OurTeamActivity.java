package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.demo.adapter.AdvisorAdapter;
import com.demo.adapter.SupportAdapter;
import com.demo.adapter.TeamAdapter;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.ActivityOurTeamBinding;
import com.demo.model.TeamResponse;
import com.demo.retroutility.MainApplication;
import com.demo.utility.ProgDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OurTeamActivity extends AppCompatActivity {
    ActivityOurTeamBinding binding;
    ProgDialog prog = new ProgDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_our_team);
        prog.progDialog(this);
        MainApplication.getApiService().getTeamMethod("application/json").enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {

                if (response.isSuccessful()) {
                    prog.hideProg();
                    setTeamAdapter(response.body().getTeamposts());
                    setAdvisorAdapter(response.body().getAdvisorposts());
                    setSupportingAdapter(response.body().getSupportingposts());
                } else {
                    prog.hideProg();
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        binding.messageLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OurTeamActivity.this, MessageActivity.class));
            }
        });

    }

    private void setTeamAdapter(List<TeamResponse.TeampostsBean> teamposts) {
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        TeamAdapter teamAdapter = new TeamAdapter(getApplicationContext(), teamposts);
        binding.teamRecycler.setLayoutManager(manager);
        binding.teamRecycler.setAdapter(teamAdapter);
    }

    private void setAdvisorAdapter(List<TeamResponse.AdvisorpostsBean> advisorposts) {
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        AdvisorAdapter teamAdapter = new AdvisorAdapter(getApplicationContext(), advisorposts);
        binding.externalAdvRecycler.setLayoutManager(manager);
        binding.externalAdvRecycler.setAdapter(teamAdapter);
    }


    private void setSupportingAdapter(List<TeamResponse.SupportingpostsBean> supportingposts) {
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        SupportAdapter teamAdapter = new SupportAdapter(getApplicationContext(), supportingposts);
        binding.supportTeamRecycler.setLayoutManager(manager);
        binding.supportTeamRecycler.setAdapter(teamAdapter);


    }

    public void back(View view) {
        finish();
    }

    public void settingLn(View view) {
        startActivity(new Intent(OurTeamActivity.this, SettingActivity.class));
    }

    public void home(View view) {
        startActivity(new Intent(OurTeamActivity.this, HomeActivity.class));
    }

    public void reachUs(View view) {
        startActivity(new Intent(OurTeamActivity.this,ReachUsActivity.class));
    }
}
