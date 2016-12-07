package com.test.myapp.thenewboston;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

/**
 * Created by hp on 07-12-2016.
 */

public class flipper extends Activity implements View.OnClickListener {

    ViewFlipper flippy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flipper);
         flippy =(ViewFlipper)findViewById(R.id.viewFlipper);
        flippy.setOnClickListener(this);
        flippy.setFlipInterval(900);
        flippy.startFlipping();

    }

    @Override
    public void onClick(View v) {
        flippy.showNext();
    }
}
