package com.demo.utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;

import androidx.core.content.ContextCompat;

import com.demo.marklaw.R;


/**
 * Created by AndroidDev on 4/19/2017.
 */

public class ProgDialog {

    ProgressDialog progress;

    public void progDialog(Activity ac) {

        progress = new ProgressDialog( ac);
        progress.setMessage("Please wait...");
        //progress.setMessage().C;
        progress.setInverseBackgroundForced( true );
        progress.getWindow().setBackgroundDrawable( new ColorDrawable( ContextCompat.getColor( ac, R.color.colorWhite)));
        progress.setProgressStyle( ProgressDialog.STYLE_SPINNER );
        progress.setIndeterminate( true );
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                progress.dismiss();
//            }
//        }, 3000);

            progress.show();

    }

    public void hideProg() {
        progress.dismiss();
    }
}
