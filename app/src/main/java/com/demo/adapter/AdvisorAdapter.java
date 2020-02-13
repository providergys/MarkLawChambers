package com.demo.adapter;

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
import com.demo.marklaw.databinding.TeamItemListBinding;
import com.demo.model.TeamResponse;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class AdvisorAdapter extends RecyclerView.Adapter<AdvisorAdapter.ViewHolder> {
    Context context;
    List<TeamResponse.AdvisorpostsBean> listdata;

    public AdvisorAdapter(Context ac, List<TeamResponse.AdvisorpostsBean> listdata) {
        this.context = ac;
        this.listdata = listdata;
    }

    @Override
    public AdvisorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TeamItemListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.team_item_list, parent, false);
        AdvisorAdapter.ViewHolder viewHolder = new AdvisorAdapter.ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AdvisorAdapter.ViewHolder holder, int position) {
       final TeamResponse.AdvisorpostsBean myListData = listdata.get(position);
        holder.binding.teamTitleText.setText(myListData.getTitle());
        holder.binding.teamdescText.setText(myListData.getContent());

        holder.binding.teamreadText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, TeamDetail.class)
                        .putExtra("teamImage",myListData.getImage()).putExtra("Description",myListData.getContent()));

                Drawable mDefaultBackground = context.getResources().getDrawable(R.drawable.placeholder);
                Glide.with(getApplicationContext()).load(myListData.getImage()).centerCrop()
                        .error(mDefaultBackground).into(holder.binding.teamProfileImage);

            }
        });

    }


    @Override
    public int getItemCount() {
        return listdata.size() ;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TeamItemListBinding binding;

        ViewHolder(final TeamItemListBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}