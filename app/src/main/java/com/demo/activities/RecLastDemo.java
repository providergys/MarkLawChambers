package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.demo.adapter.CaseDetailAdapter;
import com.demo.adapter.ChatWindowAdapter;
import com.demo.marklaw.R;
import com.demo.model.GetchatResponse;
import com.demo.model.SendMessageRequest;
import com.demo.retroutility.MainApplication;
import com.demo.utility.Constants;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecLastDemo extends AppCompatActivity {
    RecyclerView chatList;
    RecAdapter chatwindowAdapter;


    public static int oneTimeOnl = 0;
    MediaPlayer mediaPlayer = new MediaPlayer();

    private double startTime = 0;
    private double finalTime = 0;

    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_last_demo);
        chatList = findViewById(R.id.recRecycler);
        SendMessageRequest sendMessageRequest = new SendMessageRequest();
        sendMessageRequest.setReceiverId("1");
        sendMessageRequest.setSenderId("66");
        MainApplication.getApiService().getChatMethod("application/json", sendMessageRequest).enqueue(new Callback<GetchatResponse>() {
            @Override
            public void onResponse(Call<GetchatResponse> call, Response<GetchatResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().getRespCode().matches("7016")) {

                        chatList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                        chatwindowAdapter = new RecAdapter(getApplicationContext(), response.body().getChat_message(), oneTimeOnl, startTime, finalTime, runnable);
                        chatList.setAdapter(chatwindowAdapter);
                    } else {

                    }

                } else {


                }
            }

            @Override
            public void onFailure(Call<GetchatResponse> call, Throwable t) {


            }
        });
    }


    public class RecAdapter extends RecyclerView.Adapter<RecAdapter.MyViewHolder> {

        Context context;
        List<GetchatResponse.ChatMessageBean> chats;

        private Handler myHandler = new Handler();


        double startTime, finalTime;
        Runnable runnable;
        int oneTimeOnl;

        public RecAdapter(Context context, List<GetchatResponse.ChatMessageBean> chat_message,
                          int oneTimeOnl, double startTime, double finalTime, Runnable runnable) {
            this.context = context;
            this.chats = chat_message;
            this.startTime = startTime;
            this.finalTime = finalTime;
            this.runnable = runnable;
            this.oneTimeOnl = oneTimeOnl;

        }

        @Override
        public RecAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.rec_layout, parent, false);
            return new RecAdapter.MyViewHolder(view);
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public void onBindViewHolder(final RecAdapter.MyViewHolder holder, final int position) {
            final GetchatResponse.ChatMessageBean data = chats.get(position);
            holder.seek_bar.setClickable(false);
            holder.btn_stop.setEnabled(false);


        }


        public class MyViewHolder extends RecyclerView.ViewHolder {
            Button mButtonPlay, btn_stop;
            SeekBar seek_bar;

            public MyViewHolder(View itemView) {
                super(itemView);
                mButtonPlay = itemView.findViewById(R.id.recb1);
                btn_stop = itemView.findViewById(R.id.recb2);
                seek_bar = itemView.findViewById(R.id.reckseek);

                mButtonPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String url = chats.get(getAdapterPosition()).getAudio_file(); // your URL here

                        Log.e("dattttaaa", "------------------------" + url);

                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mediaPlayer.reset();
                        try {
                            mediaPlayer.setDataSource(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        try {
                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                        } else {


                            mediaPlayer.start();

                            finalTime = mediaPlayer.getDuration();
                            startTime = mediaPlayer.getCurrentPosition();
                            Log.e("time", "" + finalTime + "------------" + startTime);
                            if (oneTimeOnl == 0) {
                                seek_bar.setMax((int) finalTime);
                                oneTimeOnl = 1;
                            }

                            seek_bar.setProgress((int) startTime);
                            Runnable UpdateSongTime = new Runnable() {
                                public void run() {
                                    startTime = mediaPlayer.getCurrentPosition();
                                    seek_bar.setProgress((int) startTime);
                                    myHandler.postDelayed(this, 100);
                                }
                            };
                            myHandler.postDelayed(UpdateSongTime, 100);
                            btn_stop.setEnabled(true);
                            mButtonPlay.setEnabled(false);


                        }

                    }


                });


                btn_stop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "Pausingsound", Toast.LENGTH_SHORT).show();
                        if (mediaPlayer != null) {

                            if (mediaPlayer.isPlaying()) {

                                mediaPlayer.stop();

                                String url = chats.get(getAdapterPosition()).getAudio_file(); // your URL here

                                Log.e("dattttaaa", "------------------------" + url);

                                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                mediaPlayer.reset();
                                try {
                                    mediaPlayer.setDataSource(url);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                                try {
                                    mediaPlayer.prepare();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                btn_stop.setEnabled(false);
                                mButtonPlay.setEnabled(true);
                            }

                        }

                    }
                });

                // Log.e("audio",""+chats.get(getAdapterPosition()).getAudio_file());

            }
        }


        @Override
        public int getItemCount() {
            return chats.size();
        }
    }
}
