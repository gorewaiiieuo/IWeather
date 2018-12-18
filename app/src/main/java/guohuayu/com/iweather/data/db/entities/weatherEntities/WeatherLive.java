package guohuayu.com.iweather.data.db.entities.weatherEntities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.WeakHashMap;

/**
 * Created by Administrator on 2018/12/14.
 */
@DatabaseTable(tableName = "WeatherLive")
public class WeatherLive {
    //以下为sqlite数据库表中行名
    public static final String CITY_ID_FILE_NAME = "cityId";//*****api中live实际无cityCode???
    public static final String UPDATETIME_FILE_NAME = "updateTime";
    public static final String WEATHER_FILE_NAME = "weather";
    public static final String TEMPERATURE_FILE_NAME = "temperature";
    public static final String FEEL_TEMPERATURE_FILE_NAME = "feelTemp";
    public static final String AIREPRESSURE_FILE_NAME = "airpressure";
    public static final String HUMIDITY_FILE_NAME = "humidity";
    public static final String RAIN_FILE_NAME = "rain";
    public static final String WIND_DIRECT_FILE_NAME = "windDirect";
    public static final String WIND_POWER_FILE_NAME = "windPower";
    public static final String WIND_SPEED_FILE_NAME = "windSpeed";


    //以下为javabean中名字
    //指定cityID为主键，不自增长
    @DatabaseField(columnName = CITY_ID_FILE_NAME, id = true)
    private String cityID;	//城市编号 *****api中live实际无cityCode???

    @DatabaseField(columnName = UPDATETIME_FILE_NAME)
    private long updatetime;	//更新时间

    @DatabaseField(columnName = WEATHER_FILE_NAME)
    private String weather;	//天气现象

    @DatabaseField(columnName = TEMPERATURE_FILE_NAME)
    private String temperature;	//气温(℃)

    @DatabaseField(columnName = FEEL_TEMPERATURE_FILE_NAME)
    private String feelTemperature;	//体感温度(℃)

    @DatabaseField(columnName = AIREPRESSURE_FILE_NAME)
    private String airpressure;	//气压(hPa)

    @DatabaseField(columnName = HUMIDITY_FILE_NAME)
    private String humidity;	//相对湿度(%)

    @DatabaseField(columnName = RAIN_FILE_NAME)
    private String rain;//降雨量(mm)

    @DatabaseField(columnName = WIND_DIRECT_FILE_NAME)
    private String winddirect;	//风向

    @DatabaseField(columnName = WIND_POWER_FILE_NAME)
    private String windpower;	//风力

    @DatabaseField(columnName = WIND_SPEED_FILE_NAME)
    private String windspeed;	//风速(m/s)

    public WeatherLive(){

    }

    public WeatherLive(String cityID, long updatetime, String weather,
                       String feelTemperature, String humidity, String winddirect, String windpower) {
        this.cityID = cityID;
        this.updatetime = updatetime;
        this.weather = weather;
        this.feelTemperature = feelTemperature;
        this.humidity = humidity;
        this.winddirect = winddirect;
        this.windpower = windpower;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(long updatetime) {
        this.updatetime = updatetime;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getFeelTemperature() {
        return feelTemperature;
    }

    public void setFeelTemperature(String feelTemperature) {
        this.feelTemperature = feelTemperature;
    }

    public String getAirpressure() {
        return airpressure;
    }

    public void setAirpressure(String airpressure) {
        this.airpressure = airpressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getWinddirect() {
        return winddirect;
    }

    public void setWinddirect(String winddirect) {
        this.winddirect = winddirect;
    }

    public String getWindpower() {
        return windpower;
    }

    public void setWindpower(String windpower) {
        this.windpower = windpower;
    }

    public String getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    @Override
    public String toString() {
        return "WeatherLive{" +
                "cityID='" + cityID + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", weather='" + weather + '\'' +
                ", temperature='" + temperature + '\'' +
                ", feelTemperature='" + feelTemperature + '\'' +
                ", airpressure='" + airpressure + '\'' +
                ", humidity='" + humidity + '\'' +
                ", rain='" + rain + '\'' +
                ", winddirect='" + winddirect + '\'' +
                ", windpower='" + windpower + '\'' +
                ", windspeed='" + windspeed + '\'' +
                '}';
    }
}
