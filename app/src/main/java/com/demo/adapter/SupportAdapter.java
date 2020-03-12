package com.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
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

public class SupportAdapter extends RecyclerView.Adapter<SupportAdapter.ViewHolder> {
    Context context;
    List<TeamResponse.SupportingpostsBean> listdata;

    public SupportAdapter(Context ac, List<TeamResponse.SupportingpostsBean> listdata) {
        this.context = ac;
        this.listdata = listdata;
    }

    @Override
    public SupportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TeamItemListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.team_item_list, parent, false);
        SupportAdapter.ViewHolder viewHolder = new SupportAdapter.ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SupportAdapter.ViewHolder holder, int position) {
     final TeamResponse.SupportingpostsBean myListData = listdata.get(position);
        holder.binding.teamTitleText.setText(myListData.getTitle());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.binding.teamdescText.setText(Html.fromHtml((myListData.getContent()), Html.FROM_HTML_MODE_COMPACT));
        }
        holder.binding.teamreadText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, TeamDetail.class)
                        .putExtra("teamImage",myListData.getImage()).putExtra("Description",myListData.getContent()));
            }
        });

        Drawable mDefaultBackground = context.getResources().getDrawable(R.drawable.placeholder);
        Glide.with(getApplicationContext()).load(myListData.getImage()).centerCrop()
                .error(mDefaultBackground).into(holder.binding.teamProfileImage);
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