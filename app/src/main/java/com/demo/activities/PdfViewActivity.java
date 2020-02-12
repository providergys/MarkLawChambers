package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.demo.marklaw.R;

public class PdfViewActivity extends AppCompatActivity {

    ImageView back_btn;
    String pdfLink;
    ProgressBar porgress_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);

        //find id of progress bar
        porgress_bar =(ProgressBar) findViewById(R.id.porgress_bar);

        //show progress bar
        porgress_bar.setVisibility(View.VISIBLE);



        //get link of pdf file from intent
        pdfLink = getIntent().getStringExtra("pdfLink");
        Log.e("pdf",""+pdfLink);

        //find id of webview
        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptEnabled(true);


        //show pdf file in webview
        webView.loadUrl("https://docs.google.com/gview?embedded=true&url="+pdfLink);
        webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {

                //after page is done loading hide the progress bar
                porgress_bar.setVisibility(View.GONE);
            }
        });

        //find ids of back button
        back_btn= findViewById(R.id.back_btn);

        //on back pressed
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}