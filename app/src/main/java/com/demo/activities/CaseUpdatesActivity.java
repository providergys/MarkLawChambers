package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.demo.adapter.CaseUpdateAdapter;
import com.demo.adapter.InVoiceAdapter;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.ActivityCaseUpdatesBinding;
import com.demo.model.CasesResponse;
import com.demo.model.GetInvoiceResponse;
import com.demo.model.LoginRequest;
import com.demo.retroutility.MainApplication;
import com.demo.utility.Constants;
import com.demo.utility.UserSharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CaseUpdatesActivity extends AppCompatActivity {
    ActivityCaseUpdatesBinding binding;
    Activity ac;
    CaseUpdateAdapter caseUpdateAdapter;
    UserSharedPreferences mSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_case_updates);
        ac = CaseUpdatesActivity.this;
        mSharedPref = new UserSharedPreferences(this);
        binding.caseRecycler.setVisibility(View.VISIBLE);
        getInVoiceByServer();
        binding.messageLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CaseUpdatesActivity.this, MessageActivity.class));
            }
        });

        binding.casesLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.caseRecycler.setVisibility(View.VISIBLE);
                binding.payLinear.setVisibility(View.GONE);
            }
        });


        binding.invoicesLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.caseRecycler.setVisibility(View.GONE);
                binding.payLinear.setVisibility(View.VISIBLE);
            }
        });


        init();
    }

    private void init() {

        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setUser_id(mSharedPref.getString(Constants.USER_ID));

        MainApplication.getApiService().getCasesMethod("application/json",loginRequest).enqueue(new Callback<CasesResponse>() {
            @Override
            public void onResponse(Call<CasesResponse> call, Response<CasesResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body().getRespCode().equals("1003")) {

                        binding.caseRecycler.setLayoutManager(new LinearLayoutManager(ac, LinearLayoutManager.VERTICAL, false));
                        caseUpdateAdapter = new CaseUpdateAdapter(ac, response.body().getPosts_data());
                        binding.caseRecycler.setAdapter(caseUpdateAdapter);
                    } else {
                        Toast.makeText(ac, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CasesResponse> call, Throwable t) {
                Toast.makeText(ac, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }



    public void back(View view) {
        finish();
    }


    public void settingLn(View view) {
        startActivity(new Intent(CaseUpdatesActivity.this, SettingActivity.class));
    }

    public void home(View view) {
        startActivity(new Intent(CaseUpdatesActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    public void reachUs(View view) {
        startActivity(new Intent(CaseUpdatesActivity.this, ReachUsActivity.class));
    }


    private void getInVoiceByServer() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUser_id(mSharedPref.getString(Constants.USER_ID));
        MainApplication.getApiService().getInvoiceMethod("application/json", loginRequest).enqueue(new Callback<GetInvoiceResponse>() {
            @Override
            public void onResponse(Call<GetInvoiceResponse> call, final Response<GetInvoiceResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body()!=null &&response.body().getRespCode().equals("7019")) {



                        binding.invoiceRecycler.setLayoutManager(new LinearLayoutManager(ac, LinearLayoutManager.VERTICAL, false));
                        InVoiceAdapter caseUpdateAdapter = new InVoiceAdapter(ac, response.body().getInvoices());
                        binding.invoiceRecycler.setAdapter(caseUpdateAdapter);



                    } else if (response.body().getRespCode().equals("7021")) {
                        //    Toast.makeText(ac, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        //Toast.makeText(ac, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    //Toast.makeText(ac, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<GetInvoiceResponse> call, Throwable t) {
               // Toast.makeText(ac, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}
