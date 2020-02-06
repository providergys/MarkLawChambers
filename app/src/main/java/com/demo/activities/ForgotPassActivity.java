package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.demo.marklaw.databinding.ActivityForgotPassBinding;
import com.demo.marklaw.R;
import com.demo.model.ForgotPassResponse;
import com.demo.model.LoginRequest;
import com.demo.retroutility.MainApplication;
import com.demo.utility.ProgDialog;
import com.facebook.login.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class ForgotPassActivity extends AppCompatActivity {
    ActivityForgotPassBinding binding;
    Activity ac;
    AwesomeValidation mAwesomeValidation;
    ProgDialog prog = new ProgDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_pass);
        ac = ForgotPassActivity.this;
        mAwesomeValidation = new AwesomeValidation(BASIC);
        validations();

    }

    private void validations() {
        mAwesomeValidation.addValidation(ac, R.id.forgotEmailEdt, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
    }

    public void next(View view) {
        if (mAwesomeValidation.validate()) {
            Toast.makeText(ac, "Success", Toast.LENGTH_LONG).show();
            forgotPass();
        }
    }

    private void forgotPass() {
        LoginRequest loginRequest = new LoginRequest();
        prog.progDialog(ac);
        loginRequest.setEmail(binding.forgotEmailEdt.getText().toString());
        MainApplication.getApiService().forgotPassMethod("application/json", loginRequest).enqueue(new Callback<ForgotPassResponse>() {
            @Override
            public void onResponse(Call<ForgotPassResponse> call, Response<ForgotPassResponse> response) {
                if(response.isSuccessful()){
                    prog.hideProg();
                    if(response.body().getRespCode().equals("1006")){
                        Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();
                    }

                  else if(response.body().getRespCode().equals("7013")){
                        Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();
                        startActivity(new Intent(ac, LoginActivity.class));
                    }


                    else if(response.body().getRespCode().equals("1012")){
                        Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();
                    }


                     else if(response.body().getRespCode().equals("7012")){
                        Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();
                    }

                }

                else {
                    prog.hideProg();
                }
            }

            @Override
            public void onFailure(Call<ForgotPassResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }

    public void back(View view) {
       finish();
    }
}
