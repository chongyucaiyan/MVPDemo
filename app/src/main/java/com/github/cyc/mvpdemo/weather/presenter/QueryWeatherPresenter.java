package com.github.cyc.mvpdemo.weather.presenter;

import android.util.Log;

import com.github.cyc.mvpdemo.http.RetrofitManager;
import com.github.cyc.mvpdemo.weather.QueryWeatherContract;
import com.github.cyc.mvpdemo.weather.model.WeatherData;
import com.github.cyc.mvpdemo.weather.model.WeatherInfo;
import com.github.cyc.mvpdemo.weather.request.QueryWeatherRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cyc on 2018/1/19.
 */

public class QueryWeatherPresenter implements QueryWeatherContract.Presenter {

    private static final String TAG = "QueryWeatherPresenter";

    // View
    private QueryWeatherContract.View mView;

    private Call<WeatherData> mCall;

    public QueryWeatherPresenter(QueryWeatherContract.View view) {
        mView = view;
    }

    @Override
    public void queryWeather() {
        mView.showLoading(true);
        mView.showWeatherInfo(false);
        mView.showLoadingFailure(false);
        mView.setQueryWeatherBtnEnabled(false);

        mCall = RetrofitManager.get()
                .create(QueryWeatherRequest.class)
                .queryWeather();
        mCall.enqueue(new Callback<WeatherData>() {

            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                WeatherInfo weatherInfo = response.body().getWeatherinfo();
                mView.setCity(weatherInfo.getCity());
                mView.setCityId(weatherInfo.getCityid());
                mView.setTemp1(weatherInfo.getTemp1());
                mView.setTemp2(weatherInfo.getTemp2());
                mView.setWeather(weatherInfo.getWeather());
                mView.setReleaseTime(weatherInfo.getPtime());
                mView.showWeatherInfo(true);

                mView.showLoading(false);
                mView.setQueryWeatherBtnEnabled(true);
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                if (call.isCanceled()) {
                    Log.i(TAG, "call is canceled.");
                } else {
                    mView.showLoadingFailure(true);

                    mView.showLoading(false);
                    mView.setQueryWeatherBtnEnabled(true);
                }
            }
        });
    }

    @Override
    public void cancelRequest() {
        if (mCall != null) {
            mCall.cancel();
        }
    }
}
