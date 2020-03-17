package com.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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

import java.io.File;
import java.io.IOException;
import java.util.List;

import id.zelory.compressor.Compressor;

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



        //  Glide.with(context).load(myListData.getImage()).apply(RequestOptions.circleCropTransform()).into(holder.binding.teamProfileImage);

      //  Bitmap bitmap = Bitmap.createScaledBitmap(holder.binding.teamProfileImage, width, height, true);


        File compressedImgFile = null;
        try {
            compressedImgFile = new Compressor(context).compressToFile(new File(myListData.getImage()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        Bitmap compressedImgBitmap = null;
        try {
            compressedImgBitmap = new Compressor(context).compressToBitmap(compressedImgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Drawable mDefaultBackground = context.getResources().getDrawable(R.drawable.placeholder);
        Glide.with(getApplicationContext()).load(compressedImgBitmap).centerCrop()
                .error(mDefaultBackground).into(holder.binding.teamProfileImage);



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

