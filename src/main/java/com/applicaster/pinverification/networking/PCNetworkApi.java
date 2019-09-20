package com.applicaster.pinverification.networking;

import com.applicaster.pinverification.models.networkresponse.ValidatePincodeResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface PCNetworkApi {

    @FormUrlEncoded
    @POST("v2/accounts/pin-codes/validate")
    Call<ValidatePincodeResponse> pcValidatePinCode(@Field("pin_code") String pinCode,
                                                    @HeaderMap Map<String, String> headers);


    @POST("/v2/accounts/pin-codes/send")
    Call<ValidatePincodeResponse> pcSendCode(
            @HeaderMap Map<String, String> headers);
}
