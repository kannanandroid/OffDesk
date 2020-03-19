package com.ifazig.optdesk.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ifazig.optdesk.R;

public class MyProfileActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        WebView webView =(WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webSettings.setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl("http://uatifazig.com/optdesk/webpage/workstationbook?Cuid=1&companyid=1&locationid=1&buildingid=1&floorid=1&wingid=6613&FromDate=2020-03-06&TodDate=2020-03-06&FromTime=10:00&ToTime=11:00");
        //webView.loadUrl("https://www.wajabat.ae/");
    }
}
