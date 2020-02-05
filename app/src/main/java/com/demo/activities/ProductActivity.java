package com.demo.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.marklaw.R;
import com.demo.model.ProductAdapter;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {


    RecyclerView list;
    ProductAdapter productAdapter;
    ArrayList<String> sup_name_list =new ArrayList<String>();
    ArrayList<String> list_type =new ArrayList<String>();
    ArrayList<String> dateList =new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_activity);

        sup_name_list.clear();
        list_type.clear();
        dateList.clear();

        sup_name_list.add("Main List");
        sup_name_list.add("Main List");
        sup_name_list.add("Main List");
        sup_name_list.add("Supplementary List");
        sup_name_list.add("Supplementary List");

        list_type.add("Liquidation (Ordinary)");
        list_type.add("Urgent");
        list_type.add("Takenup");
        list_type.add("Regular");
        list_type.add("Liquidation (Urgent)");

        dateList.add("22/01/2020");
        dateList.add("22/01/2020");
        dateList.add("21/01/2020");
        dateList.add("18/01/2020");
        dateList.add("18/01/2020");


        list=(RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(ProductActivity.this));

        productAdapter = new ProductAdapter(ProductActivity.this, sup_name_list, list_type,dateList);
        list.setAdapter(productAdapter);
    }
}
