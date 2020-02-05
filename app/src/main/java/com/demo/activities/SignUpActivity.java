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
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.demo.R;
import com.demo.databinding.ActivitySignUpBinding;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    Activity ac;
    AwesomeValidation mAwesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        ac = SignUpActivity.this;
        mAwesomeValidation = new AwesomeValidation(BASIC);
        validations();
    }

    private void validations() {
        mAwesomeValidation.addValidation(ac, R.id.userNameEdt, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        mAwesomeValidation.addValidation(ac, R.id.emailEdt, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        mAwesomeValidation.addValidation(ac, R.id.numberEdt, RegexTemplate.NOT_EMPTY, R.string.invalid_number);
        mAwesomeValidation.addValidation(ac, R.id.passEdt, RegexTemplate.NOT_EMPTY, R.string.invalid_password);
    }

    public void signUpButton(View view) {
        if (mAwesomeValidation.validate()) {
            startActivity(new Intent(SignUpActivity.this,HomeActivity.class));
        }
    }


    public void login(View view) {
        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
    }


}
