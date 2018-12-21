package guohuayu.com.iweather.data.db.entities.adapter;

import com.guohuayu.android.library.util.DateConvertUtils;

import java.util.ArrayList;
import java.util.List;

import guohuayu.com.iweather.data.db.entities.weatherEntities.AirQuality;
import guohuayu.com.iweather.data.db.entities.weatherEntities.LifeIndex;
import guohuayu.com.iweather.data.db.entities.weatherEntities.Weather;
import guohuayu.com.iweather.data.db.entities.weatherEntities.WeatherForecast;
import guohuayu.com.iweather.data.db.entities.weatherEntities.WeatherLive;
import guohuayu.com.iweather.data.http.entity.CityAirLiveEntity;
import guohuayu.com.iweather.data.http.entity.WeatherForecastEntity;
import guohuayu.com.iweather.data.http.entity.WeatherLiveEntity;

/**
 * Created by Administrator on 2018/12/14.
 *
 *      用于将四个weatherEntities中信息整合到Weather中
 *
 */

public class WeatherAdapter {
    private CityAirLiveEntity cityAirLiveEntity;
    private WeatherForecastEntity weatherForecastEntity;
    private WeatherLiveEntity weatherLiveEntity;

    public WeatherAdapter(CityAirLiveEntity cityAirLiveEntity, WeatherForecastEntity weatherForecastEntity, WeatherLiveEntity weatherLiveEntity) {
        this.cityAirLiveEntity = cityAirLiveEntity;
        this.weatherForecastEntity = weatherForecastEntity;
        this.weatherLiveEntity = weatherLiveEntity;
    }

    public String getCityId(){
        return weatherLiveEntity.getCitycode();
    }

    public String getCityName(){
        return weatherForecastEntity.getCityname();
    }

    public String getCityNameEng(){
        return null;
    }

    public WeatherLive getWeatherLive(){
        WeatherLive weatherLive = new WeatherLive();
        weatherLive.setAirpressure(weatherLiveEntity.getAirpressure());
        weatherLive.setCityID(weatherLiveEntity.getCitycode());//*****api中live实际无cityCode???
        weatherLive.setFeelTemperature(weatherLiveEntity.getFeelst());
        weatherLive.setHumidity(weatherLiveEntity.getHumidity());
        weatherLive.setRain(weatherLiveEntity.getRain());
        weatherLive.setTemperature(weatherLiveEntity.getTemperature());
        weatherLive.setUpdatetime(DateConvertUtils.dateToTimeStamp(weatherLiveEntity.getUpdatetime(), DateConvertUtils.DATA_FORMAT_PATTEN_YYYY_MM_DD_HH_MM));
        weatherLive.setWeather(weatherLiveEntity.getPhenomena());
        weatherLive.setWinddirect(weatherLiveEntity.getWinddirect());
        weatherLive.setWindpower(weatherLiveEntity.getWindpower());
        weatherLive.setWindspeed(weatherLiveEntity.getWindspeed());

        System.out.println("********weatherlive.getCityCode(): " + weatherLiveEntity.getCitycode());
        return weatherLive;
    }

    public List<WeatherForecast> getWeatherForecast(){
        List<WeatherForecast> weatherForecasts = new ArrayList<>();

        for(WeatherForecastEntity.ForecastBean forecastBean : weatherForecastEntity.getForecast()){
            WeatherForecast weatherForecast = new WeatherForecast();
            weatherForecast.setWind(forecastBean.getWind().getDir() + forecastBean.getWind().getSc());
            weatherForecast.setCityId(getCityId());
            weatherForecast.setHumidity(forecastBean.getHum());
            weatherForecast.setMoonrise(forecastBean.getAstro().getMr());
            weatherForecast.setMoonset(forecastBean.getAstro().getMs());
            weatherForecast.setPop(forecastBean.getPop());
            weatherForecast.setPrecipitation(forecastBean.getPcpn());
            weatherForecast.setPressure(forecastBean.getPres());
            weatherForecast.setSunrise(forecastBean.getAstro().getSr());
            weatherForecast.setSunset(forecastBean.getAstro().getSs());
            weatherForecast.setTempMax(Integer.parseInt(forecastBean.getTmp().getMax()));
            weatherForecast.setTempMin(Integer.parseInt(forecastBean.getTmp().getMin()));
            weatherForecast.setUv(forecastBean.getUv());
            weatherForecast.setVisibility(forecastBean.getVis());
            weatherForecast.setWeatherDay(forecastBean.getCond().getCond_d());
            weatherForecast.setWeatherNight(forecastBean.getCond().getCond_n());
            weatherForecast.setWeek(DateConvertUtils.convertDataToWeek(forecastBean.getDate()));
            weatherForecast.setDate(DateConvertUtils.convertDataToString(forecastBean.getDate()));
            weatherForecasts.add(weatherForecast);
        }
        return weatherForecasts;
    }

    //LifeIndex(String cityId, String name, String index, String details)
    public List<LifeIndex> getLifeIndex(){
        List<LifeIndex> lifeIndexes = new ArrayList<>();

        WeatherForecastEntity.SuggestionBean suggestionEntity = weatherForecastEntity.getSuggestion();

        LifeIndex index1 = new LifeIndex();
        index1.setCityId(weatherForecastEntity.getCitycode());
        index1.setName("舒适度");
        index1.setIndex(suggestionEntity.getComf().getBrf());
        index1.setDetails(suggestionEntity.getComf().getTxt());
        lifeIndexes.add(index1);

        LifeIndex index2 = new LifeIndex();
        index2.setCityId(weatherForecastEntity.getCitycode());
        index2.setName("穿衣");
        index2.setIndex(suggestionEntity.getDrs().getBrf());
        index2.setDetails(suggestionEntity.getDrs().getTxt());
        lifeIndexes.add(index2);

        LifeIndex index3 = new LifeIndex();
        index3.setCityId(weatherForecastEntity.getCitycode());
        index3.setName("感冒");
        index3.setIndex(suggestionEntity.getFlu().getBrf());
        index3.setDetails(suggestionEntity.getFlu().getTxt());
        lifeIndexes.add(index3);

        LifeIndex index4 = new LifeIndex();
        index4.setCityId(weatherForecastEntity.getCitycode());
        index4.setName("运动");
        index4.setIndex(suggestionEntity.getSport().getBrf());
        index4.setDetails(suggestionEntity.getSport().getTxt());
        lifeIndexes.add(index4);

        LifeIndex index5 = new LifeIndex();
        index5.setCityId(weatherForecastEntity.getCitycode());
        index5.setName("旅游");
        index5.setIndex(suggestionEntity.getTrav().getBrf());
        index5.setDetails(suggestionEntity.getTrav().getTxt());
        lifeIndexes.add(index5);

        LifeIndex index6 = new LifeIndex();
        index6.setCityId(weatherForecastEntity.getCitycode());
        index6.setName("洗车");
        index6.setIndex(suggestionEntity.getCw().getBrf());
        index6.setDetails(suggestionEntity.getCw().getTxt());
        lifeIndexes.add(index6);

        return lifeIndexes;
    }

    public AirQuality getAirQualityLive() {

        AirQuality airQualityLive = new AirQuality();
//        airQualityLive.setAdvice(cityAirLiveEntity.getA);
        airQualityLive.setAqi(Integer.parseInt(cityAirLiveEntity.getAQI()));
        airQualityLive.setCityId(cityAirLiveEntity.getCitycode());
//        airQualityLive.setCityRank("");
        airQualityLive.setCo(cityAirLiveEntity.getCO());
        airQualityLive.setNo2(cityAirLiveEntity.getNO2());
        airQualityLive.setO3(cityAirLiveEntity.getO3());
        airQualityLive.setPm10(Integer.parseInt(cityAirLiveEntity.getPM10()));
        airQualityLive.setPm25(Integer.parseInt(cityAirLiveEntity.getPM25()));
        airQualityLive.setPrimary(cityAirLiveEntity.getPrimary());
        airQualityLive.setPublishTime(cityAirLiveEntity.getTime());
        airQualityLive.setQuality(getAqiQuality(airQualityLive.getAqi()));
        airQualityLive.setSo2(cityAirLiveEntity.getSO2());
        return airQualityLive;
    }

    private String getAqiQuality(int aqi) {

        if (aqi <= 50) {
            return "优";
        } else if (aqi > 50 && aqi <= 100) {
            return "良";
        } else if (aqi > 100 && aqi <= 150) {
            return "轻度污染";
        } else if (aqi > 150 && aqi <= 200) {
            return "中度污染";
        } else if (aqi > 200 && aqi <= 300) {
            return "重度污染";
        } else if (aqi > 300 && aqi < 500) {
            return "严重污染";
        } else if (aqi >= 500) {
            return "污染爆表";
        }
        return null;
    }

    public Weather getWeather(){
        Weather weather = new Weather();
        weather.setCityId(getCityId());
        weather.setCityName(getCityName());
        weather.setCityNameEn(getCityNameEng());

        weather.setAirQuality(getAirQualityLive());
        weather.setLifeIndexes(getLifeIndex());
        weather.setWeatherForecasts(getWeatherForecast());
        weather.setWeatherLive(getWeatherLive());

        return weather;
    }
}
