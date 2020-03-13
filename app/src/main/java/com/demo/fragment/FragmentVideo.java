package com.demo.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.adapter.SharingPosdCastAdapter;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.ActivityFragmentVideoBinding;
import com.demo.model.SharingResponse;
import com.demo.retroutility.MainApplication;
import com.demo.utility.ProgDialog;
import com.facebook.CallbackManager;
import com.facebook.share.widget.ShareDialog;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

public class FragmentVideo extends Fragment {
    ActivityFragmentVideoBinding binding;
    SharingPosdCastAdapter sharingPosdCastAdapter;

    ProgDialog prog = new ProgDialog();
    CallbackManager callbackManager;
    ShareDialog shareDialog;



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_fragment_video, container, false);
        View view = binding.getRoot();

        callbackManager = CallbackManager.Factory.create();

        shareDialog = new ShareDialog(this);
        prog.progDialog(getActivity());
        MainApplication.getApiService().getSharing("application/json").enqueue(new Callback<SharingResponse>() {
            @Override
            public void onResponse(Call<SharingResponse> call, Response<SharingResponse> response) {
                if (response.isSuccessful()) {

                    if(response.body().getRespCode().equals("1003")) {

                        prog.hideProg();
                        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 1, GridLayoutManager.VERTICAL, false);
                        sharingPosdCastAdapter = new SharingPosdCastAdapter(getActivity(), response.body().getVideosposts(), callbackManager, shareDialog);
                        binding.videoRecycler.setLayoutManager(manager);
                        binding.videoRecycler.setHasFixedSize(true);
                        binding.videoRecycler.setAdapter(sharingPosdCastAdapter);
                    }



                }
            }

            @Override
            public void onFailure(Call<SharingResponse> call, Throwable t) {


            }
        });
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);    }
}




