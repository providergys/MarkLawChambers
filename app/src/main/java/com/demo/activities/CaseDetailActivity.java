package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.demo.adapter.CaseDetailAdapter;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.ActivityCaseDetailBinding;
import com.demo.model.CasesResponse;

import java.util.ArrayList;

public class CaseDetailActivity extends AppCompatActivity {
    ActivityCaseDetailBinding binding;
    CaseDetailAdapter caseUpdateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_case_detail);
        binding.caseDetailText.setText(getIntent().getStringExtra("caseTitle"));
        Bundle bundle = getIntent().getExtras();
        ArrayList<? extends CasesResponse.PostsDataBean.CasesBean> arraylist = bundle.getParcelableArrayList("selectedContacts");
        binding.caseDetailRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        caseUpdateAdapter = new CaseDetailAdapter(getApplicationContext(), arraylist);
        binding.caseDetailRecycler.setAdapter(caseUpdateAdapter);


    }
    public void back(View view){
        finish();
    }
}
