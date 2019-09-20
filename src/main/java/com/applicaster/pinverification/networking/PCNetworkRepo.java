package com.applicaster.pinverification.networking;

import com.applicaster.pinverification.interfaces.PCNetworkResponse;
import com.applicaster.pinverification.models.networkresponse.ValidatePincodeResponse;

import org.json.JSONObject;

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
                        listener.onError(getErrorMessage(response));
                        break;

                    case HttpURLConnection.HTTP_FORBIDDEN:

                        listener.onError(getErrorMessage(response));

                        break;
                    default:
                        listener.onError(getErrorMessage(response));
                }
            }

            @Override
            public void onFailure(Call<ValidatePincodeResponse> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }


    public void getNewPinCode(String authorizationToken, final PCNetworkResponse listener) {

        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Authorization", "Bearer " + authorizationToken);

        Call<ValidatePincodeResponse> call = PCRestClient.pcClient.pcSendCode(headersMap);

        call.enqueue(new Callback<ValidatePincodeResponse>() {
            @Override
            public void onResponse(Call<ValidatePincodeResponse> call, Response<ValidatePincodeResponse> response) {
                switch (response.code()) {
                    case HttpURLConnection.HTTP_OK:
                        listener.onSuccess(response);
                        break;

                    case HttpURLConnection.HTTP_UNAUTHORIZED:
                        listener.onError(getErrorMessage(response));
                        break;

                    case HttpURLConnection.HTTP_FORBIDDEN:

                        listener.onError(getErrorMessage(response));

                        break;
                    default:
                        listener.onError(getErrorMessage(response));
                }
            }

            @Override
            public void onFailure(Call<ValidatePincodeResponse> call, Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }

    private String getErrorMessage(Response<ValidatePincodeResponse> response) {

        String errorMessage = "Failed";

        try {
            String data = response.errorBody().string();
            JSONObject jObj = new JSONObject(data);
            errorMessage = jObj.getString("message");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return errorMessage;
    }
}
