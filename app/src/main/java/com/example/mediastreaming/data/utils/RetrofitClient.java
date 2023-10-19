package com.example.mediastreaming.data.utils;

import com.example.mediastreaming.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient _instance;
    private final APIs apIs;

    private RetrofitClient(){
        Retrofit client = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apIs =client.create(APIs.class);
    }

    public static synchronized RetrofitClient getInstance(){
        if(_instance==null){
            _instance = new RetrofitClient();
        }
        return _instance;
    }
    public APIs getApIs(){
        return apIs;
    }
}
