package guohuayu.com.iweather.data.db.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedDelete;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import guohuayu.com.iweather.data.db.WeatherDatabaseHelper;
import guohuayu.com.iweather.data.db.entities.weatherEntities.AirQuality;
import guohuayu.com.iweather.data.db.entities.weatherEntities.LifeIndex;
import guohuayu.com.iweather.data.db.entities.weatherEntities.Weather;
import guohuayu.com.iweather.data.db.entities.weatherEntities.WeatherForecast;
import guohuayu.com.iweather.data.db.entities.weatherEntities.WeatherLive;

/**
 * Created by Administrator on 2018/12/15.
 */

public class WeatherDao {
    private Context context;

    private Dao<AirQuality, String> aqDaoOperation;
    private Dao<WeatherForecast, Long> forecastDaoOperation;
    private Dao<LifeIndex, Long> lifeIndexDaoOperation;
    private Dao<WeatherLive, String> liveDaoOperation;
    private Dao<Weather, String> weatherDaoOperation;

    @Inject
    WeatherDao(Context context) {
        this.context = context;
        this.aqDaoOperation = aqDaoOperation;
        this.forecastDaoOperation = forecastDaoOperation;
        this.lifeIndexDaoOperation = lifeIndexDaoOperation;
        this.liveDaoOperation = liveDaoOperation;
        this.weatherDaoOperation = weatherDaoOperation;
    }

    //通过cityId查询weather
    public Weather queryWeatherById(String cityId) throws SQLException {
        return TransactionManager.callInTransaction(WeatherDatabaseHelper.getInstance(context).getConnectionSource(),() ->{//匿名函数
            Weather weather = weatherDaoOperation.queryForId(cityId);
            if(weather != null){
                weather.setWeatherLive(liveDaoOperation.queryForId(cityId));
                weather.setWeatherForecasts(forecastDaoOperation.queryForEq(WeatherForecast.CITY_ID_FIELD_NAME, cityId));
                weather.setLifeIndexes(lifeIndexDaoOperation.queryForEq(WeatherForecast.CITY_ID_FIELD_NAME, cityId));
                weather.setAirQuality(aqDaoOperation.queryForId(cityId));
            }
            return weather;
        });
    }

    public void insertOrUpdateWeather(Weather weather) throws SQLException{
        TransactionManager.callInTransaction(WeatherDatabaseHelper.getInstance(context).getConnectionSource(), ()->{
            if(weatherDaoOperation.idExists(weather.getCityId())){
                updateWeather(weather);
            }else{
                insertWeather(weather);
            }
            return null;
        });
    }

    public void delteById(String cityId) throws SQLException {
        weatherDaoOperation.deleteById(cityId);
    }

    private void delete(Weather data) throws SQLException {
        weatherDaoOperation.delete(data);
    }

    /*
    * 查询数据库中所有已添加的城市，返回城市信息 不包括天气信息
    * */
    public List<Weather> queryAllSaveCity() throws SQLException {
        //获取weather表中所有数据
        List<Weather> weatherList = weatherDaoOperation.queryForAll();

        for(Weather weather : weatherList){
            String cityId = weather.getCityId();
            weather.setWeatherLive(liveDaoOperation.queryForId(cityId));
            weather.setWeatherForecasts(forecastDaoOperation.queryForEq(WeatherForecast.CITY_ID_FIELD_NAME, cityId));
            weather.setLifeIndexes(lifeIndexDaoOperation.queryForEq(WeatherForecast.CITY_ID_FIELD_NAME, cityId));
            weather.setAirQuality(aqDaoOperation.queryForId(cityId));
        }
        return weatherList;
    }

    //将该weather对象插入到表中
    private void insertWeather(Weather weather) throws SQLException {
        //为该weather对象在表中创建一个新行
        weatherDaoOperation.create(weather);

        aqDaoOperation.create(weather.getAirQuality());

        liveDaoOperation.create(weather.getWeatherLive());

        for(LifeIndex lifeIndex : weather.getLifeIndexes()){
            lifeIndexDaoOperation.create(lifeIndex);
        }

        for (WeatherForecast forecast : weather.getWeatherForecasts()){
            forecastDaoOperation.create(forecast);
        }
    }

    private void updateWeather(Weather weather) throws SQLException {
        weatherDaoOperation.update(weather);
        aqDaoOperation.update(weather.getAirQuality());
        liveDaoOperation.update(weather.getWeatherLive());

        //先删除旧数据
        DeleteBuilder<WeatherForecast, Long> forecastDeleteBuilder = forecastDaoOperation.deleteBuilder();
        forecastDeleteBuilder.where().eq(WeatherForecast.CITY_ID_FIELD_NAME, weather.getCityId());
        PreparedDelete<WeatherForecast> forecastPrepared = forecastDeleteBuilder.prepare();
        forecastDaoOperation.delete(forecastPrepared);
        //插入新数据
        for (WeatherForecast forecast : weather.getWeatherForecasts()){
            forecastDaoOperation.create(forecast);
        }

        //先删除旧数据
        DeleteBuilder<LifeIndex, Long> indexDeleteBuilder = lifeIndexDaoOperation.deleteBuilder();
        indexDeleteBuilder.where().eq(WeatherForecast.CITY_ID_FIELD_NAME, weather.getCityId());
        PreparedDelete<LifeIndex> indexPrepared = indexDeleteBuilder.prepare();
        lifeIndexDaoOperation.delete(indexPrepared);
        //插入新数据
        for (LifeIndex index : weather.getLifeIndexes()){
            lifeIndexDaoOperation.create(index);
        }
    }
}
