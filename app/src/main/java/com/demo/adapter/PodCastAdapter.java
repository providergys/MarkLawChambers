package com.demo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demo.activities.TeamDetail;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.PodcastItemListBinding;
import com.demo.marklaw.databinding.TeamItemListBinding;
import com.demo.model.SharingResponse;
import com.demo.model.TeamResponse;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class PodCastAdapter extends RecyclerView.Adapter<PodCastAdapter.ViewHolder> {
    Context context;
    List<SharingResponse.PodcastpostsBean> listdata;


    public PodCastAdapter(Context ac, List<SharingResponse.PodcastpostsBean> listdata) {
        this.context = ac;
        this.listdata = listdata;
    }


    @Override
    public PodCastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PodcastItemListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.podcast_item_list, parent, false);
        PodCastAdapter.ViewHolder viewHolder = new PodCastAdapter.ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PodCastAdapter.ViewHolder holder, int position) {
         SharingResponse.PodcastpostsBean myListData = listdata.get(position);
        holder.binding.podCastText.setText(myListData.getTitle());

        Drawable mDefaultBackground = context.getResources().getDrawable(R.drawable.placeholder);
        Glide.with(getApplicationContext()).load(myListData.getImage()).centerCrop()
                .error(mDefaultBackground).into(holder.binding.podCastImage);


    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final PodcastItemListBinding binding;

        ViewHolder(final PodcastItemListBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}