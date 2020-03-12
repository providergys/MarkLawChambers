package com.demo.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.demo.marklaw.R;
import com.demo.model.SharingResponse;
import com.demo.utility.CustomTextureVideoView;
import com.demo.utility.UIUtils;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;


import java.io.File;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.List;


public class SharingPosdCastAdapter extends RecyclerView.Adapter<SharingPosdCastAdapter.VideoViewHolder> {

    List<SharingResponse.VideospostsBean> mVideos;
    WeakReference<Activity> activityWeakReference;

    CallbackManager callbackManager;
    ShareDialog shareDialog;


    public SharingPosdCastAdapter(Activity applicationContext, List<SharingResponse.VideospostsBean> videos,
                                  CallbackManager callbackManager, ShareDialog shareDialog) {
        this.activityWeakReference = new WeakReference<>(applicationContext);
        this.mVideos = videos;


        this.callbackManager = callbackManager;
        this.shareDialog = shareDialog;


    }

    private Activity getActivity() {
        return activityWeakReference.get();
    }


    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {

        SharingResponse.VideospostsBean data = mVideos.get(position);

        holder.videoUrl = data.getImage();
        holder.imageLoaderProgressBar.setVisibility(View.INVISIBLE);
        holder.videoImageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onViewRecycled(VideoViewHolder holder) {
        if (holder == currentVideoViewHolder) {
            currentVideoViewHolder = null;
            holder.stopVideo();
        }
        holder.videoView.stopPlayback();
        super.onViewRecycled(holder);

    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }

    public void onScrolled(RecyclerView recyclerView) {
        if (currentVideoViewHolder != null) {
            currentVideoViewHolder.onScrolled(recyclerView);
        }
    }

    VideoViewHolder currentVideoViewHolder;

    class VideoViewHolder extends RecyclerView.ViewHolder {
        ImageView videoPlayImageButton;
        ImageView video_pause_img_btn;
        ProgressBar imageLoaderProgressBar;
        CustomTextureVideoView videoView;
        ImageView videoImageView;
        String videoUrl;
        TextView shareWhatsAppText,shareWhatsText;

        public String getVideoUrl() {
            return videoUrl;
        }

        public VideoViewHolder(View view) {
            super(view);
            videoPlayImageButton = view.findViewById(R.id.video_play_img_btn);
            video_pause_img_btn = view.findViewById(R.id.video_pause_img_btn);
            imageLoaderProgressBar = view.findViewById(R.id.lyt_image_loader_progress_bar);
            videoView = view.findViewById(R.id.video_feed_item_video);
            videoImageView = view.findViewById(R.id.video_feed_item_video_image);
           /* shareWhatsAppText = view.findViewById(R.id.sharefbText);
            shareWhatsText = view.findViewById(R.id.shareWhatsText);*/

//            shareWhatsText.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Uri uri = Uri.parse(mVideos.get(getAdapterPosition()).getImage());
//                    Intent videoshare = new Intent(Intent.ACTION_SEND);
//                    videoshare.setType("*/*");
//                    videoshare.setPackage("com.whatsapp");
//                    videoshare.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    videoshare.putExtra(Intent.EXTRA_STREAM,uri);
//
//                    getActivity().startActivity(videoshare);
//                }
//            });



           /* shareWhatsAppText.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                        @Override
                        public void onSuccess(Sharer.Result result) {
                            Log.e("success", "" + result);
                        }

                        @Override
                        public void onCancel() {
                            Log.e("cancel", "vvvvvvvvvvvvvvvvvvv" + "cancel");
                        }

                        @Override
                        public void onError(FacebookException error) {
                            if (error != null && error.getMessage().equals("null")) {
                                Log.e("error", "yyyyyyyyyyyyyyyyyyyyyyyyyyy" + error);
                                // Don't use the app for sharing in case of null-error
                                //   shareDialog.show(linkContent, ShareDialog.Mode.WEB);
                            }
                        }
                    });

*//*
                    if (ShareDialog.canShow(ShareLinkContent.class)) {
                        ShareVideo ShareVideo = new ShareVideo.Builder()
                                .setLocalUrl(Uri.parse("http://182.74.186.138/marklaw/wp-content/uploads/2020/03/file_example_MP4_480_1_5MG.mp4"))
                                .build();
                        ShareVideoContent content = new ShareVideoContent.Builder()
                                .setVideo(ShareVideo)
                                .build();
                        shareDialog.show(content,ShareDialog.Mode.AUTOMATIC);


                    }*//*


              if (ShareDialog.canShow(ShareLinkContent.class)) {


                        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                                .setContentUrl(Uri.parse("http://182.74.186.138/marklaw/wp-content/uploads/2020/03/file_example_MP4_480_1_5MG.mp4"))
                                .setQuote("Click On the Link to view Video")
                                .build();
                        shareDialog.show(linkContent);
                    }


                }
            });*/




            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(final MediaPlayer mp) {
                    Log.v("Video", "onPrepared" + mVideos);
                    int width = mp.getVideoWidth();
                    int height = mp.getVideoHeight();
                    videoView.setIsPrepared(true);
                    UIUtils.resizeView(videoView, UIUtils.getScreenWidth(getActivity()), UIUtils.getScreenWidth(getActivity()) * height / width);
                    if (currentVideoViewHolder == VideoViewHolder.this) {
                        videoImageView.setVisibility(View.GONE);
                        imageLoaderProgressBar.setVisibility(View.INVISIBLE);
                        videoView.setVisibility(View.VISIBLE);
                        videoView.seekTo(0);
                        videoView.start();
                    }
                }
            });


            video_pause_img_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stopVideo();
                    videoPlayImageButton.setVisibility(View.VISIBLE);
                    video_pause_img_btn.setVisibility(View.GONE);
                    Log.e("stateposition", "" + videoView.getCurrentPosition());
                }
            });


        /*    videoView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    Log.v("Video", "onFocusChange" + hasFocus);
                    if (!hasFocus && currentVideoViewHolder == VideoViewHolder.this) {
                        stopVideo();

                        Log.e("stateposition",""+videoView.getCurrentPosition());
                    }

                }
            });*/


            videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(MediaPlayer mp, int what, int extra) {
                    Log.v("Video", "onInfo" + what + " " + extra);

                    return false;
                }
            });
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Log.v("Video", "onCompletion");

                    videoImageView.setVisibility(View.VISIBLE);
                    videoPlayImageButton.setVisibility(View.VISIBLE);

                    if (videoView.getVisibility() == View.VISIBLE)
                        videoView.setVisibility(View.INVISIBLE);


                    imageLoaderProgressBar.setVisibility(View.INVISIBLE);
                    currentVideoViewHolder = null;
                }
            });
            videoPlayImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    videoPlayImageButton.setVisibility(View.GONE);
                    video_pause_img_btn.setVisibility(View.VISIBLE);


                    if (currentVideoViewHolder != null && currentVideoViewHolder != VideoViewHolder.this) {
                        currentVideoViewHolder.videoView.pause();
                        currentVideoViewHolder.videoImageView.setVisibility(View.INVISIBLE);
                        currentVideoViewHolder.videoPlayImageButton.setVisibility(View.VISIBLE);
                        currentVideoViewHolder.imageLoaderProgressBar.setVisibility(View.INVISIBLE);
                        if (currentVideoViewHolder.videoView.getVisibility() == View.VISIBLE)
                            currentVideoViewHolder.videoView.setVisibility(View.INVISIBLE);


                        currentVideoViewHolder = null;
                    }

                    currentVideoViewHolder = VideoViewHolder.this;

                    videoPlayImageButton.setVisibility(View.INVISIBLE);
                    imageLoaderProgressBar.setVisibility(View.VISIBLE);
                    videoView.setVisibility(View.VISIBLE);
                    videoImageView.setVisibility(View.INVISIBLE);
                    if (!getVideoUrl().equals(mVideos)) {
                        videoView.setIsPrepared(false);
                        videoView.setVideoPath(getVideoUrl());
                        videoView.requestFocus();
                    } else {
                        if (videoView.isPrepared()) {
                            imageLoaderProgressBar.setVisibility(View.INVISIBLE);
                        } else {
                            imageLoaderProgressBar.setVisibility(View.VISIBLE);
                        }
                        videoView.requestFocus();
                        videoView.seekTo(0);
                        videoView.start();
                    }
                }
            });


        }

        public void stopVideo() {
            Log.v("Video", "stopVideo");

            //imageView is within the visible window
            videoView.pause();
            if (videoView.getVisibility() == View.VISIBLE) {
                videoView.setVisibility(View.INVISIBLE);
            }
            videoImageView.setVisibility(View.VISIBLE);
            videoPlayImageButton.setVisibility(View.VISIBLE);
            imageLoaderProgressBar.setVisibility(View.INVISIBLE);
            currentVideoViewHolder = null;
        }

        public void onScrolled(RecyclerView recyclerView) {
            if (isViewNotVisible(videoPlayImageButton, recyclerView) || isViewNotVisible(imageLoaderProgressBar, recyclerView)) {
                //imageView is within the visible window
                stopVideo();
            }
        }

        public boolean isViewNotVisible(View view, RecyclerView recyclerView) {
            Rect scrollBounds = new Rect();
            recyclerView.getHitRect(scrollBounds);
            return view.getVisibility() == View.VISIBLE && !view.getLocalVisibleRect(scrollBounds);
        }
    }
}
