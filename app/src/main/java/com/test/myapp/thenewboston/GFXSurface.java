package com.test.myapp.thenewboston;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by hp on 19-07-2016.
 */
public class GFXSurface extends Activity implements View.OnTouchListener{ // differnt than onclicklistner. Activates when you touch, move your fingers, and releast the touch
    MyBringBackSurface ourSurfaceView;
    float x,y;
    float  sX, sY; //starting x and y
    float fX, fY; //finishing x and y
    float dX,dY, aniX, aniY, scaledX, scaledY;
    Bitmap test, plus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        ourSurfaceView = new MyBringBackSurface(this);
        ourSurfaceView.setOnTouchListener(this);
        x=0; y=0;
        sX=0; sY=0;
        fX=0; fY=0;
        dX=dY=aniX= aniY= scaledX= scaledY=0;
        test = BitmapFactory.decodeResource(getResources(),R.drawable.energia);
        plus = BitmapFactory.decodeResource(getResources(),R.drawable.plus);
        setContentView(ourSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSurfaceView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ourSurfaceView.resume();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        try {
            Thread.sleep(50); // For slow mobiles. 50 Frames per second.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        x = event.getX();
        y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN: //when we first touch the screen
                sX = event.getX();
                sY = event.getY();
                dX=dY=aniX= aniY= scaledX= scaledY=fX=fY=0; //so that we can shoot the picture againa as it keeps going up if not done so
                break;
            case MotionEvent.ACTION_UP: //when we take our finger up from the screen
                fX = event.getX();
                fY = event.getY();
                dX = fX - sX;
                dY = fY - sY;
                scaledX = dX/30;
                scaledY = dY/30;
                x=y=0; //to get rid of the picture once we shoot it
                break;
        }
        return true;
    }

    public class MyBringBackSurface extends SurfaceView implements Runnable { //we are extending surfaceview class because view class uses too much of cpu time and makes it slow

        SurfaceHolder ourHolder;
        Thread ourThread = null;
        boolean isRunning = false;
        public MyBringBackSurface(Context context) {
            super(context);
            ourHolder = getHolder(); //Holder is used to lock the canvas, so only one thread can write on it at a time. Thus, only our thread will draw on it and others will need permission.

        }
        public void pause(){
            isRunning= false;
            while(true)
            {
                try {
                    ourThread.join(); //blocks the current thread   until receiver finishes its execution and dies
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            ourThread = null;

        }
        public void resume(){
            isRunning = true;
            ourThread = new Thread(this); //Context this so that though the thread is defined here, it will run only in run method.
            ourThread.start();

        }

        @Override
        public void run() {
            while(isRunning){
                if(!ourHolder.getSurface().isValid())
                    continue;

                Canvas canvas = ourHolder.lockCanvas(); //Lock canvas so that other threads, classes, activities cant access it.
                canvas.drawRGB(20,02,100);
                if(x!=0 && y!=0){

                    canvas.drawBitmap(test, x-(test.getWidth()/2), y-(test.getHeight()/2), null);

                }
                if(sX!=0 && sY!=0){

                    canvas.drawBitmap(plus, sX-(plus.getWidth()/2), sY-(plus.getHeight()/2), null);

                }
                if(fX!=0 && fY!=0){
                    canvas.drawBitmap(test, fX-(test.getWidth()/2)-aniX, fY-(test.getHeight()/2)-aniY, null);
                    canvas.drawBitmap(plus, fX-(plus.getWidth()/2), fY-(plus.getHeight()/2), null);

                }
                aniX = aniX + scaledX;
                aniY = aniY + scaledY;
                ourHolder.unlockCanvasAndPost(canvas);

            }

        }
    }
}
