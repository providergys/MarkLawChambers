package com.demo.retroutility;




import com.demo.model.LoginRequest;
import com.demo.model.LoginResponse;

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
    Call<LoginResponse> loginMethod(@Header("Content-Type") String contenttype, @Body LoginRequest loginRequest);
}