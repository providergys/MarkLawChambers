package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.demo.marklaw.databinding.ActivitySignUpBinding;
import com.demo.marklaw.R;
import com.demo.model.LoginRequest;
import com.demo.model.LoginResponse;
import com.demo.model.LoginResponseFb;
import com.demo.retroutility.MainApplication;
import com.demo.utility.Constants;
import com.demo.utility.ProgDialog;
import com.demo.utility.UserSharedPreferences;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    Activity ac;
    AwesomeValidation mAwesomeValidation;
    CallbackManager callbackManager;
    String accessToken;
    ProgDialog prog = new ProgDialog();
    UserSharedPreferences mSharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        init();

    }

    private void init() {
        ac = SignUpActivity.this;
        mAwesomeValidation = new AwesomeValidation(BASIC);
        mSharedPref=new UserSharedPreferences(ac);
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

                                    LoginRequest loginRequest = new LoginRequest();
                                    prog.progDialog(ac);



                                    loginRequest.setSocialid(id);
                                    loginRequest.setEmail(email);
                                    loginRequest.setUsername(first_name);
                                    loginRequest.setLogintype("A");

                                    MainApplication.getApiService().loginMethodFb("application/json", loginRequest).enqueue(new Callback<LoginResponseFb>() {
                                        @Override
                                        public void onResponse(Call<LoginResponseFb> call, Response<LoginResponseFb> response) {
                                            if (response.isSuccessful()) {
                                                prog.hideProg();

                                            } else {
                                                prog.hideProg();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<LoginResponseFb> call, Throwable t) {
                                            prog.hideProg();
                                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });
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
        mAwesomeValidation.addValidation(ac, R.id.userNameEdt, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        mAwesomeValidation.addValidation(ac, R.id.emailEdt, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        mAwesomeValidation.addValidation(ac, R.id.numberEdt, RegexTemplate.NOT_EMPTY, R.string.invalid_number);
        mAwesomeValidation.addValidation(ac, R.id.passEdt, RegexTemplate.NOT_EMPTY, R.string.invalid_password);
    }

    public void signUpButton(View view) {
        if (mAwesomeValidation.validate()) {
            retrofitLogin();
        }
    }


    public void retrofitLogin() {
        LoginRequest loginRequest = new LoginRequest();
        prog.progDialog(ac);
        loginRequest.setUsername(binding.userNameEdt.getText().toString());
        loginRequest.setEmail(binding.emailEdt.getText().toString());
        loginRequest.setPassword(binding.passEdt.getText().toString());
        loginRequest.setMobile_number(binding.numberEdt.getText().toString());

        //Show Your Progress Dialog


        MainApplication.getApiService().signUpmethod("application/json", loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    prog.hideProg();
                    if(response.body().getSuccess().equals("true")){

                         Log.e("successtrue",""+response.body().getSuccess());
                         Toast.makeText(ac,response.body().getMessage(),Toast.LENGTH_LONG).show();
                        mSharedPref.save(Constants.USER_NAME,response.body().getUser_data().getUsername());
                        mSharedPref.save(Constants.USER_ID,String.valueOf(response.body().getUser_data().getId()));
                        mSharedPref.save(Constants.USER_PHONE,response.body().getUser_data().getMobile_number());
                        mSharedPref.save(Constants.USER_EMAIL,response.body().getUser_data().getUseremail());
                        mSharedPref.save(Constants.USER_TYPE,response.body().getUser_data().getUsertype());
                        startActivity(new Intent(SignUpActivity.this,HomeActivity.class));

                    }
                    else if(response.body().getSuccess().equals("false")){
                        Log.e("successfalse",""+response.body().getSuccess());

                        if (response.body().getRespCode().equals("1009")){
                            Toast.makeText(ac,response.body().getMessage(),Toast.LENGTH_LONG).show();
                        }


                        else if (response.body().getRespCode().equals("1002")){
                            Toast.makeText(ac,response.body().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }


                }

                else {
                    prog.hideProg();
                    Toast.makeText(ac,response.body().getMessage(),Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                prog.hideProg();
                Toast.makeText(ac,t.getMessage(),Toast.LENGTH_LONG).show();


                //   snakeBaar.showSnackBar(ac, "Something went wrong..", login_Layout);

            }
        });
    }


    public void login(View view) {
        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
