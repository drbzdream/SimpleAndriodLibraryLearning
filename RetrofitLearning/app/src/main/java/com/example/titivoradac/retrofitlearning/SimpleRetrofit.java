package com.example.titivoradac.retrofitlearning;

import retrofit.http.GET;

/**
 * Created by titivorada.c on 7/19/2017.
 */

public class SimpleRetrofit {

    @GET("/shots/21603")
    Shot getShot();
}
