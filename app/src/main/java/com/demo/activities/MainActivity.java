package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.demo.R;
import com.demo.model.DataModel;
import com.demo.model.DrawerItemCustomAdapter;


public class MainActivity extends AppCompatActivity {
    public static DrawerItemCustomAdapter drawer_adapter;
    DataModel[] drawerItem;
    ListView mDrawerList;
    ImageView menu_icon;
    DrawerLayout drawer;
    WebView webView;
    Activity ac;
    ProgressBar porgress_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ac= this;
        //find id of progress bar
        porgress_bar =(ProgressBar) findViewById(R.id.porgress_bar);

        //show progress bar
        porgress_bar.setVisibility(View.VISIBLE);
       webView = findViewById(R.id.webView);

        webView.loadUrl("https://mark-lawchambers.com/");
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {

                //after page is done loading hide the progress bar
                porgress_bar.setVisibility(View.GONE);
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.categoriesleft_drawer);

        //initalize the array of data model class and set navigation drawer all values
        drawerItem = new DataModel[6];

        drawerItem[0] = new DataModel("Home");
        drawerItem[1] = new DataModel("Our Team");
        drawerItem[2] = new DataModel("Sharing");
        drawerItem[3] = new DataModel("Recent Activities");
        drawerItem[4] = new DataModel("Articles");
        drawerItem[5] = new DataModel("Cases");

        drawer_adapter = new DrawerItemCustomAdapter(ac, R.layout.list_view_item_row, drawerItem,false);
        mDrawerList.setAdapter(drawer_adapter);



        // onitemclick listener to open and close the navigation drawer
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (drawer.isDrawerOpen(Gravity.LEFT)) {
                    drawer.closeDrawer(Gravity.LEFT);

                }
                if (position == 0) {

                }else if (position == 1) {
                        Intent web = new Intent(MainActivity.this, WebViewActivity.class);
                        web.putExtra("webviewScreenType","ourTeam");
                        startActivity(web);
                }else if (position == 2) {
                    Intent web = new Intent(MainActivity.this, WebViewActivity.class);
                    web.putExtra("webviewScreenType","Sharing");
                    startActivity(web);

                }else if (position == 3) {
                    Intent web = new Intent(MainActivity.this, WebViewActivity.class);
                    web.putExtra("webviewScreenType","RecentAct");
                    startActivity(web);

                }else if (position == 4) {
                    Intent web = new Intent(MainActivity.this, WebViewActivity.class);
                    web.putExtra("webviewScreenType","Articles");
                    startActivity(web);

                }else if (position == 5) {
                    Intent web = new Intent(MainActivity.this, ProductActivity.class);
                    startActivity(web);

                }

            }
        });

        menu_icon = (ImageView) findViewById(R.id.menu_icon);

        //onclick listener of menu button to open/close the navigation drawer
        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);

                }
            }
        });

    }


}
