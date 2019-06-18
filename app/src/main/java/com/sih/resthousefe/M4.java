package com.sih.resthousefe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;



public class M4 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1);
        WebView simpleWebView=(WebView) findViewById(R.id.web1);
        WebSettings webSettings = simpleWebView.getSettings();
        webSettings.setGeolocationEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        simpleWebView.loadUrl("https://www.google.co.in/maps/place/SOUTHERN+RAILWAY%2FICF+HOLIDAY+HOME/@11.4065849,76.6952978,15z/data=!4m5!3m4!1s0x0:0x3965f4577ea3388d!8m2!3d11.4065849!4d76.6952978");
    }
}