package com.example.conversormoedas.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    public static Retrofit getRetrofit(){
        return new Retrofit
                .Builder()
                .baseUrl("https://api.hgbrasil.com/finance/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
