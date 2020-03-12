package com.demo.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.PodcastItemListBinding;
import com.demo.model.SharingResponse;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {
    Context context;
    List<SharingResponse.ArticalpostsBean> listdata;


    public ArticlesAdapter(Context ac, List<SharingResponse.ArticalpostsBean> listdata) {
        this.context = ac;
        this.listdata = listdata;
    }


    @Override
    public ArticlesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PodcastItemListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.podcast_item_list, parent, false);
        ArticlesAdapter.ViewHolder viewHolder = new ArticlesAdapter.ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ArticlesAdapter.ViewHolder holder, int position) {
        SharingResponse.ArticalpostsBean myListData = listdata.get(position);
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