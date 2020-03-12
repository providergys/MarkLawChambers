package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.demo.marklaw.R;
import com.demo.utility.UserSharedPreferences;

import java.io.IOException;

public class VDemoNew extends Activity implements SurfaceHolder.Callback {

    SurfaceView videoSurface1;
    MediaPlayer player1;
    String viewSource = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
    ImageView btnPlay, btnPause;
    SurfaceHolder videoHolder1;

    int position = 0;
    int lastPosition=0;
    UserSharedPreferences mSharedpRef;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vdemo_new);
        mSharedpRef=new UserSharedPreferences(this);
        btnPlay = findViewById(R.id.btn1);
        btnPause = findViewById(R.id.btn2);
        videoSurface1 = findViewById(R.id.surfaceView);



        videoHolder1 = videoSurface1.getHolder();
        videoHolder1.addCallback(this);
        videoHolder1.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        player1 = new MediaPlayer();
        btnPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub


                btnPlay.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);
                if(!mSharedpRef.getString("position").equals("")){
                    try {
                        player1.setDataSource(viewSource);
                        player1.setDisplay(videoHolder1);
                        player1.prepare();
                        player1.setAudioStreamType(AudioManager.STREAM_MUSIC);

                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    player1.seekTo(Integer.parseInt(mSharedpRef.getString("position")));
                    player1.start();
                }


                else {
                    try {
                        player1.setDataSource(viewSource);
                        player1.setDisplay(videoHolder1);
                        player1.prepare();
                        player1.setAudioStreamType(AudioManager.STREAM_MUSIC);

                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    player1.seekTo(0);
                    player1.start();
                }




            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                btnPause.setVisibility(View.GONE);
                btnPlay.setVisibility(View.VISIBLE);
                player1.pause();

                Log.e("getDuration", "" + player1.getDuration());
                Log.e("getDuration", "" + player1.getCurrentPosition());

                position = player1.getCurrentPosition();

                mSharedpRef.save("position",""+position);



            }
        });





    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        // TODO Auto-generated method stub

    }


}