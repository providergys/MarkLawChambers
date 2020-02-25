package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

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
import com.demo.marklaw.databinding.ActivityReachUsBinding;
import com.demo.utility.Constants;
import com.demo.utility.UserSharedPreferences;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class ReachUsActivity extends AppCompatActivity {
    ActivityReachUsBinding binding;
    Activity ac;
    AwesomeValidation mAwesomeValidation;
    UserSharedPreferences mSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reach_us);
        init();

    }

    private void init() {
        ac = ReachUsActivity.this;
        mSharedPref = new UserSharedPreferences(this);
        binding.useEmailReach.setText(mSharedPref.getString(Constants.USER_EMAIL));
        mAwesomeValidation = new AwesomeValidation(BASIC);
        validations();
    }

    private void validations() {
        mAwesomeValidation.addValidation(ac, R.id.useNameReach, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        mAwesomeValidation.addValidation(ac, R.id.useEmailReach, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        mAwesomeValidation.addValidation(ac, R.id.userNumberReach, RegexTemplate.NOT_EMPTY, R.string.invalid_number);
        mAwesomeValidation.addValidation(ac, R.id.usermessagereach, RegexTemplate.NOT_EMPTY, R.string.invalid_message);
    }



    // clicks
    public void reachUsSubmit(View view) {
        if (mAwesomeValidation.validate()) {
            if (!binding.useEmailReach.getText().toString().equals(mSharedPref.getString(Constants.USER_EMAIL))) {
                Toast.makeText(ac, "Please Enter the Email from  you are Currently Login", Toast.LENGTH_LONG).show();
            } else {
                startActivity(new Intent(ReachUsActivity.this, HomeActivity.class));
            }
        }
    }
    public void back(View view) {
        startActivity(new Intent(ac, HomeActivity.class));
    }

}
