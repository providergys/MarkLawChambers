package com.demo.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.SharepodcastItemListBinding;
import com.demo.model.PodCastList;


import static com.facebook.FacebookSdk.getApplicationContext;

public class SharingPosdCastAdapter extends RecyclerView.Adapter<SharingPosdCastAdapter.ViewHolder> {
    Context context;
    PodCastList[] listdata;

    public SharingPosdCastAdapter(Context ac, PodCastList[] listdata) {
        this.context = ac;
        this.listdata = listdata;
    }

    @Override
    public SharingPosdCastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         SharepodcastItemListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.sharepodcast_item_list, parent, false);
        SharingPosdCastAdapter.ViewHolder viewHolder = new SharingPosdCastAdapter.ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SharingPosdCastAdapter.ViewHolder holder, int position) {
        PodCastList myListData = listdata[position];
        holder.binding.podCastTitle.setText(myListData.getmTitle());
        holder.binding.podCastDate.setText(myListData.getmDate());
        Drawable mDefaultBackground = context.getResources().getDrawable(R.drawable.placeholder);
        Glide.with(getApplicationContext()).load(myListData.getmImage()).centerCrop()
                .error(mDefaultBackground).into(holder.binding.podCastImage);
    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final SharepodcastItemListBinding binding;

        ViewHolder(final SharepodcastItemListBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}