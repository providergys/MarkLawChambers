package com.demo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ProgressBar;

import androidx.databinding.DataBindingUtil;

import com.demo.R;
import com.demo.databinding.SplashScreenBinding;
import com.demo.utility.TinyDB;


public class SplashScreen extends Activity {


    private static int counter;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    Activity ac;
    protected boolean _active = true;
    protected int _splashTime = 1;/*500*/
    SplashScreenBinding binding;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.splash_screen);
        ac = SplashScreen.this;

        // animation of logo


        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        binding.mainLog.startAnimation(animation1);


        // progress increasing
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {

                    int waited = 0;
                    while (_active && (waited < _splashTime)) {
                        sleep(200);

                        if (_active) {
                            waited += 200;
                        }
                    }
                } catch (Exception e) {

                } finally {
                }
            }

            ;
        };
        splashTread.start();

        progMethod();
    }

    public void progMethod() {
        counter = 0;
        binding.progressbar.setMax(200);
        new Thread(new Runnable() {

            public void run() {
                while (progressStatus < 200) {
                    progressStatus = doSomeWork();

                    handler.post(new Runnable() {
                        public void run() {
                            binding.progressbar.setProgress(progressStatus);
                           /* new DotProgressBarBuilder(ac)
                                    .setDotAmount(5)
                                    .setStartColor(Color.BLACK)
                                    .setAnimationDirection(DotProgressBar.LEFT_DIRECTION)
                                    .build();*/

                        }
                    });

                }

                handler.post(new Runnable() {

                    public void run() {
                       startActivity(new Intent(SplashScreen.this,LoginActivity.class));


                   /*     if(tinyDB.getBoolean("rememberme_check")){
                            tinyDB.putBoolean("loginCheck",false);
                            Intent mainIntent = new Intent(SplashScreen.this, ProductActivity.class);
                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(mainIntent);
                            finish();
                        }else{
                            Intent mainIntent = new Intent(SplashScreen.this, LoginActivity.class);
                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(mainIntent);
                        }*/




                        //  progressBar.setVisibility(View.VISIBLE);
                    }

                });
            }

            //---do some long lasting work here---
            private int doSomeWork() {
                try {
                    //---simulate doing some work---
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return ++counter;
            }
        }).start();

    }
}
