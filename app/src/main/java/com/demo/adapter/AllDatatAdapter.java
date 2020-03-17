package com.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demo.activities.SharingActivity;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.BothItemListBinding;
import com.demo.marklaw.databinding.PodcastItemListBinding;
import com.demo.model.SharingResponse;
import com.demo.model.VideoPosdcastResponse;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class AllDatatAdapter extends RecyclerView.Adapter<AllDatatAdapter.ViewHolder> {
    Context context;
    List<VideoPosdcastResponse.VideopodcastBean> videosposts;
    public AllDatatAdapter(Context applicationContext, List<VideoPosdcastResponse.VideopodcastBean> videopodcast) {
        this.context=applicationContext;
        this.videosposts=videopodcast;
    }


    @Override
    public AllDatatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BothItemListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.both_item_list, parent, false);
        AllDatatAdapter.ViewHolder viewHolder = new AllDatatAdapter.ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AllDatatAdapter.ViewHolder holder, int position) {
        VideoPosdcastResponse.VideopodcastBean data=videosposts.get(position);


        if(data.getCategory_type().equals("Video")){
            holder.binding.bothLn.setVisibility(View.VISIBLE);


        }


        else {

            holder.binding.bothText.setVisibility(View.VISIBLE);
            holder.binding.bothText.setText(data.getTitle());

        }


        Log.e("images",""+data.getImage());

        Drawable mDefaultBackground = context.getResources().getDrawable(R.drawable.placeholder);
        Glide.with(getApplicationContext()).load(data.getImage()).centerCrop()
                    .error(mDefaultBackground).into(holder.binding.bothImage);











    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            context.startActivity(new Intent(context, SharingActivity.class));
        }
    });
    }


    @Override
    public int getItemCount() {
        return videosposts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final BothItemListBinding binding;

        ViewHolder(final BothItemListBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}