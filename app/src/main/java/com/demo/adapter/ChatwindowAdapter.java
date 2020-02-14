package com.demo.adapter;

import android.content.Context;
import android.graphics.Color;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.demo.marklaw.R;
import com.demo.model.ChatMessage;

import java.util.List;


/**
 * Created by AndroidDev on 21-Dec-18.
 */

public class ChatwindowAdapter extends RecyclerView.Adapter<ChatwindowAdapter.MyViewHolder> {


    Context context;
   List<ChatMessage> chats =null;

    public ChatwindowAdapter(Context context, List<ChatMessage> chats) {
        this.context = context;
        this.chats= chats;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.message_layout, parent, false);

        return new MyViewHolder(view);
    }
    @Override
    public int getItemViewType(int position)
    {
        return position;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

            if (chats.get(position).getIssender()) {
                holder.message_body.setText(chats.get(position).getMessages().trim());
                holder.messageDate.setVisibility(View.GONE);
                holder.message_body.setTextColor(Color.parseColor("#ffffff"));
                holder.message_body.setBackgroundResource(R.drawable.sender_message);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.message_body.getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);

                RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) holder.messageDate.getLayoutParams();
                params1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);

            } else {

                holder.messageDate.setVisibility(View.VISIBLE);
                holder.message_body.setText(chats.get(position).getMessages().trim());
                holder.message_body.setTextColor(Color.parseColor("#000000"));
                holder.message_body.setBackgroundResource(R.drawable.reciver_message);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.message_body.getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);

                RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) holder.messageDate.getLayoutParams();
                params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            }



    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView message_body,messageDate;


        public MyViewHolder(View itemView) {
            super(itemView);
            message_body = (TextView) itemView.findViewById(R.id.message_body);
            messageDate = (TextView) itemView.findViewById(R.id.messageDate);
        }
    }


    @Override
    public int getItemCount() {
        return chats.size();
    }
}
