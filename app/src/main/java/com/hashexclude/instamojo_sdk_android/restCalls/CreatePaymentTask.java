package com.hashexclude.instamojo_sdk_android.restCalls;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.JsonObject;
import com.hashexclude.instamojo_sdk_android.Instamojo;
import com.hashexclude.instamojo_sdk_android.interfaces.APIRequestListener;
import com.hashexclude.instamojo_sdk_android.models.PaymentData;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by Sidhant Panda on 28/02/16.
 * Email me at sidhantpanda@gmail.com
 */
public class CreatePaymentTask extends AsyncTask<Void, String, Object> {

    private final Context context;
    private final PaymentData paymentData;

    private APIRequestListener apiRequestListener;

    public CreatePaymentTask(Context context, PaymentData paymentData) {
        this.context = context;
        this.paymentData = paymentData;
    }

    public void setApiRequestListener(APIRequestListener apiRequestListener) {
        this.apiRequestListener = apiRequestListener;
    }

    @Override
    protected Object doInBackground(Void... params) {
        JsonObject json = new JsonObject();
        json.addProperty("purpose", paymentData.getPurpose());
        json.addProperty("amount", paymentData.getAmount());
        json.addProperty("currency", paymentData.getCurrency());
        json.addProperty("buyerName", paymentData.getBuyerName());
        json.addProperty("email", paymentData.getBuyerEmail());
        json.addProperty("phone", paymentData.getBuyerPhone());
        json.addProperty("webhook", paymentData.getWebhook());
        json.addProperty("redirect_url", paymentData.getRedirectUrl());

        Ion.with(context).load(Endpoints.CREATE_PAYMENT)
                .setHeader("X-Api-Key", Instamojo.getApiKey())
                .setHeader("X-Auth-Token", Instamojo.getAuthToken())
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if (e == null) {
                            if (apiRequestListener != null) {
                                apiRequestListener.success(result);
                            }
                        } else {
                            if (apiRequestListener != null) {
                                apiRequestListener.error();
                            }
                        }
                    }
                });
        return null;
    }
}
