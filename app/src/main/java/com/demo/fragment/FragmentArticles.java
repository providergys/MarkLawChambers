package com.demo.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.adapter.ArticlesAdapter;
import com.demo.adapter.PodCastAdapter;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.ActivityFragmentVideoBinding;
import com.demo.model.SharingResponse;
import com.demo.retroutility.MainApplication;
import com.demo.utility.ProgDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

public class FragmentArticles extends Fragment {
    ActivityFragmentVideoBinding binding;
    ArticlesAdapter sharingPosdCastAdapter;
    ProgDialog prog = new ProgDialog();
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_fragment_video, container, false);
        View view = binding.getRoot(); prog.progDialog(getActivity());

        MainApplication.getApiService().getSharing("application/json").enqueue(new Callback<SharingResponse>() {
            @Override
            public void onResponse(Call<SharingResponse> call, Response<SharingResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getRespCode().equals("1003")) {
                        // video data.
                        prog.hideProg();
                        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
                        sharingPosdCastAdapter = new ArticlesAdapter(getApplicationContext(), response.body().getArticalposts());
                        binding.videoRecycler.setLayoutManager(manager);
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





}