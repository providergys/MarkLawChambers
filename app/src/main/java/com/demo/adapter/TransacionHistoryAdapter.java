package com.demo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.activities.CaseDetailActivity;
import com.demo.activities.PdfViewActivity;
import com.demo.activities.ReceiptActivity;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.CaseItemListBinding;
import com.demo.marklaw.databinding.TransHistoryItemListBinding;
import com.demo.model.CasesResponse;
import com.demo.model.GetInvoiceResponse;

import java.util.List;

public class TransacionHistoryAdapter extends RecyclerView.Adapter<TransacionHistoryAdapter.ViewHolder> {
   Context context;
    List<GetInvoiceResponse.InvoicesBean.InvoiceDetailBean.PaidAmountBean> listdata;

    public TransacionHistoryAdapter(Context context, List<GetInvoiceResponse.InvoicesBean.InvoiceDetailBean.PaidAmountBean> paid_amount) {
        this.context = context;
        this.listdata = paid_amount;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TransHistoryItemListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.trans_history_item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final GetInvoiceResponse.InvoicesBean.InvoiceDetailBean.PaidAmountBean myListData = listdata.get(position);





        if(myListData.getAmount().isEmpty()){
            holder.binding.amountCheck.setVisibility(View.GONE);
            holder.binding.historyAmount.setVisibility(View.GONE);
            holder.binding.historyDate.setVisibility(View.GONE);
            holder.binding.receiptText.setVisibility(View.GONE);

        }


        else {
            holder.binding.amountCheck.setVisibility(View.VISIBLE);
            holder.binding.historyAmount.setText(myListData.getAmount());
            holder.binding.historyDate.setText(myListData.getPay_date());
            holder.binding.receiptText.setVisibility(View.VISIBLE);
            holder.binding.receiptText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, PdfViewActivity.class);
                    intent. addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    intent.putExtra("pdfLink",myListData.getInvoice_link());
                    context.startActivity(intent);

                }
            });

        }


    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TransHistoryItemListBinding binding;

        ViewHolder(final TransHistoryItemListBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}