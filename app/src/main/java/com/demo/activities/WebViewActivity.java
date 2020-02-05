package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.demo.R;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    String webviewScreenType="";
    ProgressBar porgress_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        //find id of progress bar
        porgress_bar =(ProgressBar) findViewById(R.id.porgress_bar);

        //show progress bar
        porgress_bar.setVisibility(View.VISIBLE);

        webviewScreenType = getIntent().getStringExtra("webviewScreenType");

        webView = findViewById(R.id.webView);


        System.out.println("erjgioejohje"+webviewScreenType);

        if(webviewScreenType.matches("ourTeam")){

            webView.loadUrl("https://mark-lawchambers.com/our-team");
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {

                    //after page is done loading hide the progress bar
                    porgress_bar.setVisibility(View.GONE);
                }
            });
        }else if(webviewScreenType.matches("Sharing")){

            webView.loadUrl("https://mark-lawchambers.com/sharings");
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {

                    //after page is done loading hide the progress bar
                    porgress_bar.setVisibility(View.GONE);
                }
            });

        }else if(webviewScreenType.matches("RecentAct")){
            webView.loadUrl("https://mark-lawchambers.com/recent-activities");
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            webView.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {

                    //after page is done loading hide the progress bar
                    porgress_bar.setVisibility(View.GONE);
                }
            });

        }else if(webviewScreenType.matches("Articles")){

            webView.loadUrl("https://mark-lawchambers.com/articles");
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {

                    //after page is done loading hide the progress bar
                    porgress_bar.setVisibility(View.GONE);
                }
            });

        }

    }
}
