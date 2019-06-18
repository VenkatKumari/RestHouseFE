package com.sih.resthousefe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class M3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1);
        WebView simpleWebView=(WebView) findViewById(R.id.web1);
        WebSettings webSettings = simpleWebView.getSettings();
        webSettings.setGeolocationEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        simpleWebView.loadUrl("https://www.google.com/maps/place/Railway+Officers+Rest+House,+Rishikesh/@30.1179957,78.3043738,17z/data=!3m1!4b1!4m5!3m4!1s0x39091617c530370d:0x5fd220c73ec1d532!8m2!3d30.1179957!4d78.3065625");
    }
}