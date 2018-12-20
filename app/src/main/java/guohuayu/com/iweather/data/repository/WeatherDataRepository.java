package guohuayu.com.iweather.data.repository;

import android.content.Context;
import android.text.TextUtils;

import com.guohuayu.android.library.util.NetworkUtils;

import java.sql.SQLException;

import guohuayu.com.iweather.data.db.dao.WeatherDao;
import guohuayu.com.iweather.data.db.entities.adapter.WeatherAdapter;
import guohuayu.com.iweather.data.db.entities.weatherEntities.Weather;
import guohuayu.com.iweather.data.http.ApiClient;
import guohuayu.com.iweather.data.http.entity.CityAirLiveEntity;
import guohuayu.com.iweather.data.http.entity.WeatherForecastEntity;
import guohuayu.com.iweather.data.http.entity.WeatherLiveEntity;
import rx.Observable;
import rx.exceptions.Exceptions;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/12/15.
 *          天气数据仓库 核心在于将api获取来的信息与本地对比 当发生改变则将改变更新到本地数据库中
 */

public class WeatherDataRepository {
    /*
    * 返回一个被观察者对象用于rxjava与一个观察者对象绑定
    * 先从本地数据库读数据，若网络不可用则直接返回本地数据；否则从网络获取数据并更新到本地数据
    * */
    public static Observable<Weather> getWeather(Context context, String cityId, WeatherDao weatherDao,
                                                 boolean refreshNow){
        //从本地数据库获取数据 rxjava事件流式调用风格  Observable被观察者 subscriber观察者
        Observable<Weather> observableWeatherFromDB = Observable.create(subscriber -> {
            try {
                Weather weather = weatherDao.queryWeatherById(cityId);
                subscriber.onNext(weather);//指定下一个事件
                subscriber.onCompleted();
            } catch (SQLException e) {
                throw Exceptions.propagate(e);
            }
        });

        //当网络不可用直接返回本地数据库中数据
        if(!NetworkUtils.isNetworkConnected(context)){
            return observableWeatherFromDB;
        }

        //从网络API获取数据
        Observable<Weather> observableWeatherFromNetWork = null;
        Observable<WeatherLiveEntity> weatherLiveObservable = ApiClient.environmentCloudWeatherService.getWeatherLive(cityId);
        Observable<WeatherForecastEntity> weatherForecastObservable = ApiClient.environmentCloudWeatherService.getWeatherForecast(cityId);
        Observable<CityAirLiveEntity> cityAirLiveObservable = ApiClient.environmentCloudWeatherService.getCityAireLive(cityId);

        /*
        *  核心
        *  通过rxjava观察者模式
        * */

        //将以上三个被观察者（weatherlive， weatherforecast和cityAirLive）绑定为一个聚合被观察者即weather
        observableWeatherFromNetWork = Observable.combineLatest(cityAirLiveObservable, weatherForecastObservable, weatherLiveObservable,
                (airLive, weatherForecast, weatherLive) -> new WeatherAdapter(airLive, weatherForecast, weatherLive).getWeather());

        /*
        * 指定当被观察者执行onNext（）的行为--->将网络获取来的天气信息插入或更新到本地数据中
        * */
        assert observableWeatherFromNetWork != null;
        observableWeatherFromNetWork = observableWeatherFromNetWork.doOnNext(weather -> Schedulers.io().createWorker().schedule(()->{
            try {
                weatherDao.insertOrUpdateWeather(weather);
            } catch (SQLException e) {
                throw Exceptions.propagate(e);
            }
        }));

        return Observable.concat(observableWeatherFromDB, observableWeatherFromNetWork)
                .filter(weather -> weather != null && !TextUtils.isEmpty(weather.getCityId()))//过滤掉空weather
                .distinct(weather -> weather.getWeatherLive().getUpdatetime())//去重
                .takeUntil(weather -> !refreshNow && System.currentTimeMillis() - weather.getWeatherLive().getUpdatetime() <= 15 * 60 * 1000);//只保留更新时间为近期的数据
    }
}
