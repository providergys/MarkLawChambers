package com.demo.adapter;

import android.content.Context;
import android.graphics.Color;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.demo.activities.MessageActivity;
import com.demo.marklaw.R;
import com.demo.model.ChatMessage;
import com.demo.model.GetchatResponse;

import java.io.IOException;
import java.util.List;



/**
 * Created by AndroidDev on 21-Dec-18.
 */

public class ChatWindowAdapter extends RecyclerView.Adapter<ChatWindowAdapter.MyViewHolder> {

    Context context;
    List<GetchatResponse.ChatMessageBean> chats = null;
    MediaPlayer mediaPlayer;
    double start, fina;
    Runnable runnable;
    int timel;

    private int progressStatus = 0;
    private Handler handler = new Handler();

    public ChatWindowAdapter(Context context, List<GetchatResponse.ChatMessageBean> chat_message, MediaPlayer mediaPlayer, double startTime,
                             double finalTime, Runnable runnable, int oneTimeOnl) {
        this.context = context;
        this.chats = chat_message;
        this.mediaPlayer = mediaPlayer;
        this.start = startTime;
        this.fina = finalTime;
        this.runnable = runnable;
        this.timel = oneTimeOnl;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.message_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Log.e("sender", "" + chats.get(position).isIsSender());


        if (chats.get(position).isIsSender()) {


            holder.message_body.setText(chats.get(position).getMessages().trim());
            holder.messageDate.setVisibility(View.GONE);
            holder.message_body.setTextColor(Color.parseColor("#ffffff"));
            holder.message_body.setBackgroundResource(R.drawable.sender_message);





         /*   if (chats.get(position).getMessages().trim().isEmpty()) {
                holder.audiolayout.setVisibility(View.VISIBLE);
                holder.message_body.setVisibility(View.GONE);


                holder.mButtonPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.playingText.setText("Playing");
                        try  {
                            mediaPlayer.reset();
                            //set file path
                            mediaPlayer.setDataSource(context, Uri.parse(chats.get(position).getAudio_file()));
                            mediaPlayer.prepare();
                            mediaPlayer.start();


                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                });

           mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
              @Override
              public void onCompletion(MediaPlayer mediaPlayer) {
                  Toast.makeText(context,"Complete",Toast.LENGTH_LONG).show();
                  holder.playingText.setText("Completed");
              }
          });

                holder.btn_stop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.playingText.setText("Stopped");
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.pause();
                            mediaPlayer.stop();
                            // holder.pause.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
                        } else {
                            mediaPlayer.start();
                            // holder.pause.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
                        }

                    }
                });
               *//* holder.mButtonPlay.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        String url = chats.get(position).getAudio_file(); // your URL here
                        mediaPlayer.reset();
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
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


                        if (!mediaPlayer.isPlaying()) {
                            mediaPlayer.start();
                            holder.mButtonPlay.setImageResource(R.drawable.ic_pause_black_24dp);

                            fina = mediaPlayer.getDuration();
                            start = mediaPlayer.getCurrentPosition();
                            if (timel == 0) {
                                holder.seek_bar.setMax((int) fina);
                                timel = 1;
                            }

                            holder.seek_bar.setProgress((int) start);


                            Runnable UpdateSongTime = new Runnable() {
                                public void run() {
                                    start = mediaPlayer.getCurrentPosition();
                                    holder.seek_bar.setProgress((int) start);
                                    handler.postDelayed(this, 100);
                                }
                            };

                            handler.postDelayed(UpdateSongTime, 100);


                        }


                        else {
                            mediaPlayer.pause();
                            holder.mButtonPlay.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                        }
                    }


                });*//*


            }*/
            if (chats.get(position).getMessages().trim().isEmpty()) {
                holder.audiolayout.setVisibility(View.VISIBLE);
                holder.message_body.setVisibility(View.GONE);
                holder.mButtonPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.pb.setVisibility(View.VISIBLE);


                        try {
                            mediaPlayer.reset();
                            //set file path
                            mediaPlayer.setDataSource(context, Uri.parse(chats.get(position).getAudio_file()));
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mediaPlayer) {
                                    Toast.makeText(context, "Completed", Toast.LENGTH_LONG).show();
                                    holder.pb.setVisibility(View.GONE);

                                }
                            });


                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                });

                holder.btn_stop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.pb.setVisibility(View.GONE);

                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.pause();
                            mediaPlayer.stop();
                            // holder.pause.setImageResource(R.drawable.ic_play_circle_filled_black_24dp);
                        } else {
                            mediaPlayer.start();
                            // holder.pause.setImageResource(R.drawable.ic_pause_circle_filled_black_24dp);
                        }

                    }
                });
            }
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.message_body.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) holder.messageDate.getLayoutParams();
            params1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) holder.audiolayout.getLayoutParams();
            params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);


        }


        else {


            holder.messageDate.setVisibility(View.VISIBLE);
            holder.message_body.setText(chats.get(position).getMessages().trim());
            holder.message_body.setTextColor(Color.parseColor("#000000"));
            holder.message_body.setBackgroundResource(R.drawable.reciver_message);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.message_body.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) holder.messageDate.getLayoutParams();
            params1.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) holder.audiolayout.getLayoutParams();
            params2.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);


            if (chats.get(position).getMessages().trim().isEmpty()) {
                holder.audiolayout.setVisibility(View.VISIBLE);
                holder.message_body.setVisibility(View.GONE);

                holder.mButtonPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.pb.setVisibility(View.VISIBLE);
                        try {
                            mediaPlayer.reset();
                            //set file path
                            mediaPlayer.setDataSource(context, Uri.parse(chats.get(position).getAudio_file()));
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mediaPlayer) {
                                    Toast.makeText(context, "Completed", Toast.LENGTH_LONG).show();
                                    holder.pb.setVisibility(View.GONE);

                                }
                            });


                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                });


                 holder.btn_stop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.pb.setVisibility(View.GONE);
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.pause();
                            mediaPlayer.stop();
                        } else {
                            mediaPlayer.start();
                        }

                    }
                });


              /*  holder.mButtonPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.e("audio",""+chats.get(position).getAudio_file());
                        String url = chats.get(position).getAudio_file(); // your URL here
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
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
                        mediaPlayer.start();
                        holder.mButtonPlay.setImageResource(R.drawable.ic_pause_black_24dp);
                        fina = mediaPlayer.getDuration();
                        start = mediaPlayer.getCurrentPosition();
                        if (timel == 0) {
                            holder.seek_bar.setMax((int) fina);
                            timel = 1;
                        }
                        holder.seek_bar.setProgress((int) start);
                        Runnable UpdateSongTime = new Runnable() {
                            public void run() {
                                start = mediaPlayer.getCurrentPosition();
                                holder.seek_bar.setProgress((int) start);
                                handler.postDelayed(this, 100);
                            }
                        };
                        handler.postDelayed(UpdateSongTime, 100);
                       *//* mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                mediaPlayer.pause();
                                holder.mButtonPlay.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                            }
                        });
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                Toast.makeText(context, "Preparing Audio", Toast.LENGTH_LONG).show();
                                if (mediaPlayer != null) {
                                    mediaPlayer.start();
                                }
                            }
                        });*//*
                    }
                });
                holder.btn_stop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }

                    }

                });*/
            }
        }


    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView message_body, messageDate;
        ImageView mButtonPlay, btn_stop;
        LinearLayout audiolayout;
        SeekBar seek_bar;
        ProgressBar pb;


        public MyViewHolder(View itemView) {
            super(itemView);
            message_body = itemView.findViewById(R.id.message_body);
            messageDate = itemView.findViewById(R.id.messageDate);
            audiolayout = itemView.findViewById(R.id.audiolayout);
            mButtonPlay = itemView.findViewById(R.id.btn_play);
            // seek_bar = itemView.findViewById(R.id.seek_bar);
            btn_stop = itemView.findViewById(R.id.btn_stop);
            pb = itemView.findViewById(R.id.pb);

            // Log.e("audio",""+chats.get(getAdapterPosition()).getAudio_file());

        }
    }


    @Override
    public int getItemCount() {
        return chats.size();
    }
}
