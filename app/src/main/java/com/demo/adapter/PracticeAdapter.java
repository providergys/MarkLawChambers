package com.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.PracticeItemListBinding;
import com.demo.retroutility.PracticeResponse;

import java.util.List;

public class PracticeAdapter extends RecyclerView.Adapter<PracticeAdapter.ViewHolder> {
    Context context;
    List<PracticeResponse.PostsDataBean> listdata;

    public PracticeAdapter(Context ac, List<PracticeResponse.PostsDataBean> listdata) {
        this.context = ac;
        this.listdata = listdata;
    }

    @Override
    public PracticeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PracticeItemListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.practice_item_list, parent, false);
        PracticeAdapter.ViewHolder viewHolder = new PracticeAdapter.ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PracticeAdapter.ViewHolder holder, int position) {
        PracticeResponse.PostsDataBean myListData = listdata.get(position);
        holder.binding.practiceTitleText.setText(myListData.getTitle());
        if (myListData.isImage()) {
            Glide.with(context).load(myListData.isImage()).into(holder.binding.practiceImage);

        } else {
            Glide.with(context).load(R.drawable.placeholder).into(holder.binding.practiceImage);
        }
        holder.binding.practiceMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final PracticeItemListBinding binding;

        ViewHolder(final PracticeItemListBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}



