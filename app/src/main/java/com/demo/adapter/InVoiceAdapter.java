package com.demo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.activities.InVoiceActivity;
import com.demo.activities.PdfViewActivity;
import com.demo.activities.ReceiptActivity;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.InvoiceItemListBinding;
import com.demo.marklaw.databinding.TransHistoryItemListBinding;
import com.demo.model.GetInvoiceResponse;
import com.demo.utility.Constants;

import java.util.List;

public class InVoiceAdapter extends RecyclerView.Adapter<InVoiceAdapter.ViewHolder> {
    Context context;
    List<GetInvoiceResponse.InvoicesBean> listdata;

    public InVoiceAdapter(Activity ac, List<GetInvoiceResponse.InvoicesBean> listdata) {
        this.context = ac;
        this.listdata = listdata;
    }

    @Override
    public InVoiceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        InvoiceItemListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.invoice_item_list, parent, false);
        InVoiceAdapter.ViewHolder viewHolder = new InVoiceAdapter.ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final InVoiceAdapter.ViewHolder holder, final int position) {
       final GetInvoiceResponse.InvoicesBean myListData = listdata.get(position);
       holder.binding.invoiceAmount.setText(myListData.getInvoice_detail().getInvoice_amount());
       holder.binding.invoicePAmount.setText(myListData.getInvoice_detail().getPending_amount());
        Log.e("desc",""+myListData.getInvoice_detail().getPending_amount());
       holder.binding.invoiceDesc.setText(myListData.getInvoice_detail().getInvoice_description());
       holder.binding.invoiceDate.setText("Date : "+" "+myListData.getInvoice_detail().getCreated_date());



       holder.binding.pdfViewIcon.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(context, PdfViewActivity.class);
               intent.putExtra("pdfLink",listdata.get(position).getInvoice_detail().getInvoice_detail());
               context.startActivity(intent);
           }
       });

       holder.binding.payBtnInVoice.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(context, InVoiceActivity.class);
               intent.putExtra(Constants.INVOICE_ID,myListData.getInvoice_detail().getId());
               intent.putExtra(Constants.INVOICE_NUMBER, myListData.getInvoice_detail().getInvoice_number());
               intent.putExtra(Constants.INVOICE_AMOUNT, myListData.getInvoice_detail().getInvoice_amount());
               intent.putExtra(Constants.INVOICE_CURRENCY, myListData.getInvoice_detail().getCurrency());
               intent.putExtra(Constants.INVOICE_PENDING, myListData.getInvoice_detail().getPending_amount());
               context.startActivity(intent);
           }
       });

       holder.binding.moreTextNew.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               holder.binding.hideTextNew.setVisibility(View.VISIBLE);
               holder.binding.moreTextNew.setVisibility(View.GONE);
               holder.binding.transactionRecycler.setVisibility(View.VISIBLE);

               holder.binding.transactionRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
               TransacionHistoryAdapter caseUpdateAdapter = new TransacionHistoryAdapter(context, myListData.getInvoice_detail().getPaid_amount());
               holder.binding.transactionRecycler.setAdapter(caseUpdateAdapter);



           }
       });


        holder.binding.hideTextNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.binding.hideTextNew.setVisibility(View.GONE);
                holder.binding.moreTextNew.setVisibility(View.VISIBLE);
                holder.binding.transactionRecycler.setVisibility(View.GONE);


            }
        });

    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final InvoiceItemListBinding binding;

        ViewHolder(final InvoiceItemListBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}
