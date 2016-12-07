package com.test.myapp.thenewboston;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by hp on 25-07-2016.
 */
public class Tabs extends Activity implements View.OnClickListener{
    TabHost th;
    TextView showResults;
    long start,stop; //to work with miliseconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        start =0;
        setContentView(R.layout.tabs);
        th = (TabHost)findViewById(R.id.tabHost);
        Button newtab = (Button)findViewById(R.id.bAddTab);
        Button bStart = (Button)findViewById(R.id.bStartWatch);
        Button bStop = (Button)findViewById(R.id.bStopWatch);
        showResults = (TextView)findViewById(R.id.textView);
        bStart.setOnClickListener(this);
        bStop.setOnClickListener(this);
        newtab.setOnClickListener(this);
        th.setup();
        TabHost.TabSpec specs = th.newTabSpec("tag1");
        specs.setContent(R.id.tab1); //the linear layout we set up in tabhost
        specs.setIndicator("StopWatch");
        th.addTab(specs);
        specs = th.newTabSpec("tag2");
        specs.setContent(R.id.tab2); //the linear layout we set up in tabhost
        specs.setIndicator("Tab 2");
        th.addTab(specs);
        specs = th.newTabSpec("tag3");
        specs.setContent(R.id.tab3); //the linear layout we set up in tabhost
        specs.setIndicator("Add a tab");
        th.addTab(specs);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bAddTab:
                TabHost.TabSpec ourSpec = th.newTabSpec("tag1");
                ourSpec.setContent(new TabHost.TabContentFactory() {
                    @Override
                    public View createTabContent(String tag) {
                        TextView text = new TextView(Tabs.this);
                        text.setText("You've created a new tab");
                        return (text);
                    }
                });
                ourSpec.setIndicator("New");
                th.addTab(ourSpec);
                break;
            case R.id.bStartWatch:
                start = System.currentTimeMillis();
                showResults.setText("");
                break;
            case R.id.bStopWatch:
                stop = System.currentTimeMillis();
                if(start != 0){

                    long result = stop - start;
                    int milis = (int)result;
                    int seconds = (int)result/1000;
                    int minutes = seconds/60;
                    milis = milis %100;
                    seconds = seconds%60;
                    showResults.setText(String.format("%d:%02d:%02d",minutes,seconds,milis));
                    start =0;
                }
                break;
        }
    }
}
