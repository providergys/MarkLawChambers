package com.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demo.activities.PdfViewActivity;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.CaseDetailItemListBinding;
import com.demo.marklaw.databinding.TeamItemListBinding;
import com.demo.model.MyCasesModel;
import com.demo.model.TeamModel;

import java.util.ArrayList;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {
    Context context;
    TeamModel[] listdata;

    public TeamAdapter(Context ac, TeamModel[] listdata) {
        this.context = ac;
        this.listdata = listdata;
    }

    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TeamItemListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.team_item_list, parent, false);
        TeamAdapter.ViewHolder viewHolder = new TeamAdapter.ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TeamAdapter.ViewHolder holder, int position) {
        final  TeamModel myListData = listdata[position];
        holder.binding.teamTitleText.setText(myListData.getmTitle());
        holder.binding.teamdescText.setText(myListData.getmDesc());
        Glide.with(context).load(myListData.getmProfileImage()).into(holder.binding.teamProfileImage);


    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TeamItemListBinding binding;

        ViewHolder(final TeamItemListBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}

