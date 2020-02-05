package com.demo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;


import androidx.databinding.DataBindingUtil;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.ActivityLoginBinding;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.Login;
import com.facebook.login.LoginResult;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class LoginActivity extends Activity {
    Activity ac;
    AwesomeValidation mAwesomeValidation;
    ActivityLoginBinding binding;
    CallbackManager callbackManager;
    String accessToken;
    private static final String EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        init();
    }

    private void init() {
        ac = LoginActivity.this;
        mAwesomeValidation = new AwesomeValidation(BASIC);
        validations();
        fbData();

    }

    private void fbData() {
        callbackManager = CallbackManager.Factory.create();
        binding.loginButton.setPermissions(Arrays.asList("email", "public_profile"));

        boolean loggedOut = AccessToken.getCurrentAccessToken() == null;
        if (!loggedOut) {
            Log.e("hello", "login");

        } else {
            Log.e("hello", "logout");
        }
        binding.loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                accessToken = AccessToken.getCurrentAccessToken().getToken();
                Log.e("accessToken", "" + accessToken);

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.d("TAG", object.toString());
                                try {
                                    String first_name = object.getString("first_name");
                                    String last_name = object.getString("last_name");
                                    String email = object.getString("email");
                                    String id = object.getString("id");
                                    String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";


                                    Log.e("first_name", "" + first_name);
                                    Log.e("last_name", "" + last_name);
                                    Log.e("email", "" + email);
                                    Log.e("id", "" + id);
                                    Log.e("image_url", "" + image_url);
                                    Log.e("accessToken", "" + accessToken);


                                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "first_name,last_name,email,id");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

    }

    private void validations() {
        mAwesomeValidation.addValidation(ac, R.id.emailEdit, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        mAwesomeValidation.addValidation(ac, R.id.passEdit, RegexTemplate.NOT_EMPTY, R.string.invalid_password);
    }

    public void signBtn(View view) {
        if (mAwesomeValidation.validate()) {
            // Here, we are sure that form is successfully validated. So, do your stuffs now...
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            // retrofitLogin();
        }
    }

    public void signUpBtn(View view) {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }

    public void forgotPassword(View view) {
        startActivity(new Intent(LoginActivity.this, ForgotPassActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*public void retrofitLogin() {


        LoginRequest loginRequest=new LoginRequest();

        //Show Your Progress Dialog



        MainApplication.getApiService().loginMethod("application/json", loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {


                    if (response.body().getStatusCode()==200) {
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));

                    } else {

                    }

                } else {




                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {




                //   snakeBaar.showSnackBar(ac, "Something went wrong..", login_Layout);

            }
        });
    }*/
}
