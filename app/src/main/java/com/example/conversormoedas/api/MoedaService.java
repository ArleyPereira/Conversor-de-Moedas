package com.example.conversormoedas.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoedaService {

    @GET(".")
    Call<Object> getMoedas(
            @Query("fields") String fields,
            @Query("key") String key
    );


}
