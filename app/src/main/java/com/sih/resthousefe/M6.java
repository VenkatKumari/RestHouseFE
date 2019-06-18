package com.sih.resthousefe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;



public class M6 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1);
        WebView simpleWebView=(WebView) findViewById(R.id.web1);
        WebSettings webSettings = simpleWebView.getSettings();
        webSettings.setGeolocationEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        simpleWebView.loadUrl("https://www.google.co.in/maps/place/South+Western+Railway+Holiday+Home/@15.279,73.91159,17z/data=!3m1!4b1!4m5!3m4!1s0x3bbfb41f4864adaf:0x958d6c93b4e263fc!8m2!3d15.279!4d73.9137787");
    }
}