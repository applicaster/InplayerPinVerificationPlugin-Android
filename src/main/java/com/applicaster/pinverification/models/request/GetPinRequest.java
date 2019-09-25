package com.applicaster.pinverification.models.request;

import com.google.gson.annotations.SerializedName;

public class GetPinRequest {

    @SerializedName("publisherId")
    public String publisherId;

    @SerializedName("token")
    public String token;

    public GetPinRequest(String publisherId, String token) {
        this.publisherId = publisherId;
        this.token = token;
    }
}
