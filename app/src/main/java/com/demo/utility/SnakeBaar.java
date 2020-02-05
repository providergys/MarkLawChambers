package com.demo.utility;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.demo.marklaw.R;
import com.google.android.material.snackbar.Snackbar;


/**
 * Created by AndroidDev on 4/19/2017.
 */

public class SnakeBaar {

    public  void showSnackBar(Activity ac, String message, View lay) {
        Snackbar snackbar = Snackbar
                .make(lay, message, Snackbar.LENGTH_LONG)
                .setAction("OK", onSnackBarClickListener());

        snackbar.setActionTextColor(ac.getResources().getColor(R.color.colorPrimary));
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.parseColor("#ed3125"));
//        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//        //  textView.setTextColor(getResources().getColor(R.color.buttonbackground));
//        textView.setTextColor(ContextCompat.getColor(ac, R.color.colorPrimary));
        snackbar.show();

    }

    private View.OnClickListener onSnackBarClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(LoginActivity.this, "You clicked SnackBar Button", Toast.LENGTH_SHORT).show();

            }
        };
    }
}
