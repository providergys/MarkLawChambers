package com.demo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.activities.CaseDetailActivity;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.CaseItemListBinding;
import com.demo.marklaw.databinding.RecentItemListBinding;
import com.demo.model.MyCasesModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CaseUpdateAdapter extends RecyclerView.Adapter<CaseUpdateAdapter.ViewHolder> {
    Context context;
    List<MyCasesModel.PostsDataBean> listdata;
    public CaseUpdateAdapter(Activity ac, List<MyCasesModel.PostsDataBean> listdata) {
        this.context = ac;
        this.listdata = listdata;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CaseItemListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.case_item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MyCasesModel.PostsDataBean myListData = listdata.get(position);
        String date = myListData.getDate();
        holder.binding.dateText.setText(date.substring(0, date.indexOf(' ')));
        holder.binding.statusText.setText(myListData.getProgress());
        holder.binding.contentText.setText(myListData.getContent());
        holder.binding.caseTitle.setText(myListData.getTitle());
        if (myListData.getProgress().equals("In progress")) {
            holder.binding.statusText.setTextColor(Color.parseColor("#F44336"));
            holder.binding.caseCard.setBackgroundResource(R.drawable.red_card);
        } else {
            holder.binding.statusText.setTextColor(Color.parseColor("#055208"));
            holder.binding.caseCard.setBackgroundResource(R.drawable.green_card);
        }
        holder.binding.caseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CaseDetailActivity.class);
                intent.putExtra("caseTitle",myListData.getTitle());
                Bundle b = new Bundle();
                b.putSerializable("selectedContacts", myListData.getCases());
                intent.putExtras(b);
                context.startActivity(intent);


            }
        });

        Log.e("cases",""+myListData.getCases());
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final CaseItemListBinding binding;

        ViewHolder(final CaseItemListBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}

