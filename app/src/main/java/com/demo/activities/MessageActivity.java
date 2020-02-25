package com.demo.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import android.util.Base64;
import android.util.Base64OutputStream;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.adapter.ChatWindowAdapter;
import com.demo.marklaw.R;
import com.demo.model.GetchatResponse;
import com.demo.model.SendMessageRequest;
import com.demo.model.SendMessageResponse;
import com.demo.retroutility.MainApplication;
import com.demo.utility.Constants;
import com.demo.utility.SnakeBaar;
import com.demo.utility.UserSharedPreferences;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class MessageActivity extends AppCompatActivity {

    RecyclerView chat_list;
    ImageView back_btn, send_btn;
    ChatWindowAdapter chatwindowAdapter;

    EditText message_edt;

    SnakeBaar snakeBaar = new SnakeBaar();
    Activity ac;
    String senderId = "", groupId = "", finalReciver = "", isGroup, reciverId = "", chatSenderId = "", groupName = "", readreciverId;
    RelativeLayout mainLayout;
    private int mInterval = 5000; // 5 seconds by default, can be changed later
    private Handler mHandler;
    //    List<Chat> chats = new ArrayList<Chat>();
    Boolean fromAddbtn = false, shipOwner = false;
    // TinyDBLogin tinyDBLogin;
    UserSharedPreferences mSharedPref;

    Button audio_btn;
    String AudioSavePathInDevice = null;
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer =new MediaPlayer();
    public static final int RequestPermissionCode = 1;
    Random random ;
    String RandomAudioFileName = "ABCDEFGHIJKLMNOP";


    public static int oneTimeOnl = 0;


    private double startTime = 0;
    private double finalTime = 0;

    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        init();

    }

     private void init() {
        ac = MessageActivity.this;
        mSharedPref = new UserSharedPreferences(ac);
        chatSenderId = getIntent().getStringExtra("chatSenderId");
        senderId = getIntent().getStringExtra("senderId");
        reciverId = getIntent().getStringExtra("reciverId");
        groupId = getIntent().getStringExtra("groupId");
        finalReciver = getIntent().getStringExtra("finalReciver");
        isGroup = getIntent().getStringExtra("isGroup");
        groupName = getIntent().getStringExtra("groupName");
        fromAddbtn = getIntent().getBooleanExtra("fromAddbtn", false);
        shipOwner = getIntent().getBooleanExtra("shipOwner", false);
        mainLayout = findViewById(R.id.mainLayout);
        message_edt = findViewById(R.id.message_edt);
        send_btn = findViewById(R.id.send_btn);
        chat_list = findViewById(R.id.chat_list);
        chat_list.setLayoutManager(new LinearLayoutManager(ac));
        back_btn = findViewById(R.id.back_btn);
         audio_btn=findViewById(R.id.audio_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (message_edt.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Type Something", Toast.LENGTH_LONG).show();
                } else {
                    retrofitSendMessages();
                }

            }
        });


        sendAudio();







        retrofitGetChatMessages();
      /*  mHandler = new Handler();
        startRepeatingTask();*/

    }

    private void sendAudio() {
        audio_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                  //  startRecording();

                    Toast.makeText(MessageActivity.this, "Hold To Record.!", Toast.LENGTH_SHORT).show();

                    if(checkPermission()) {

                        AudioSavePathInDevice =
                                Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +
                                        "123121" + "AudioRecordingGeet.mp3";

                        MediaRecorderReady();

                        try {




                            mediaRecorder.prepare();
                            mediaRecorder.start();


                        } catch (IllegalStateException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }




                    } else {
                        requestPermission();
                    }

                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

                    //Do Nothing
                   // stopRecording();
                  //  mediaRecorder.stop();
                    Toast.makeText(MessageActivity.this, "Recorded.!", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });
    }




    public void MediaRecorderReady(){
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);

        Log.e("AudioSavePathInDevice",""+AudioSavePathInDevice);




        InputStream inputStream = null;//You can get an inputStream using any IO API
        try {
            inputStream = new FileInputStream(AudioSavePathInDevice);
            byte[] buffer = new byte[8192];
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            Base64OutputStream output64 = new Base64OutputStream(output, Base64.DEFAULT);

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output64.write(buffer, 0, bytesRead);
            }


            output64.close();


            String attachedFile = output.toString();
            Log.e("attachedFile", "" + attachedFile);

            SendMessageRequest sendmessagesRequest = new SendMessageRequest();
            // sendmessagesRequest.setChatmessage(message_edt.getText().toString());
            sendmessagesRequest.setSenderId(mSharedPref.getString(Constants.USER_ID));
            sendmessagesRequest.setReceiverId("1");
            sendmessagesRequest.setAudio(attachedFile);

            MainApplication.getApiService().sendMessageMethod("application/json", sendmessagesRequest).enqueue(new Callback<SendMessageResponse>() {
                @Override
                public void onResponse(Call<SendMessageResponse> call, Response<SendMessageResponse> response) {

                    if (response.isSuccessful()) {
                        if (response.body().getRespCode().matches("7015")) {
                            retrofitGetChatMessages();
                        } else {

                        }

                    } else {
    }
                }

                @Override
                public void onFailure(Call<SendMessageResponse> call, Throwable t) {


                    //   snakeBaar.showSnackBar(ac, "Something went wrong..", login_Layout);

                }
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(MessageActivity.this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

    public String CreateRandomAudioFileName(int string){
        StringBuilder stringBuilder = new StringBuilder( string );
        int i = 0 ;
        while(i < string ) {
            stringBuilder.append(RandomAudioFileName.
                    charAt(random.nextInt(RandomAudioFileName.length())));

            i++ ;
        }
        return stringBuilder.toString();
    }
    public void retrofitGetChatMessages() {
        SendMessageRequest sendMessageRequest = new SendMessageRequest();
        sendMessageRequest.setReceiverId("1");
        sendMessageRequest.setSenderId(mSharedPref.getString(Constants.USER_ID));
        MainApplication.getApiService().getChatMethod("application/json", sendMessageRequest).enqueue(new Callback<GetchatResponse>() {
            @Override
            public void onResponse(Call<GetchatResponse> call, Response<GetchatResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().getRespCode().matches("7016")) {
                        chat_list.setAdapter(null);
                        chatwindowAdapter = new ChatWindowAdapter(getApplicationContext(), response.body().getChat_message(),mediaPlayer,startTime,finalTime,runnable,oneTimeOnl);
                        chat_list.setAdapter(chatwindowAdapter);
                        chat_list.setNestedScrollingEnabled(false);
                        chat_list.scrollToPosition(response.body().getChat_message().size() - 1);
                    } else {
                        snakeBaar.showSnackBar(ac, response.body().getMessage(), mainLayout);
                    }

                } else {
                    snakeBaar.showSnackBar(ac, "Server error", mainLayout);

                }
            }

            @Override
            public void onFailure(Call<GetchatResponse> call, Throwable t) {


            }
        });
    }

    public void retrofitSendMessages() {
        SendMessageRequest sendmessagesRequest = new SendMessageRequest();
        sendmessagesRequest.setChatmessage(message_edt.getText().toString());
        sendmessagesRequest.setSenderId(mSharedPref.getString(Constants.USER_ID));
        sendmessagesRequest.setReceiverId("1");
        // sendmessagesRequest.setAudio(attachedFile);

        MainApplication.getApiService().sendMessageMethod("application/json", sendmessagesRequest).enqueue(new Callback<SendMessageResponse>() {
            @Override
            public void onResponse(Call<SendMessageResponse> call, Response<SendMessageResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().getRespCode().matches("7015")) {
                        message_edt.setText("");
                        retrofitGetChatMessages();
                    } else {
                        snakeBaar.showSnackBar(ac, response.body().getMessage(), mainLayout);
                    }

                } else {


                    snakeBaar.showSnackBar(ac, "Server error", mainLayout);

                }
            }

            @Override
            public void onFailure(Call<SendMessageResponse> call, Throwable t) {


                //   snakeBaar.showSnackBar(ac, "Something went wrong..", login_Layout);

            }
        });
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                retrofitGetChatMessages();//this function can change value of mInterval.

            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    /*void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }
*/


    // send audio to server







    // activity cycle
    @Override
    public void onBackPressed() {

       // stopRepeatingTask();
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        mediaPlayer.stop();
        super.onBackPressed();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //stopRepeatingTask();
    }
    @Override
    protected void onStop() {
       // stopRepeatingTask();
        super.onStop();
    }





}
