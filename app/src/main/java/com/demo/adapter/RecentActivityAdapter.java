package com.demo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.demo.activities.RecentDetailActivity;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.RecentItemListBinding;
import com.demo.model.RecentResponse;

import java.util.List;

public class RecentActivityAdapter extends RecyclerView.Adapter<RecentActivityAdapter.ViewHolder> {
    Context context;
    List<RecentResponse.PostsDataBean> listdata;

    // RecyclerView recyclerView;
    public RecentActivityAdapter(Activity ac, List<RecentResponse.PostsDataBean> listdata) {
        this.context=ac;
        this.listdata = listdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecentItemListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recent_item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final RecentResponse.PostsDataBean myListData = listdata.get(position);
        holder.binding.titleName.setText(myListData.getTitle());
        Glide.with(context).load(myListData.getImage()).into(holder.binding.titleImage);
        holder.binding.listLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               context.startActivity(new Intent(context, RecentDetailActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("content",myListData.getContent()).putExtra("image",myListData.getImage()));
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {
         final RecentItemListBinding binding;

         ViewHolder(final RecentItemListBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}
