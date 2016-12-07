package com.test.myapp.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by hp on 12-03-2016.
 * Sending Email from an app
 */
public class EmailPlay extends Activity implements View.OnClickListener {

    EditText personsEmail, personsName, intro, stupidThings,hatefulAction, outro;
    String emailAdd, begining, name, stupidAction, hatefulAct, out;
    Button sendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);
        intializeVars();
        sendEmail.setOnClickListener(this);
    }

    private void intializeVars() {
        personsEmail = (EditText) findViewById(R.id.etEmail);
        intro = (EditText) findViewById(R.id.etIntro);
        personsName = (EditText) findViewById(R.id.etName);
        stupidThings = (EditText) findViewById(R.id.etThings);
        hatefulAction = (EditText) findViewById(R.id.etAction);
        outro = (EditText) findViewById(R.id.etoutro);
        sendEmail = (Button) findViewById(R.id.bSendEmail);

    }



    @Override
    public void onClick(View v) {
        convertVarsToText();
        String emailaddress[] = {emailAdd};
        String message = "Well hello" + name + "I want to say" + begining
                + "I hate you" + stupidAction + "i just want to make you crazy"
                + hatefulAct + "thats what i wanted to say"+outro+'\n'+" go fuck yourself ";
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL,emailaddress);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"i hate you!");
        emailIntent.setType("plain/Text");
        emailIntent.putExtra(Intent.EXTRA_TEXT,message);
        startActivity(emailIntent);


    }
    private void convertVarsToText() {
        emailAdd = personsEmail.getText().toString();
        begining = intro.getText().toString();
        name = personsName.getText().toString();
        stupidAction = stupidThings.getText().toString();
        hatefulAct = hatefulAction.getText().toString();
        out = outro.getText().toString();

    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
