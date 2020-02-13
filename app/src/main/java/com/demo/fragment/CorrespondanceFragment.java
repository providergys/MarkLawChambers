package com.demo.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.marklaw.R;
import com.demo.marklaw.databinding.ActivityCorrespondanceFragmentBinding;

public class CorrespondanceFragment extends Fragment {
    ActivityCorrespondanceFragmentBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_correspondance_fragment, container, false);
        View view = binding.getRoot();
        return view;
    }
}
