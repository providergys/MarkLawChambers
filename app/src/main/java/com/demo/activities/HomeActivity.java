package com.demo.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.demo.adapter.AllDatatAdapter;
import com.demo.adapter.RecentActivityAdapter;
import com.demo.adapter.SharingPosdCastAdapter;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.ActivityHomeBinding;
import com.demo.model.RecentResponse;
import com.demo.model.SharingResponse;
import com.demo.model.VideoPosdcastResponse;
import com.demo.retroutility.MainApplication;
import com.demo.utility.Constants;
import com.demo.utility.UserSharedPreferences;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

public class HomeActivity extends AppCompatActivity {
    Activity ac;
    ActivityHomeBinding binding;
    UserSharedPreferences mSharedPref;
    RecentActivityAdapter recentActivityAdapter;
    AllDatatAdapter sharingPosdCastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        init();
    }

    private void init() {
        ac = HomeActivity.this;
        mSharedPref = new UserSharedPreferences(ac);
        Log.e("userIdHome", "" + String.valueOf(mSharedPref.getString(Constants.USER_ID)));
        if (mSharedPref.getString(Constants.USER_TYPE).equals("Client")) {
            binding.withCaseRel.setVisibility(View.VISIBLE);
        } else {
            binding.withoutCaseRel.setVisibility(View.VISIBLE);
        }
        getActivities();
        binding.practiceLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ac, OurPracticeActivity.class));
            }
        });

        binding.practiceLnwithout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ac, OurPracticeActivity.class));
            }
        });

        binding.ourTeamLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ac, OurTeamActivity.class));
            }
        });

        binding.ourTeamwithoutLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ac, OurTeamActivity.class));
            }
        });

        binding.sharingLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*final Dialog dialog = new Dialog(ac);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.comingsoondialog);
                dialog.setCanceledOnTouchOutside(true);
                Button btnOk = dialog.findViewById(R.id.btnCancel);
                dialog.show();
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                    }
                });*/


                startActivity(new Intent(HomeActivity.this,SharingActivity.class));
            }
        });

        binding.sharingwithoutLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,SharingActivity.class));
            }
        });


        binding.messageLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,MessageActivity.class));
            }
        });


        MainApplication.getApiService().getBothVP("application/json").enqueue(new Callback<VideoPosdcastResponse>() {
            @Override
            public void onResponse(Call<VideoPosdcastResponse> call, Response<VideoPosdcastResponse> response) {
                if (response.isSuccessful()) {



                    final int time = 4000; // it's the delay time for sliding between items in recyclerview


                    sharingPosdCastAdapter = new AllDatatAdapter(getApplicationContext(),response.body().getVideopodcast());
                    binding.scrollRecycler.setAdapter(sharingPosdCastAdapter);

                    final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                    binding.scrollRecycler.setLayoutManager(linearLayoutManager);

                    final LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
                    linearSnapHelper.attachToRecyclerView(binding.scrollRecycler);

                    final Timer timer = new Timer();
                    timer.schedule(new TimerTask() {

                        @Override
                        public void run() {

                            if (linearLayoutManager.findLastCompletelyVisibleItemPosition() < (sharingPosdCastAdapter.getItemCount() - 1)) {

                                linearLayoutManager.smoothScrollToPosition(binding.scrollRecycler, new RecyclerView.State(),
                                        linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1);
                            }

                            else if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == (sharingPosdCastAdapter.getItemCount() - 1)) {

                                linearLayoutManager.smoothScrollToPosition(binding.scrollRecycler, new RecyclerView.State(), 0);
                            }
                        }
                    }, 0, time);


                }
            }

            @Override
            public void onFailure(Call<VideoPosdcastResponse> call, Throwable t) {


            }
        });



    }

    public void caseUpdateClick(View view) {
        startActivity(new Intent(ac, CaseUpdatesActivity.class));

    }

 /*   public void visitWebsiteText(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://mark-lawchambers.com"));
        startActivity(intent);
    }*/

    private void getActivities() {
        MainApplication.getApiService().recentActivityMethod("application/json").enqueue(new Callback<RecentResponse>() {
            @Override
            public void onResponse(Call<RecentResponse> call, final Response<RecentResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getRespCode().equals("1003")) {
                        binding.recentActivityRecycler.setLayoutManager(new LinearLayoutManager(ac, LinearLayoutManager.HORIZONTAL, false));
                        recentActivityAdapter = new RecentActivityAdapter(ac, response.body().getPosts_data());
                        binding.recentActivityRecycler.setAdapter(recentActivityAdapter);








                    /*    binding.scrollRecycler.setLayoutManager(new LinearLayoutManager(ac, LinearLayoutManager.HORIZONTAL, false));
                        recentActivityAdapter = new RecentActivityAdapter(ac, response.body().getPosts_data());
                        binding.scrollRecycler.setAdapter(recentActivityAdapter);
                        binding.scrollRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                                super.onScrolled(recyclerView, dx, dy);

                                firstItem=layoutManager.findFirstVisibleItemPosition();

                                if (firstItem != 1 && firstItem % response.body().getPosts_data().size() == 1) {
                                    layoutManager.scrollToPosition(1);
                                }
                                 firstCompletelyItemVisible = layoutManager.findFirstCompletelyVisibleItemPosition();
                                if (firstCompletelyItemVisible == 0) {
                                    layoutManager.scrollToPositionWithOffset(response.body().getPosts_data().size(), 0);
                                }
                            }
                        });*/





/*
                        final int speedScroll = 2000;
                        final Handler handler = new Handler();
                        final Runnable runnable = new Runnable() {
                            int count = 0;
                            @Override
                            public void run() {

                                Log.e("coount","--------------"+count);
                                if(count < response.body().getPosts_data().size()){
                                    binding.scrollRecycler.scrollToPosition(++count);
                                    handler.postDelayed(this,speedScroll);
                                }


                            else if(count==response.body().getPosts_data().size()){

                                    binding.scrollRecycler.scrollToPosition(0);
                                    handler.postDelayed(this,speedScroll);
                                }


                            }
                        };

                        handler.postDelayed(runnable,speedScroll);*/


                    } else {
                        Toast.makeText(ac, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RecentResponse> call, Throwable t) {
                Toast.makeText(ac, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void settingLn(View view) {
        startActivity(new Intent(HomeActivity.this, SettingActivity.class));
        finish();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void reachUs(View view){
      startActivity(new Intent(HomeActivity.this,ReachUsActivity.class));
    }
}
