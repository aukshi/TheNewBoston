package com.test.myapp.thenewboston;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by hp on 07-12-2016.
 */
public class ourViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView v, String url){
      v.loadUrl(url);
        return true;
    };
}
