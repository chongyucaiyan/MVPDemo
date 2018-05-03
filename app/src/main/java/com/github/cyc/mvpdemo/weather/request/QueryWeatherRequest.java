package com.github.cyc.mvpdemo.weather.request;

import com.github.cyc.mvpdemo.weather.model.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by cyc on 18/5/3.
 */

public interface QueryWeatherRequest {

    @GET("data/cityinfo/101210101.html")
    Call<WeatherData> queryWeather();

}
