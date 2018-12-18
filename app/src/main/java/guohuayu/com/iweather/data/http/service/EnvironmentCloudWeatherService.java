package guohuayu.com.iweather.data.http.service;

import guohuayu.com.iweather.data.http.entity.CityAirLiveEntity;
import guohuayu.com.iweather.data.http.entity.WeatherForecastEntity;
import guohuayu.com.iweather.data.http.entity.WeatherLiveEntity;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2018/12/13.
 * baseURL = http://service.envicloud.cn:8082
 */

public interface EnvironmentCloudWeatherService {
    /*
    * 获取指定城市的实时天气
    *
    * API地址 = http://service.envicloud.cn:8082/v2/weatherlive/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/101020100
    * */
    @GET("/v2/weatherlive/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/{citycode}")
    Observable<WeatherLiveEntity> getWeatherLive(@Path("citycode")String citycode);


    /*
    * 获取指定城市的未来7天天气预报
    *
    * API地址 = http://service.envicloud.cn:8082/v2/weatherforecast/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/101020100
    * */
    @GET("/v2/weatherforecast/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/{citycode}")
    Observable<WeatherForecastEntity> getWeatherForecast(@Path("citycode")String citycode);

    /*
    * 获取指定城市的实时空气质量
    *
    * API地址 = http://service.envicloud.cn:8082/v2/cityairlive/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/101020100
    * */
    @GET("/v2/cityairlive/YMFYB256AGFUZZE0ODQ3MZM1MZE2NTU=/{citycode}")
    Observable<CityAirLiveEntity> getCityAireLive(@Path("citycode")String citycode);
}
