package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.marklaw.R;

import java.util.concurrent.TimeUnit;

public class AudioDemo extends Activity {
    private Button b3,b2;
    private ImageView iv;
    private MediaPlayer mediaPlayer;

    private double startTime = 0;
    private double finalTime = 0;

    private Handler myHandler = new Handler();
    ;
    private int forwardTime = 5000;
    private int backwardTime = 5000;
    private SeekBar seekbar;


    public static int oneTimeOnly = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_demo);
        b2 =  findViewById(R.id.butto2);
        b3 = findViewById(R.id.butto3);
        mediaPlayer = MediaPlayer.create(this, R.raw.aud);
        seekbar = findViewById(R.id.seek);
        seekbar.setClickable(false);
        b2.setEnabled(false);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Playingsound", Toast.LENGTH_SHORT).show();
                mediaPlayer.start();

                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();
                Log.e("time", "" + finalTime + "------------" + startTime);
                if (oneTimeOnly == 0) {
                    seekbar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }

                seekbar.setProgress((int) startTime);
                Runnable UpdateSongTime = new Runnable() {
                    public void run() {
                        startTime = mediaPlayer.getCurrentPosition();

                        seekbar.setProgress((int) startTime);
                        myHandler.postDelayed(this, 100);
                    }
                };
                myHandler.postDelayed(UpdateSongTime, 100);
                b2.setEnabled(true);
                b3.setEnabled(false);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Pausingsound",Toast.LENGTH_SHORT).show();
                        mediaPlayer.pause();
                b2.setEnabled(false);
                b3.setEnabled(true);
            }
        });


    }


}