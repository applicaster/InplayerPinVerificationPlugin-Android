package com.applicaster.pinverification.networking;

import android.text.TextUtils;
import android.util.Log;

import com.applicaster.pinverification.interfaces.PCNetworkResponse;
import com.applicaster.pinverification.models.networkresponse.ValidatePincodeResponse;
import com.applicaster.pinverification.models.request.GetPinRequest;
import com.applicaster.pinverification.models.request.PinValidationRequest;

import org.json.JSONObject;

import java.net.HttpURLConnection;

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

    public void setPinCode(String pinCode, String publisherID, String authorizationToken, final PCNetworkResponse listener) {
        PinValidationRequest body = new PinValidationRequest(authorizationToken, publisherID, pinCode);

        Call<ValidatePincodeResponse> call = PCRestClient.pcClient.pcValidatePinCode(body);
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

                    case HttpURLConnection.HTTP_BAD_REQUEST:
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


    public void getNewPinCode(String authorizationToken, String publisherId, final PCNetworkResponse listener) {
        GetPinRequest body = new GetPinRequest(publisherId, authorizationToken);

        Call<ValidatePincodeResponse> call = PCRestClient.pcClient.pcSendCode(body);
        call.enqueue(new Callback<ValidatePincodeResponse>() {
            @Override
            public void onResponse(Call<ValidatePincodeResponse> call, Response<ValidatePincodeResponse> response) {
                switch (response.code()) {
                    case HttpURLConnection.HTTP_OK:
                        listener.onSuccess(response.body().getMessage());
                        break;

                    case HttpURLConnection.HTTP_UNAUTHORIZED:
                        listener.onError(getErrorMessage(response));
                        break;

                    case HttpURLConnection.HTTP_FORBIDDEN:
                        listener.onError(getErrorMessage(response));
                        break;

                    case HttpURLConnection.HTTP_BAD_REQUEST:
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
            if (!TextUtils.isEmpty(response.message())) {
                errorMessage = response.message();
            } else {
                String data = response.errorBody().string();
                JSONObject jObj = new JSONObject(data);
                errorMessage = jObj.getString("message");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return errorMessage;
    }
}
