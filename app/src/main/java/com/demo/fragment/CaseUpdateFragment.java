package com.demo.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.demo.adapter.CaseUpdateAdapter;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.ActivityCaseUpdateFragmentBinding;
import com.demo.model.MyCasesModel;
import com.demo.retroutility.MainApplication;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CaseUpdateFragment extends Fragment {
    ActivityCaseUpdateFragmentBinding binding;
    CaseUpdateAdapter caseUpdateAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_case_update_fragment, container, false);
        View view = binding.getRoot();
        getCasesByServer();
        return view;
    }
    private void getCasesByServer() {
        MainApplication.getApiService().getCases("application/json").enqueue(new Callback<MyCasesModel>() {
            @Override
            public void onResponse(Call<MyCasesModel> call, Response<MyCasesModel> response) {
                if(response.isSuccessful()) {

                    if (response.body().getRespCode().equals("1003")) {

                        binding.caseRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                        caseUpdateAdapter = new CaseUpdateAdapter(getActivity(), response.body().getPosts_data());
                        binding.caseRecycler.setAdapter(caseUpdateAdapter);
                    }
                    else {
                        Toast.makeText(getContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<MyCasesModel> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}