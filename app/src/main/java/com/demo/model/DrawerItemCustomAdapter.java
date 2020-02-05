package com.demo.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.R;


/**
 * Created by anupamchugh on 10/12/15.
 */
public class DrawerItemCustomAdapter extends ArrayAdapter<DataModel> {

    Context mContext;
    int layoutResourceId;
    DataModel data[] = null;
    boolean newMessage;

    public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, DataModel[] data, boolean newMessage) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
        this.newMessage = newMessage;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View listItem = convertView;
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);


        final TextView textViewName = (TextView) listItem.findViewById(R.id.textViewName);

        DataModel folder = data[position];
        textViewName.setText(folder.name);



        return listItem;
    }
}

