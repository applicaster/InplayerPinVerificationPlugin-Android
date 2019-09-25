package com.applicaster.pinverification.models.request;

import com.google.gson.annotations.SerializedName;

public class PinValidationRequest {

    @SerializedName("token")
    public String token;

    @SerializedName("publisherId")
    public String publisherId;

    @SerializedName("pinCode")
    public String pinCode;


    public PinValidationRequest(String token, String publisherId, String pinCode) {
        this.token = token;
        this.publisherId = publisherId;
        this.pinCode = pinCode;
    }
}
