package com.urban.androidhomework;

import android.content.Context;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {

    private static NetworkClient instance;
    private NetworkApi service;
    private Retrofit retrofit;

    public static synchronized NetworkClient get() {
        if (instance == null) {
            instance = new NetworkClient();
        }
        return instance;
    }

    public void setup(Context context) {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();

        service = retrofit.create(NetworkApi.class);
    }

    public NetworkApi getService() {
        return service;
    }
}
