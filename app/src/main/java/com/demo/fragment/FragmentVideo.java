package com.demo.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.adapter.CaseUpdateAdapter;
import com.demo.adapter.PracticeAdapter;
import com.demo.adapter.SharingPosdCastAdapter;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.ActivityCaseUpdateFragmentBinding;
import com.demo.marklaw.databinding.ActivityFragmentVideoBinding;
import com.demo.model.PodCastList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class FragmentVideo extends Fragment {
    ActivityFragmentVideoBinding binding;
    SharingPosdCastAdapter sharingPosdCastAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_fragment_video, container, false);
        View view = binding.getRoot();
         getVideo();
        return view;
    }


    private void getVideo() {
        PodCastList[] podCastList = new PodCastList[]{
                new PodCastList("Asia Ceo Forum 2019", "August 17,2019", R.drawable.activity1),
                new PodCastList("Asia Ceo Forum 2019", "August 17,2019", R.drawable.activity2),
                new PodCastList("Asia Ceo Forum 2019", "August 17,2019", R.drawable.activity1),
                new PodCastList("Asia Ceo Forum 2019", "August 17,2019", R.drawable.activity2),
                new PodCastList("Asia Ceo Forum 2019", "August 17,2019", R.drawable.activity1),
                new PodCastList("Asia Ceo Forum 2019", "August 17,2019", R.drawable.activity2),};


        // video data
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        sharingPosdCastAdapter = new SharingPosdCastAdapter(getApplicationContext(), podCastList);
        binding.videoRecycler.setLayoutManager(manager);
        binding.videoRecycler.setAdapter(sharingPosdCastAdapter);

    }

}