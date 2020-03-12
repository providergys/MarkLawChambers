package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.demo.marklaw.R;

import java.util.ArrayList;
import java.util.Arrays;

public class VideoDemo extends AppCompatActivity {

    VideoView videoView;
    ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("" +
                    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_demo);
        videoView = findViewById(R.id.videoView1);
        final MediaController mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(videoView);
        videoView.setMediaController(mediacontroller);
        videoView.setVideoURI(Uri.parse(arrayList.get(index)));
        videoView.requestFocus();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        videoView.setMediaController(mediacontroller);
                        mediacontroller.setAnchorView(videoView);
                        Log.e("state", "" + mp.getCurrentPosition());
                        Log.e("state", "" + mp.getDuration());





                    }
                });
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "Video over", Toast.LENGTH_SHORT).show();
                if (index++ == arrayList.size()) {
                    index = 0;
                    mp.release();
                    Toast.makeText(getApplicationContext(), "Videos completed", Toast.LENGTH_SHORT).show();
                } else {
                    videoView.setVideoURI(Uri.parse(arrayList.get(index)));
                    videoView.start();
                }
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.d("API123", "What " + what + " extra " + extra);
                return false;
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e("viedostate","yesss");
        Log.e("viedostate",""+videoView.getCurrentPosition());
    }
}