package com.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.demo.activities.TeamDetail;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.TeamItemListBinding;
import com.demo.model.TeamResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import id.zelory.compressor.Compressor;
import retrofit2.http.Url;

import static com.facebook.FacebookSdk.getApplicationContext;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {
    Context context;
    List<TeamResponse.TeampostsBean> listdata;

    public TeamAdapter(Context ac, List<TeamResponse.TeampostsBean> listdata) {
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
        final TeamResponse.TeampostsBean myListData = listdata.get(position);
        holder.binding.teamTitleText.setText(myListData.getTitle());
        holder.binding.teamdescText.setText(myListData.getContent());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.binding.teamdescText.setText(Html.fromHtml((myListData.getContent()), Html.FROM_HTML_MODE_COMPACT));
        }

        RequestOptions myOptions = new RequestOptions()
                .override(100, 100);

        Glide.with(context)
                .asBitmap()
                .apply(myOptions)
                .load(myListData.getImage())
                .into(holder.binding.teamProfileImage);


     /*   Drawable mDefaultBackground = context.getResources().getDrawable(R.drawable.placeholder);
        Glide.with(getApplicationContext()).load(myListData.getImage()).centerCrop()
                .error(mDefaultBackground).into(holder.binding.teamProfileImage);*/



        holder.binding.teamreadText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, TeamDetail.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .putExtra("teamImage",myListData.getImage()).putExtra("Description",myListData.getContent()));
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

