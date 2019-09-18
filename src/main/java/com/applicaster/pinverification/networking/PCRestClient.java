package com.applicaster.pinverification.networking;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PCRestClient {

    private static final short TIME_OUT = 15000;
    public static final String BASE_URL = "https://services.inplayer.com/";

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS).build();


    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();

    public static PCNetworkApi pcClient = retrofit.create(PCNetworkApi.class);

}
