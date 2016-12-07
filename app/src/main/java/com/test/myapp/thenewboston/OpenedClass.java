package com.test.myapp.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by hp on 12-07-2016.
 * To exchange data between to activities (part 2)
 */
public class OpenedClass extends Activity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{
    TextView Test, question;
    Button returnData;
    RadioGroup selectionList;
    String setData, gotBread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        Test = (TextView) findViewById(R.id.tvText);
        question = (TextView)findViewById(R.id.tvQuestion);
        returnData = (Button) findViewById(R.id.bReturn);
        selectionList = (RadioGroup)findViewById(R.id.rgAns);
        returnData.setOnClickListener(this);
        selectionList.setOnCheckedChangeListener(this);

        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String et = getData.getString("name","Abhishek is...");
        String values = getData.getString("list","4");
        if(values.contentEquals("1"))
        {
            question.setText(et);

        }

       // Bundle gotBasket = getIntent().getExtras();
        //gotBread = gotBasket.getString("key");
        //question.setText(gotBread);


    }

    @Override
    public void onClick(View v) {
        Intent person = new Intent();
        Bundle backpack = new Bundle();
        backpack.putString("ans",setData);
        person.putExtras(backpack);
        setResult(RESULT_OK,person);
        finish();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rCrazy:
                setData = "Probably right!";

                break;
            case R.id.rSmart:
                setData = "Definitely right!";
                break;
            case R.id.rBoth:
                setData = "Spot on!";
                break;
        }
        Test.setText(setData);
    }
}
