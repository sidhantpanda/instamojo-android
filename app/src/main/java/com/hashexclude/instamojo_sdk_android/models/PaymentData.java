package com.hashexclude.instamojo_sdk_android.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sidhant Panda on 28/02/16.
 * Email me at sidhantpanda@gmail.com
 */
public class PaymentData implements Parcelable{
    private String purpose,
            redirectUrl,
            webhook,
            currency = "INR",
            buyerName,
            buyerEmail,
            buyerPhone;

    private boolean sendSMS,
                    sendEmail,
                    allowRepeatedPayments;

    private int amount;

    public PaymentData(){}

    public PaymentData(String purpose, int amount) {
        this.purpose = purpose;
        this.amount = amount;
    }

    protected PaymentData(Parcel in) {
        purpose = in.readString();
        redirectUrl = in.readString();
        webhook = in.readString();
        currency = in.readString();
        buyerName = in.readString();
        buyerEmail = in.readString();
        buyerPhone = in.readString();
        sendSMS = in.readByte() != 0;
        sendEmail = in.readByte() != 0;
        allowRepeatedPayments = in.readByte() != 0;
        amount = in.readInt();
    }

    public static final Creator<PaymentData> CREATOR = new Creator<PaymentData>() {
        @Override
        public PaymentData createFromParcel(Parcel in) {
            return new PaymentData(in);
        }

        @Override
        public PaymentData[] newArray(int size) {
            return new PaymentData[size];
        }
    };

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public void setSendSMS(boolean sendSMS) {
        this.sendSMS = sendSMS;
    }

    public void setSendEmail(boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    public void setAllowRepeatedPayments(boolean allowRepeatedPayments) {
        this.allowRepeatedPayments = allowRepeatedPayments;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public String getWebhook() {
        return webhook;
    }

    public String getCurrency() {
        return currency;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public boolean isSendSMS() {
        return sendSMS;
    }

    public boolean isSendEmail() {
        return sendEmail;
    }

    public boolean isAllowRepeatedPayments() {
        return allowRepeatedPayments;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(purpose);
        dest.writeString(redirectUrl);
        dest.writeString(webhook);
        dest.writeString(currency);
        dest.writeString(buyerName);
        dest.writeString(buyerEmail);
        dest.writeString(buyerPhone);
        dest.writeByte((byte) (sendSMS ? 1 : 0));
        dest.writeByte((byte) (sendEmail ? 1 : 0));
        dest.writeByte((byte) (allowRepeatedPayments ? 1 : 0));
        dest.writeInt(amount);
    }
}