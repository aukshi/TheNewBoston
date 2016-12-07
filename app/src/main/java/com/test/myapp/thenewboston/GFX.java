package com.test.myapp.thenewboston;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;

/**
 * Created by hp on 18-07-2016.
 * Adding animation and graphics
 */
public class GFX extends Activity {
    MyBringBack ourView;
    PowerManager.WakeLock wL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PowerManager pM =(PowerManager)getSystemService(Context.POWER_SERVICE);
         wL = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK,"Whatever");

        //Wake-lock is set up before super methods

        super.onCreate(savedInstanceState);
        wL.acquire();
        ourView = new MyBringBack(this);
        setContentView(ourView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        wL.acquire();
    }

    @Override
    protected void onPause() {
        super.onPause();
        wL.release();
    }
}
