package com.github.cyc.mvpdemo.weather.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.cyc.mvpdemo.R;
import com.github.cyc.mvpdemo.weather.QueryWeatherContract;
import com.github.cyc.mvpdemo.weather.presenter.QueryWeatherPresenter;

public class QueryWeatherActivity extends AppCompatActivity implements QueryWeatherContract.View {

    // Presenter
    private QueryWeatherContract.Presenter mPresenter;

    private ViewGroup mVgWeatherInfo;

    private TextView mTvCity;

    private TextView mTvCityId;

    private TextView mTvTemp1;

    private TextView mTvTemp2;

    private TextView mTvWeather;

    private TextView mTvReleaseTime;

    private TextView mTvQueryFailure;

    private Button mBtnQueryWeather;

    private ProgressBar mPbProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_weather);
        initContentView();
        // 创建Presenter
        mPresenter = new QueryWeatherPresenter(this);
    }

    private void initContentView() {
        mVgWeatherInfo = findViewById(R.id.vg_weather_info);
        mTvCity = findViewById(R.id.tv_city_value);
        mTvCityId = findViewById(R.id.tv_city_id_value);
        mTvTemp1 = findViewById(R.id.tv_temp1_value);
        mTvTemp2 = findViewById(R.id.tv_temp2_value);
        mTvWeather = findViewById(R.id.tv_weather_value);
        mTvReleaseTime = findViewById(R.id.tv_time_value);
        mTvQueryFailure = findViewById(R.id.tv_query_failure);
        mBtnQueryWeather = findViewById(R.id.btn_query_weather);
        mPbProgress = findViewById(R.id.pb_progress);

        mBtnQueryWeather.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mPresenter.queryWeather();
            }
        });
    }

    @Override
    public void showLoading(boolean show) {
        mPbProgress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showLoadingFailure(boolean show) {
        mTvQueryFailure.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showWeatherInfo(boolean show) {
        mVgWeatherInfo.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setCity(String city) {
        mTvCity.setText(city);
    }

    @Override
    public void setCityId(String cityId) {
        mTvCityId.setText(cityId);
    }

    @Override
    public void setTemp1(String temp1) {
        mTvTemp1.setText(temp1);
    }

    @Override
    public void setTemp2(String temp2) {
        mTvTemp2.setText(temp2);
    }

    @Override
    public void setWeather(String weather) {
        mTvWeather.setText(weather);
    }

    @Override
    public void setReleaseTime(String time) {
        mTvReleaseTime.setText(time);
    }

    @Override
    public void setQueryWeatherBtnEnabled(boolean enabled) {
        mBtnQueryWeather.setEnabled(enabled);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消请求
        mPresenter.cancelRequest();
    }
}
