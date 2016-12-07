package com.test.myapp.thenewboston;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by hp on 07-12-2016.
 */

public class SimpleBrowser extends Activity implements View.OnClickListener{
    WebView ourBrow;
    EditText url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplebrowser);
         ourBrow =(WebView)findViewById(R.id.wvBrowser);
        ourBrow.getSettings().setJavaScriptEnabled(true);
        ourBrow.getSettings().setLoadWithOverviewMode(true);
        ourBrow.getSettings().setUseWideViewPort(true);


        ourBrow.setWebViewClient(new ourViewClient());
        try {
            ourBrow.loadUrl("http://WWW.wikipedia.com"); //Website may not load, so its better to surround it with try catch
        }catch (Exception e){
            e.printStackTrace();
        }
        Button Go =(Button)findViewById(R.id.bGo);
        Button Back = (Button)findViewById(R.id.bBack);
        Button Forward = (Button)findViewById(R.id.bForward);
        Button History = (Button)findViewById(R.id.bHistory);
        Button Refresh = (Button)findViewById(R.id.bRefresh);
        url = (EditText)findViewById(R.id.etUrl);
        Go.setOnClickListener(this);
        Back.setOnClickListener(this);
        Forward.setOnClickListener(this);
        Refresh.setOnClickListener(this);
        History.setOnClickListener(this);
            }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bGo:
                String theWebsite = url.getText().toString();
                ourBrow.loadUrl(theWebsite);
                //to hide the keyboard after loading the webpase
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromInputMethod(url.getWindowToken(),0);

                break;
            case R.id.bBack:
                if(ourBrow.canGoBack())
                    ourBrow.goBack();
                break;
            case R.id.bForward:
                if(ourBrow.canGoForward())
                    ourBrow.goForward();
                break;
            case R.id.bRefresh:
                ourBrow.reload();
                break;
            case R.id.bHistory:
                ourBrow.clearHistory();
                break;
        }
    }
}
