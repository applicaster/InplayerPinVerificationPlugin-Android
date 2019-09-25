package com.applicaster.pinverification.networking;

import com.applicaster.pinverification.models.networkresponse.ValidatePincodeResponse;
import com.applicaster.pinverification.models.request.GetPinRequest;
import com.applicaster.pinverification.models.request.PinValidationRequest;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface PCNetworkApi {

    @POST("validatePinCode")
    Call<ValidatePincodeResponse> pcValidatePinCode(@Body PinValidationRequest body);

    @POST("sendPinCode")
    Call<ValidatePincodeResponse> pcSendCode(@Body GetPinRequest body);

}
