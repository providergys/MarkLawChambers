package com.demo.retroutility;
import com.demo.model.ChangePassResponse;
import com.demo.model.ForgotPassResponse;
import com.demo.model.GetchatResponse;
import com.demo.model.LoginRequest;
import com.demo.model.LoginResponse;
import com.demo.model.LoginFbResponse;
import com.demo.model.CasesResponse;
import com.demo.model.RecentResponse;
import com.demo.model.SendMessageRequest;
import com.demo.model.SendMessageResponse;
import com.demo.model.SignUpResponse;
import com.demo.model.TeamResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface ApiCalls {
    @POST("signup")
    Call<SignUpResponse> signUpMethod(@Header("Content-Type") String contentType, @Body LoginRequest loginRequest);


    @POST("login")
    Call<LoginResponse> loginMethod(@Header("Content-Type") String contentType, @Body LoginRequest loginRequest);


    @POST("facebook_login")
    Call<LoginFbResponse> loginFbMethod(@Header("Content-Type") String contentType, @Body LoginRequest loginRequest);


    @POST("forgotpassword")
    Call<ForgotPassResponse> forgotPassMethod(@Header("Content-Type") String contentType, @Body LoginRequest loginRequest);


    @GET("recent_activities")
    Call<RecentResponse> recentActivityMethod(@Header("Content-Type") String contentType);


    @GET("case_studies")
    Call<CasesResponse> getCasesMethod(@Header("Content-Type") String contentType);


    @GET("practices")
    Call<PracticeResponse> getPracticeLawMethod(@Header("Content-Type") String contentType);

    @GET("teams")
    Call<TeamResponse> getTeamMethod(@Header("Content-Type") String contentType);

    @POST("change_password")
    Call<ChangePassResponse> changePassMethod(@Header("Content-Type") String contentType, @Body LoginRequest loginRequest);



    @POST("send_message")
    Call<SendMessageResponse> sendMessageMethod(@Header("Content-Type") String contentType, @Body SendMessageRequest sendMessageRequest);


    @POST("get_chat_messages")
    Call<GetchatResponse> getChatMethod(@Header("Content-Type") String contentType, @Body SendMessageRequest sendMessageRequest);
}