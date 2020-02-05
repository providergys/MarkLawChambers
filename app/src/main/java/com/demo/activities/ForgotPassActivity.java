package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.demo.marklaw.databinding.ActivityForgotPassBinding;
import com.demo.marklaw.R;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class ForgotPassActivity extends AppCompatActivity {
    ActivityForgotPassBinding binding;
    Activity ac;
    AwesomeValidation mAwesomeValidation;

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
        }
    }

    public void back(View view) {
       finish();
    }
}
