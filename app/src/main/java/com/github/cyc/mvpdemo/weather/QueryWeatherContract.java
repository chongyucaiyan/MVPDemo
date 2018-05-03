package com.github.cyc.mvpdemo.weather;

import com.github.cyc.mvpdemo.base.BasePresenter;
import com.github.cyc.mvpdemo.base.BaseView;

/**
 * Created by cyc on 2018/1/19.
 */

public interface QueryWeatherContract {

    interface View extends BaseView {

        void showLoading(boolean show);

        void showLoadingFailure(boolean show);

        void showWeatherInfo(boolean show);

        void setCity(String city);

        void setCityId(String cityId);

        void setTemp1(String temp1);

        void setTemp2(String temp2);

        void setWeather(String weather);

        void setReleaseTime(String time);

        void setQueryWeatherBtnEnabled(boolean enabled);

    }

    interface Presenter extends BasePresenter {

        void queryWeather();

        void cancelRequest();

    }
}
