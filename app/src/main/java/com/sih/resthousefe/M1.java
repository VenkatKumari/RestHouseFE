package com.sih.resthousefe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class M1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1);
        WebView simpleWebView=(WebView) findViewById(R.id.web1);
        WebSettings webSettings = simpleWebView.getSettings();
        webSettings.setGeolocationEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        simpleWebView.loadUrl("https://www.google.co.in/maps/place/Railway+Officers+Rest+House/@13.0284858,80.213494,12z/data=!4m5!3m4!1s0x0:0xb2e0c249a560f20d!8m2!3d13.0830732!4d80.2741385");
    }
}
