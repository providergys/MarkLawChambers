package com.demo.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.databinding.DataBindingUtil;

import com.demo.marklaw.databinding.SplashScreenBinding;
import com.demo.marklaw.R;
import com.demo.utility.Constants;
import com.demo.utility.UserSharedPreferences;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.content.ContentValues.TAG;


public class SplashScreen extends Activity {


    private static int counter;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    Activity ac;
    protected boolean _active = true;
    protected int _splashTime = 1;/*500*/
    SplashScreenBinding binding;
    UserSharedPreferences mSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.splash_screen);
        ac = SplashScreen.this;

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( SplashScreen.this,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Log.e("newToken",newToken);

            }
        });
        mSharedPref = new UserSharedPreferences(ac);
        genereatekey();

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

    private void genereatekey() {
        try {
            PackageInfo info = ac.getPackageManager().getPackageInfo(ac.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));


                Log.e("keyhash", "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "printHashKey()", e);
        } catch (Exception e) {
            Log.e(TAG, "printHashKey()", e);
        }
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

                    Log.e("userId",""+mSharedPref.getString(Constants.USER_ID));

                     if(mSharedPref.getString(Constants.USER_ID).equalsIgnoreCase("")){
                         startActivity(new Intent(SplashScreen.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                         finish();
                          finish();
                        }else{
                            Intent mainIntent = new Intent(SplashScreen.this, HomeActivity.class);
                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(mainIntent);
                            finish();
                        }
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
