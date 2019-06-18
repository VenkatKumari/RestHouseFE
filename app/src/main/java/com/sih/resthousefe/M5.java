package com.sih.resthousefe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class M5 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1);
        WebView simpleWebView=(WebView) findViewById(R.id.web1);
        WebSettings webSettings = simpleWebView.getSettings();
        webSettings.setGeolocationEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        simpleWebView.loadUrl("https://www.google.co.in/maps/place/Railway+Officer+Rest+house+and+Holiday+Home/@19.8636388,75.3135349,17z/data=!4m13!1m5!2m4!1sAurangabad+railway+holiday+home!5m2!5m1!1s2018-04-02!3m6!1s0x3bdb985f2bbb38c9:0xbe36bbefbce55e8e!5m1!1s2018-04-02!8m2!3d19.860573!4d75.3122959");
    }
}