package com.applicaster.pinverification.interfaces;

public interface PCNetworkResponse<T> {


    void onSuccess(T data);

    void onError(T data);

    void onFailure(T data);
}
