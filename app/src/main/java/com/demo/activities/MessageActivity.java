package com.demo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.adapter.ChatwindowAdapter;
import com.demo.marklaw.R;
import com.demo.model.GetchatRequest;
import com.demo.model.GetchatResponse;
import com.demo.model.SendMessageRequest;
import com.demo.model.SendMessageResponse;
import com.demo.retroutility.MainApplication;
import com.demo.utility.Constants;
import com.demo.utility.ProgDialog;
import com.demo.utility.SnakeBaar;
import com.demo.utility.UserSharedPreferences;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MessageActivity extends AppCompatActivity {

    RecyclerView chat_list;
    ImageView back_btn,send_btn;
    ChatwindowAdapter chatwindowAdapter;
    ArrayList<Boolean> userType = new ArrayList<Boolean>();
    ArrayList<String> userMessage = new ArrayList<String>();
    EditText message_edt;
    ProgDialog prog = new ProgDialog();
    SnakeBaar snakeBaar = new SnakeBaar();
    Activity ac;
    String senderId="",groupId="",finalReciver="",isGroup, reciverId="",chatSenderId="",groupName="",readreciverId;
    RelativeLayout mainLayout;
    private int mInterval = 5000; // 5 seconds by default, can be changed later
    private Handler mHandler;
//    List<Chat> chats = new ArrayList<Chat>();
    Boolean fromAddbtn =false,shipOwner =false;
   // TinyDBLogin tinyDBLogin;
   UserSharedPreferences mSharedPref;
    ArrayList<String> notificationList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        ac = MessageActivity.this ;
        mSharedPref = new UserSharedPreferences(ac);
//        tinyDBLogin = new TinyDBLogin(ac);
        chatSenderId = getIntent().getStringExtra("chatSenderId");
        senderId = getIntent().getStringExtra("senderId");
        reciverId = getIntent().getStringExtra("reciverId");

        System.out.println("pojbbbbbbbbbbbbb"+reciverId);
        groupId = getIntent().getStringExtra("groupId");
        finalReciver = getIntent().getStringExtra("finalReciver");
        isGroup = getIntent().getStringExtra("isGroup");
        groupName = getIntent().getStringExtra("groupName");

        fromAddbtn = getIntent().getBooleanExtra("fromAddbtn",false);
        shipOwner= getIntent().getBooleanExtra("shipOwner",false);

        mainLayout=findViewById(R.id.mainLayout);


        message_edt = findViewById(R.id.message_edt);
        send_btn  = findViewById(R.id.send_btn);

        chat_list= findViewById(R.id.chat_list);
        chat_list.setLayoutManager(new LinearLayoutManager(ac));



        back_btn =findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(message_edt.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Type Something",Toast.LENGTH_LONG).show();
                }

                else {
                    retrofitSendMessages();
                }

            }
        });

       retrofitChatMessages();

        mHandler = new Handler();
        startRepeatingTask();

    }

    public void retrofitChatMessages() {

        SendMessageRequest sendMessageRequest= new SendMessageRequest();
        sendMessageRequest.setReceiverId("1");
        sendMessageRequest.setSenderId(mSharedPref.getString(Constants.USER_ID));
        MainApplication.getApiService().getChatmethod("application/json", sendMessageRequest).enqueue(new Callback<GetchatResponse>() {
            @Override
            public void onResponse(Call<GetchatResponse> call, Response<GetchatResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().getRespCode().matches("7016")) {
                     chat_list.setAdapter(null);
                      chatwindowAdapter = new ChatwindowAdapter(ac,response.body().getChatMessage());
                      chat_list.setAdapter(chatwindowAdapter);
                      chat_list.setNestedScrollingEnabled(false);
                      chat_list.scrollToPosition(response.body().getChatMessage().size() - 1);
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

        MainApplication.getApiService().sendMessagemethod("application/json", sendmessagesRequest).enqueue(new Callback<SendMessageResponse>() {
            @Override
            public void onResponse(Call<SendMessageResponse> call, Response<SendMessageResponse> response) {

                if (response.isSuccessful()) {


                    if (response.body().getRespCode().matches("7015")) {

                        message_edt.setText("");
                        retrofitChatMessages();




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
                retrofitChatMessages();//this function can change value of mInterval.

            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };
    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }
    @Override
    public void onBackPressed() {
        stopRepeatingTask();
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        super.onBackPressed();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
    }

    @Override
    protected void onStop() {
        stopRepeatingTask();
        super.onStop();
    }



}
