package com.sih.resthousefe;

import com.sih.resthousefe.pojo.Station;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface  RailwayPNRServices {
    @GET("v2/pnr-status/pnr/{pnrno}/apikey/dmkod2e96n/")
    Call<Station> listStations(@Path("pnrno") String pnrno);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.railwayapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
