package com.demo.retroutility;
import com.demo.model.ChangePassResponse;
import com.demo.model.ContactResponse;
import com.demo.model.ContactUSRequest;
import com.demo.model.ForgotPassResponse;
import com.demo.model.GetInvoiceResponse;
import com.demo.model.GetchatResponse;
import com.demo.model.LoginRequest;
import com.demo.model.LoginResponse;
import com.demo.model.LoginFbResponse;
import com.demo.model.CasesResponse;
import com.demo.model.PaymentRequest;
import com.demo.model.PaymentResponse;
import com.demo.model.RecentResponse;
import com.demo.model.SendMessageRequest;
import com.demo.model.SendMessageResponse;
import com.demo.model.SharingResponse;
import com.demo.model.SignUpResponse;
import com.demo.model.TeamResponse;
import com.demo.model.VideoPosdcastResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface ApiCalls {
    // register api
    @POST("signup")
    Call<SignUpResponse> signUpMethod(@Header("Content-Type") String contentType, @Body LoginRequest loginRequest);


    // login api
    @POST("login")
    Call<LoginResponse> loginMethod(@Header("Content-Type") String contentType, @Body LoginRequest loginRequest);


    // facebook login api
    @POST("facebook_login")
    Call<LoginFbResponse> loginFbMethod(@Header("Content-Type") String contentType, @Body LoginRequest loginRequest);


    // forgot password api
    @POST("forgotpassword")
    Call<ForgotPassResponse> forgotPassMethod(@Header("Content-Type") String contentType, @Body LoginRequest loginRequest);


    // recent activities api
    @GET("recent_activities")
    Call<RecentResponse> recentActivityMethod(@Header("Content-Type") String contentType);


    // case api
    @POST("case_studies")
    Call<CasesResponse> getCasesMethod(@Header("Content-Type") String contentType,@Body LoginRequest loginRequest);


    // practices api
    @GET("practices")
    Call<PracticeResponse> getPracticeLawMethod(@Header("Content-Type") String contentType);


    // team members api
    @GET("teams")
    Call<TeamResponse> getTeamMethod(@Header("Content-Type") String contentType);


    // change password api
    @POST("change_password")
    Call<ChangePassResponse> changePassMethod(@Header("Content-Type") String contentType, @Body LoginRequest loginRequest);


    // send messages to admin api
    @POST("send_message")
    Call<SendMessageResponse> sendMessageMethod(@Header("Content-Type") String contentType, @Body SendMessageRequest sendMessageRequest);


    // chats api
    @POST("get_chat_messages")
    Call<GetchatResponse> getChatMethod(@Header("Content-Type") String contentType, @Body SendMessageRequest sendMessageRequest);


    // invoice  api
    @POST("get_invoice_by_userid")
    Call<GetInvoiceResponse> getInvoiceMethod(@Header("Content-Type") String contentType, @Body LoginRequest sendMessageRequest);


    // payment api
    @POST("update_payment_status")
    Call<PaymentResponse> getPaymentStatusMethod(@Header("Content-Type") String contentType, @Body PaymentRequest sendMessageRequest);


   // contact us api
   @POST("contactus")
   Call<ContactResponse> contactUsMethod(@Header("Content-Type") String contentType, @Body ContactUSRequest sendMessageRequest);


    @POST("get_sharing")
    Call<SharingResponse> getSharing(@Header("Content-Type") String contentType);

    @POST("get_video_podcast")
    Call<VideoPosdcastResponse> getBothVP(@Header("Content-Type") String contentType);

}


