package com.demo.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.marklaw.R;
import com.demo.model.PaymentRequest;
import com.demo.model.PaymentResponse;
import com.demo.retroutility.MainApplication;
import com.demo.utility.Constants;
import com.demo.utility.UserSharedPreferences;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InVoiceActivity extends Activity implements PaymentResultWithDataListener {
    private static final String TAG = InVoiceActivity.class.getSimpleName();

    String orderid, amount, currency;
    int iD;
    EditText amountEditTxt;
    UserSharedPreferences mSharedPref;
     TextView orderNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_in_voice);
        mSharedPref = new UserSharedPreferences(this);
        iD = getIntent().getIntExtra(Constants.INVOICE_ID, 0);
        orderid = getIntent().getStringExtra(Constants.INVOICE_NUMBER);
        amount = getIntent().getStringExtra(Constants.INVOICE_AMOUNT);
        currency = getIntent().getStringExtra(Constants.INVOICE_CURRENCY);
        orderNumber=findViewById(R.id.orderNumber);
        orderNumber.setText("Invoice Number"+": "+orderid);
        /*
         To ensure faster loading of the Checkout form,
          call this method as early as possible in your checkout flow.
         */
        Checkout.preload(getApplicationContext());

        // Payment button created by you in XML layout
        Button button = findViewById(R.id.btn_pay);
        amountEditTxt = findViewById(R.id.amountEditTxt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("Amount", "" + amountEditTxt.getText().toString());

                if (amountEditTxt.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Amount ", Toast.LENGTH_LONG).show();
                } else if (Double.parseDouble(amountEditTxt.getText().toString()) > Integer.valueOf(getIntent().getStringExtra(Constants.INVOICE_PENDING))) {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Amount ", Toast.LENGTH_LONG).show();
                } else {
                    startPayment();
                }
            }
        });

        TextView privacyPolicy = findViewById(R.id.txt_privacy_policy);

        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                httpIntent.setData(Uri.parse("https://razorpay.com/sample-application/"));
                startActivity(httpIntent);
            }
        });
    }

    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", getIntent().getStringExtra(Constants.USER_EMAIL));
            options.put("description", "Pay to Mark Law Chambers");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            // options.put("order_id",orderid);
            options.put("currency", getIntent().getStringExtra(Constants.INVOICE_CURRENCY));
            String payment = amountEditTxt.getText().toString();
            double total = Double.parseDouble(payment);
            total = total * 100;


            options.put("amount", total);
            //  options.put("order_id",iD );

            JSONObject preFill = new JSONObject();
            preFill.put("email", getIntent().getStringExtra(Constants.USER_EMAIL));
            preFill.put("contact", "123456479");
            options.put("prefill", preFill);
            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }


    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        Log.e("data", "" + paymentData.getExternalWallet());
        Log.e("data", "" + paymentData.getOrderId());
        Log.e("data", "" + paymentData.getPaymentId());
        Log.e("data", "" + paymentData.getSignature());
        Log.e("data", "" + paymentData.getUserContact());
        Log.e("data", "" + paymentData.getUserEmail());
        Log.e("data", "" + paymentData.getData());


        try {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

            Log.e("pendingBack", "" + getIntent().getStringExtra(Constants.INVOICE_PENDING));
            Log.e("pendingHere", "" + amountEditTxt.getText().toString());

            int final_ = (int) (Integer.parseInt(getIntent().getStringExtra(Constants.INVOICE_PENDING)) - Double.parseDouble(amountEditTxt.getText().toString()));

            PaymentRequest loginRequest = new PaymentRequest();
            loginRequest.setUser_id(mSharedPref.getString(Constants.USER_ID));
            loginRequest.setInvoice_id(String.valueOf(getIntent().getIntExtra(Constants.INVOICE_ID, 0)));
            loginRequest.setPayment_status("Payment Successful");
            loginRequest.setPay_amount(amountEditTxt.getText().toString());
            loginRequest.setPending_amount(String.valueOf(final_));
            MainApplication.getApiService().getPaymentStatusMethod("application/json", loginRequest).enqueue(new Callback<PaymentResponse>() {
                @Override
                public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                    if (response.isSuccessful()) {
                        startActivity(new Intent(InVoiceActivity.this, HomeActivity.class));
                    }
                }

                @Override
                public void onFailure(Call<PaymentResponse> call, Throwable t) {
                }
            });


        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}