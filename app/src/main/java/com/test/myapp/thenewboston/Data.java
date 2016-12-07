package com.test.myapp.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by hp on 12-07-2016.
 * To exchange data between to activities (part 1)
 */
public class Data extends Activity implements View.OnClickListener {
    Button bStart, bSafr;
    EditText etSend;
    TextView tv;
    String gotBread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get);
        bStart = (Button) findViewById(R.id.bSA);
        bSafr = (Button) findViewById(R.id.bSAFR);
        tv = (TextView) findViewById(R.id.tvGot);
        etSend = (EditText)findViewById(R.id.etSend);
        bSafr.setOnClickListener(this);
        bStart.setOnClickListener(this);




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Bundle basket = data.getExtras();
            String s = basket.getString("ans");
            tv.setText(s);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bSA:
                String bread = etSend.getText().toString();
                Bundle basket = new Bundle();
                basket.putString("key",bread);
                Intent a = new Intent(Data.this, OpenedClass.class);
                a.putExtras(basket);
                startActivity(a);
                break;
            case R.id.bSAFR:
                Intent i = new Intent(Data.this, OpenedClass.class);
                startActivityForResult(i,0);
                break;
        }

    }
}
