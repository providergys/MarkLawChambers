package com.demo.retroutility;
import com.demo.model.ForgotPassResponse;
import com.demo.model.LoginRequest;
import com.demo.model.LoginResponse;
import com.demo.model.LoginResponseFb;
import com.demo.model.RecentResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;


/**
 * Created on 26 June 2019
 */


public interface ApiCalls {
    @POST("signup")
    Call<LoginResponse> signUpmethod(@Header("Content-Type") String contenttype, @Body LoginRequest loginRequest);


    @POST("login")
    Call<LoginResponse> loginMethod(@Header("Content-Type") String contenttype, @Body LoginRequest loginRequest);


    @POST("facebook_login")
    Call<LoginResponseFb> loginMethodFb(@Header("Content-Type") String contenttype, @Body LoginRequest loginRequest);


    @POST("forgotpassword")
    Call<ForgotPassResponse> forgotPassMethod(@Header("Content-Type") String contenttype, @Body LoginRequest loginRequest);


    @GET("recent_activities")
    Call<RecentResponse> recentActivityMethod(@Header("Content-Type") String contenttype);
}