package com.hashexclude.instamojo_sdk_android.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.google.gson.JsonObject;
import com.hashexclude.instamojo_sdk_android.R;
import com.hashexclude.instamojo_sdk_android.interfaces.APIRequestListener;
import com.hashexclude.instamojo_sdk_android.models.PaymentData;
import com.hashexclude.instamojo_sdk_android.restCalls.CreatePaymentTask;
import com.hashexclude.instamojo_sdk_android.restCalls.GetPaymentDetailsTask;

/**
 * Created by Sidhant Panda on 28/02/16.
 * Email me at sidhantpanda@gmail.com
 */
public class PaymentActivity extends AppCompatActivity {
    private static final String TAG = "PaymentActivity";

    private PaymentData paymentData;

    private ProgressBar progressBar;

    private WebView paymentView;

    private String paymentRequestId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        progressBar = (ProgressBar) findViewById(R.id.progressbar_loading_webview);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            paymentData = extras.getParcelable("paymentData");
            // TODO cancel activity if no paymentData found
        }

        paymentView = (WebView)findViewById(R.id.webview);
        paymentView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);

                if (url.contains("?order_id=")) {
                    progressBar.setVisibility(View.VISIBLE);
                    GetPaymentDetailsTask getPaymentDetailsTask = new GetPaymentDetailsTask(PaymentActivity.this, paymentRequestId);
                    getPaymentDetailsTask.setApiRequestListener(new APIRequestListener() {
                        @Override
                        public void success(JsonObject result) {
                            progressBar.setVisibility(View.GONE);
                            String status = result.get("payment_request").getAsJsonObject().get("status").getAsString();

                            Intent returnIntent = new Intent();
                            returnIntent.putExtra("status", status);
                            returnIntent.putExtra("payment_details", String.valueOf(result.get("payment_request").getAsJsonObject()));
                            setResult(RESULT_OK, returnIntent);
                            finish();
                        }

                        @Override
                        public void error() {

                        }
                    });
                    getPaymentDetailsTask.execute();
                }
            }
        });
        paymentView.getSettings().setSupportMultipleWindows(true);
        paymentView.getSettings().setJavaScriptEnabled(true);

        CreatePaymentTask createPaymentTask = new CreatePaymentTask(PaymentActivity.this, paymentData);
        createPaymentTask.setApiRequestListener(new APIRequestListener() {
            @Override
            public void success(JsonObject result) {
                paymentRequestId = result.get("payment_request").getAsJsonObject().get("id").getAsString();
                paymentView.loadUrl(result.get("payment_request").getAsJsonObject().get("longurl").getAsString());
            }

            @Override
            public void error() {

            }
        });
        createPaymentTask.execute();
    }
}
