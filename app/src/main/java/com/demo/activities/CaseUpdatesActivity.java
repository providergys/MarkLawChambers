package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.demo.fragment.CaseUpdateFragment;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.ActivityCaseUpdatesBinding;

public class CaseUpdatesActivity extends AppCompatActivity {
    ActivityCaseUpdatesBinding binding;
    Activity ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_case_updates);
        binding.messageLn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CaseUpdatesActivity.this,MessageActivity.class));
            }
        });
        init(); }

    private void init() {
        ac = CaseUpdatesActivity.this;
        loadFragment(new CaseUpdateFragment());

    }



    public void back(View view) {
        finish();
    }


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.caseFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void settingLn(View view){
        startActivity(new Intent(CaseUpdatesActivity.this,SettingActivity.class));
    }
    public void home(View view){
        startActivity(new Intent(CaseUpdatesActivity.this,HomeActivity.class));
    }

    public void reachUs(View view){
        Uri uri = Uri.parse("http://182.74.186.138/marklaw/contact-us/"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
