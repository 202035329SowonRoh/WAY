package com.example.way;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class GlobeActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_globe);

        webView = (WebView) findViewById(R.id.webView);

        // Init webview setting
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);

        //++
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);


        webView.loadUrl("file:///android_asset/globe.html");
//        webView.loadUrl("https://www.naver.com");
    }

}