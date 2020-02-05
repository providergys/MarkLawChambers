package com.demo.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.demo.R;
import com.demo.activities.ProductDetail;

import java.util.ArrayList;




/**
 * Created by AndroidDev on 21-Dec-18.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {


    Context context;
    ArrayList<String> expert_name=new ArrayList<String>();
    ArrayList<String> expert_message=new ArrayList<String>();
    ArrayList<String> dateList =new ArrayList<String>();



    private int[] expert_images = {
            R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,
    };

    public ProductAdapter(Context context, ArrayList<String> expert_name, ArrayList<String> expert_message,ArrayList<String> dateList ) {
        this.expert_name=expert_name;
        this.expert_message=expert_message;
        this.dateList=dateList;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

                holder.listMain.setText(expert_name.get(position));
                holder.listType.setText(expert_message.get(position));
                 holder.listdate.setText(dateList.get(position));

                 holder.listdate.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Intent productDetail = new Intent(context , ProductDetail.class);
                         context.startActivity(productDetail);
                     }
                 });




    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView listdate,listType,listMain;


        public MyViewHolder(View itemView) {
            super(itemView);
            listdate = (TextView) itemView.findViewById(R.id.listdate);
            listType = (TextView) itemView.findViewById(R.id.listType);
            listMain = (TextView) itemView.findViewById(R.id.listMain);

//            online_status = (ImageView) itemView.findViewById(R.id,online_status);
        }
    }


    @Override
    public int getItemCount() {
        return expert_name.size() ;
    }
}
