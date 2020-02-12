package com.demo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.activities.PdfViewActivity;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.CaseDetailItemListBinding;
import com.demo.model.MyCasesModel;

import java.util.ArrayList;

public class CaseDetailAdapter extends RecyclerView.Adapter<CaseDetailAdapter.ViewHolder> {
    Context context;
    ArrayList<? extends MyCasesModel.PostsDataBean.CasesBean> listdata;

    public CaseDetailAdapter(Context ac, ArrayList<? extends MyCasesModel.PostsDataBean.CasesBean> listdata) {
        this.context = ac;
        this.listdata = listdata;
    }

    @Override
    public CaseDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CaseDetailItemListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.case_detail_item_list, parent, false);
        CaseDetailAdapter.ViewHolder viewHolder = new CaseDetailAdapter.ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CaseDetailAdapter.ViewHolder holder, int position) {
        final MyCasesModel.PostsDataBean.CasesBean myListData = listdata.get(position);
        holder.binding.dateTextView.setText(myListData.getDate());
        holder.binding.contentTextView.setText(myListData.getCase_studies());

        holder.binding.pdfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, PdfViewActivity.class);
                intent.putExtra("pdfLink",myListData.getFile());
                context.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final CaseDetailItemListBinding binding;

        ViewHolder(final CaseDetailItemListBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}
