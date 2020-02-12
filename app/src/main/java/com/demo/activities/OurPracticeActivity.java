package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.demo.adapter.PracticeAdapter;
import com.demo.adapter.TeamAdapter;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.ActivityOurPracticeBinding;
import com.demo.retroutility.MainApplication;
import com.demo.retroutility.PracticeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OurPracticeActivity extends AppCompatActivity {
    ActivityOurPracticeBinding binding;
    PracticeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_our_practice);

        MainApplication.getApiService().getPracticeLaw("application/json").enqueue(new Callback<PracticeResponse>() {
            @Override
            public void onResponse(Call<PracticeResponse> call, Response<PracticeResponse> response) {
                if(response.isSuccessful()) {
                    if (response.body().getRespCode().equals("1003")) {
                        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 3, GridLayoutManager.VERTICAL, false);
                        adapter = new PracticeAdapter(getApplicationContext(), response.body().getPosts_data());
                        binding.practiceRecycler.setLayoutManager(manager);
                        binding.practiceRecycler.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<PracticeResponse> call, Throwable t) {

            }
        });

    }

    public void back(View view) {
        finish();
    }
}
