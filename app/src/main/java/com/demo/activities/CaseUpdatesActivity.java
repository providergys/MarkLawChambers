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

        MainApplication.getApiService().getCasesMethod("application/json").enqueue(new Callback<CasesResponse>() {
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
    // loadFragment(new CaseUpdateFragment());


    public void back(View view) {
        finish();
    }


  /*  private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.caseFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }*/

    public void settingLn(View view) {
        startActivity(new Intent(CaseUpdatesActivity.this, SettingActivity.class));
    }

    public void home(View view) {
        startActivity(new Intent(CaseUpdatesActivity.this, HomeActivity.class));
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

                      /*  binding.transactionRecycler.setLayoutManager(new LinearLayoutManager(ac, LinearLayoutManager.VERTICAL, false));
                        TransacionHistoryAdapter caseUpdateAdapter = new TransacionHistoryAdapter(ac, response.body().getInvoices().get(0).getPaid_amount());
                        binding.transactionRecycler.setAdapter(caseUpdateAdapter);
                        binding.amountPay.setText(response.body().getInvoices().get(0).getInvoice_amount());
                        binding.amountPending.setText(response.body().getInvoices().get(0).getPending_amount());
                        binding.payBtn.setVisibility(View.VISIBLE);
                        binding.amountPay.setVisibility(View.VISIBLE);
                        binding.invoicetext.setVisibility(View.VISIBLE);
                        binding.pendingText.setVisibility(View.VISIBLE);
                        binding.amountPending.setVisibility(View.VISIBLE);
                        binding.pdfView.setVisibility(View.VISIBLE);



                        binding.pdfView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View  view) {

                                Log.e("pdfOnVoice",""+response.body().getInvoices().get(0).getInvoice_detail());


                                Intent intent=new Intent(CaseUpdatesActivity.this, PdfViewActivity.class);
                                intent.putExtra("pdfLink",response.body().getInvoices().get(0).getInvoice_detail());
                                 startActivity(intent);

                            }
                        });


                        binding.payBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(ac, InVoiceActivity.class);
                                intent.putExtra(Constants.INVOICE_ID, response.body().getInvoices().get(0).getId());
                                intent.putExtra(Constants.INVOICE_NUMBER, response.body().getInvoices().get(0).getInvoice_number());
                                intent.putExtra(Constants.INVOICE_AMOUNT, response.body().getInvoices().get(0).getInvoice_amount());
                                intent.putExtra(Constants.INVOICE_CURRENCY, response.body().getInvoices().get(0).getCurrency());
                                intent.putExtra(Constants.INVOICE_PENDING, response.body().getInvoices().get(0).getPending_amount());
                                startActivity(intent);


                            }
                        });*/

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
