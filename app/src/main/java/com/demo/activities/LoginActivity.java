package com.demo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.ActivityLoginBinding;
import com.demo.model.LoginRequest;
import com.demo.model.LoginResponse;
import com.demo.model.LoginFbResponse;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class LoginActivity extends Activity {
    Activity ac;
    AwesomeValidation mAwesomeValidation;
    ActivityLoginBinding binding;
    CallbackManager callbackManager;
    String accessToken;
    ProgDialog prog = new ProgDialog();
    UserSharedPreferences mSharedPref;
    String newToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( LoginActivity.this,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                newToken = instanceIdResult.getToken();
                Log.e("newToken",newToken);


            }
        });
        init();


    }

    private void init() {
        ac = LoginActivity.this;
        mAwesomeValidation = new AwesomeValidation(BASIC);
        mSharedPref = new UserSharedPreferences(ac);
        validations();
        fbData();

    }

    private void fbData() {
        callbackManager = CallbackManager.Factory.create();
        binding.loginButton.setPermissions(Arrays.asList("email", "public_profile"));

        boolean loggedOut = AccessToken.getCurrentAccessToken() == null;
        if (!loggedOut) {
            Log.e("hello", "login");
            binding.emailEdit.setFocusable(false);
            binding.passEdit.setFocusable(false);
            binding.signInBtn.setEnabled(false);
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
                                //    String last_name = object.getString("last_name");
                                    String email = object.getString("email");
                                    String id = object.getString("id");
                                 //   String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";


                                    LoginRequest loginRequest = new LoginRequest();
                                    prog.progDialog(ac);

                                    loginRequest.setSocialid(id);
                                    loginRequest.setEmail(email);
                                    loginRequest.setUsername(first_name);
                                    loginRequest.setLogintype("A");
                                    loginRequest.setUsertype("Non Client");
                                    loginRequest.setDevicetype("A");
                                    loginRequest.setDevicetoken(newToken);
                                    MainApplication.getApiService().loginFbMethod("application/json", loginRequest).enqueue(new Callback<LoginFbResponse>() {
                                        @Override
                                        public void onResponse(Call<LoginFbResponse> call, Response<LoginFbResponse> response) {
                                            if (response.isSuccessful()) {
                                                prog.hideProg();
                                                mSharedPref.save(Constants.USER_NAME, response.body().getUser_data().getUsername());
                                                mSharedPref.save(Constants.USER_ID, String.valueOf(response.body().getUser_data().getId()));
                                                mSharedPref.save(Constants.USER_EMAIL, response.body().getUser_data().getUseremail());
                                                mSharedPref.save(Constants.USER_TYPE, response.body().getUser_data().getUsertype());
                                                startActivity(new Intent(LoginActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                                finish();
                                                Toast.makeText(ac, response.body().getMessage(), Toast.LENGTH_LONG).show();


                                            } else {
                                                prog.hideProg();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<LoginFbResponse> call, Throwable t) {
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
        mAwesomeValidation.addValidation(ac, R.id.emailEdit, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        mAwesomeValidation.addValidation(ac, R.id.passEdit, RegexTemplate.NOT_EMPTY, R.string.invalid_password);
    }

    public void signBtn(View view) {
        if (mAwesomeValidation.validate()) {
            // Here, we are sure that form is successfully validated. So, do your stuffs now...
            retrofitLogin();
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

    public void retrofitLogin() {


        LoginRequest loginRequest = new LoginRequest();
        prog.progDialog(ac);
        loginRequest.setUsername(binding.emailEdit.getText().toString());
        loginRequest.setPassword(binding.passEdit.getText().toString());
        loginRequest.setDevicetype("A");
        loginRequest.setDevicetoken(newToken);

        MainApplication.getApiService().loginMethod("application/json", loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.body() !=null && response.isSuccessful()) {
                    prog.hideProg();
                    if ( response.body().getRespCode().matches("1010")) {
                        Toast.makeText(ac, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        mSharedPref.save(Constants.USER_NAME, response.body().getUser_data().getUsername());
                        mSharedPref.save(Constants.USER_ID, String.valueOf(response.body().getUser_data().getId()));
                        mSharedPref.save(Constants.USER_PHONE, response.body().getUser_data().getMobile_number());
                        mSharedPref.save(Constants.USER_EMAIL, response.body().getUser_data().getUseremail());
                        mSharedPref.save(Constants.USER_TYPE, response.body().getUser_data().getUsertype());
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        finish();

                    } else {
                        Toast.makeText(ac, response.body().getMessage(), Toast.LENGTH_LONG).show();

                    }

                } else {
                    prog.hideProg();

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                prog.hideProg();
                Toast.makeText(ac, t.getMessage(), Toast.LENGTH_LONG).show();
                //   snakeBaar.showSnackBar(ac, "Something went wrong..", login_Layout);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
