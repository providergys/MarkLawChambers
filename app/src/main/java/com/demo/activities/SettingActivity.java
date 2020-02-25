package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.ActivitySettingBinding;
import com.demo.model.ChangePassResponse;
import com.demo.model.LoginRequest;
import com.demo.retroutility.MainApplication;
import com.demo.utility.Constants;
import com.demo.utility.ProgDialog;
import com.demo.utility.UserSharedPreferences;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;

import java.util.Arrays;

import kotlin.text.Regex;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class SettingActivity extends AppCompatActivity {
    ActivitySettingBinding binding;
    Activity ac;
    AwesomeValidation mAwesomeValidation;
    ProgDialog prog = new ProgDialog();
    UserSharedPreferences mSharedPref;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        ac = SettingActivity.this;
        mAwesomeValidation = new AwesomeValidation(BASIC);
        mSharedPref = new UserSharedPreferences(ac);
        binding.nameTextView.setText("Hi Welcome !" + " " + mSharedPref.getString(Constants.USER_NAME));
        validations();
        callbackManager = CallbackManager.Factory.create();
        boolean loggedOut = AccessToken.getCurrentAccessToken() == null;
        if (!loggedOut) {
            Log.e("helloSee", "login");
            binding.logoutText.setVisibility(View.GONE);
            binding.fbLn.setVisibility(View.VISIBLE);
            binding.logoutFacebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mSharedPref.clear();
                    LoginManager.getInstance().logOut();
                    startActivity(new Intent(SettingActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    finish();
                }
            });

        } else {
            binding.logoutText.setVisibility(View.VISIBLE);

            binding.fbLn.setVisibility(View.GONE);
            Log.e("helloSee", "logout");

        }
    }


    public void changePass(View view) {
        binding.settingMainLn.setVisibility(View.GONE);
        binding.passLn.setVisibility(View.VISIBLE);
    }


    private void validations() {
        mAwesomeValidation.addValidation(ac, R.id.emailPassEdt, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        mAwesomeValidation.addValidation(ac, R.id.currentPassEdt, RegexTemplate.NOT_EMPTY, R.string.invalid_email);
        mAwesomeValidation.addValidation(ac, R.id.newPassEdt, RegexTemplate.NOT_EMPTY, R.string.invalid_password);
        mAwesomeValidation.addValidation(ac, R.id.renewPassEdt, RegexTemplate.NOT_EMPTY, R.string.invalid_password);
    }

    public void next(View view) {
        if (mAwesomeValidation.validate()) {
            changePassword();
        }
    }

    private void changePassword() {


        if (!binding.renewPassEdt.getText().toString().equals(binding.newPassEdt.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Password Des not Match", Toast.LENGTH_LONG).show();
        } else {
            LoginRequest loginRequest = new LoginRequest();
            prog.progDialog(ac);
            loginRequest.setCurrentpassword(binding.currentPassEdt.getText().toString());
            loginRequest.setNewpassword(binding.newPassEdt.getText().toString());
            loginRequest.setEmail(binding.emailPassEdt.getText().toString());

            MainApplication.getApiService().changePassMethod("application/json", loginRequest).enqueue(new Callback<ChangePassResponse>() {
                @Override
                public void onResponse(Call<ChangePassResponse> call, Response<ChangePassResponse> response) {
                    if (response.isSuccessful()) {
                        prog.hideProg();
                        if (response.body().getRespCode().equals("1018")) {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                            startActivity(new Intent(SettingActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            finish();
                        } else if (response.body().getRespCode().equals("1006")) {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ChangePassResponse> call, Throwable t) {
                    // Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    public void logout(View view) {
        mSharedPref.clear();
        startActivity(new Intent(SettingActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();

    }

    public void back(View view) {
        startActivity(new Intent(SettingActivity.this, HomeActivity.class));

    }

}
