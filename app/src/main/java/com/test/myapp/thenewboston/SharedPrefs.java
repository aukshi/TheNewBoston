package com.test.myapp.thenewboston;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by hp on 07-12-2016.
 */
//Use shared preferences only to store simple datas like boolean, strings etc. Not for large amount of data.
public class SharedPrefs extends Activity implements View.OnClickListener {

    EditText sharedData;
    TextView dataResults;
    SharedPreferences someData;
    public static  String filename = "MySharedString";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedprefrences);
        setupVariable();
        someData = getSharedPreferences(filename,0);
    }

    private void setupVariable() {
        Button save = (Button)findViewById(R.id.bSave);
        Button load = (Button)findViewById(R.id.bLoad);
        sharedData = (EditText)findViewById(R.id.etSharedData);
        dataResults = (TextView)findViewById(R.id.tvContent);
        save.setOnClickListener(this);
        load.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bSave:
                //to save some data
                String StringData = sharedData.getText().toString();
                SharedPreferences.Editor editor = someData.edit(); //to allow us to edit the sharedpreference someData so that we can change its settings
                editor.putString("SharedString",StringData); //(key, value)
                editor.commit(); //to finalise everything
                break;

            case R.id.bLoad:
                someData = getSharedPreferences(filename,0); //to update the shared preference
                String dataReturned = someData.getString("SharedString","Couldn't Load Data"); //(key, default value i.e. value which will be given if specified key is not found
                dataResults.setText(dataReturned);
//Use shared preferences only to store simple datas like boolean, strings etc. Not for large amount of data.
                break;
        }
    }
}
