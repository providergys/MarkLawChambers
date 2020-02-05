package com.demo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;
import androidx.databinding.DataBindingUtil;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.demo.R;
import com.demo.databinding.ActivityLoginBinding;
import com.demo.model.LoginRequest;
import com.demo.model.LoginResponse;
import com.demo.retroutility.MainApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class LoginActivity extends Activity {
    Activity ac;
    AwesomeValidation mAwesomeValidation;
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        init();
    }

    private void init() {
        ac = LoginActivity.this;
        mAwesomeValidation = new AwesomeValidation(BASIC);
        validations();

    }

    private void validations() {
        mAwesomeValidation.addValidation(ac, R.id.emailEdit, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        mAwesomeValidation.addValidation(ac, R.id.passEdit, RegexTemplate.NOT_EMPTY, R.string.invalid_password);
    }

    public void signBtn(View view) {
        if (mAwesomeValidation.validate()) {
            // Here, we are sure that form is successfully validated. So, do your stuffs now...

            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
           // retrofitLogin();
        }
    }

    public void signUpBtn(View view) {
       startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
    }

    public void forgotPassword(View view) {
        startActivity(new Intent(LoginActivity.this,ForgotPassActivity.class));
    }



    /*public void retrofitLogin() {


        LoginRequest loginRequest=new LoginRequest();

        //Show Your Progress Dialog



        MainApplication.getApiService().loginMethod("application/json", loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {


                    if (response.body().getStatusCode()==200) {
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));

                    } else {

                    }

                } else {




                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {




                //   snakeBaar.showSnackBar(ac, "Something went wrong..", login_Layout);

            }
        });
    }*/
}
