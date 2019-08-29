package com.applicaster.pinverification.networking;

import com.applicaster.pinverification.interfaces.PCNetworkResponse;
import com.applicaster.pinverification.models.networkresponse.ValidatePincodeResponse;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PCNetworkRepo {

    private static PCNetworkRepo instance;


    public static PCNetworkRepo getInstance() {

        if (instance == null) {
            instance = new PCNetworkRepo();
        }

        return instance;


    }


    public void setPinCode(String pinCode, String authorizationToken, final PCNetworkResponse listener) {


        Map<String, String> tMap = new HashMap<>();
        tMap.put("pin_code", pinCode);


        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Authorization", "Bearer " + authorizationToken);

        Call<ValidatePincodeResponse> call = PCRestClient.pcClient.pcValidatePinCode(pinCode, headersMap);

        call.enqueue(new Callback<ValidatePincodeResponse>() {
            @Override
            public void onResponse(Call<ValidatePincodeResponse> call, Response<ValidatePincodeResponse> response) {

                switch (response.code()) {
                    case HttpURLConnection.HTTP_OK:
                        listener.onSuccess(response);
                        break;

                    case HttpURLConnection.HTTP_UNAUTHORIZED:
                        listener.onError(response.message());
                        break;

                    case HttpURLConnection.HTTP_FORBIDDEN:
                        //TODO... The pin does not match
                        listener.onError(response.message());
                        break;
                }


            }

            @Override
            public void onFailure(Call<ValidatePincodeResponse> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });


    }


    public void getNewPinCode(String brandingID, String authorizationToken, final PCNetworkResponse listener) {

        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Authorization", "Bearer " + authorizationToken);

        Call<ValidatePincodeResponse> call = PCRestClient.pcClient.pcSendCode(brandingID, headersMap);

        call.enqueue(new Callback<ValidatePincodeResponse>() {
            @Override
            public void onResponse(Call<ValidatePincodeResponse> call, Response<ValidatePincodeResponse> response) {
                switch (response.code()) {
                    case HttpURLConnection.HTTP_OK:
                        listener.onSuccess(response);
                        break;

                    case HttpURLConnection.HTTP_UNAUTHORIZED:
                        listener.onError(response.message());
                        break;

                    case HttpURLConnection.HTTP_FORBIDDEN:
                        //TODO... The pin does not match
                        listener.onError(response.message());
                        break;
                }

            }

            @Override
            public void onFailure(Call<ValidatePincodeResponse> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });

    }
}
