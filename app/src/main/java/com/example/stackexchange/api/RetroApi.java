package com.example.stackexchange.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroApi {

    private static final String BASE_URL = "https://api.stackexchange.com/";
    private static Retrofit retrofit;

    //create and return retrofit instance
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static <T> T getService(Class<T> k) {
        return getRetrofitInstance().create(k);
    }

}
