package guohuayu.com.iweather.data.http.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Administrator on 2018/12/13.
 * 指定城市实时天气的javabean对象
 */

public class WeatherLiveEntity {

    /**
     * windspeed : 1.9
     * airpressure : 1005.0
     * phenomena : 晴
     * rdesc : Success
     * humidity : 55.0
     * updatetime : 2016-09-09 08:05
     * windpower : 微风
     * feelst : 23.0
     * winddirect : 东北风
     * rcode : 200
     * rain : 0.0
     * temperature : 23.1
     */

    private String windspeed;
    private String airpressure;
    private String phenomena;
    private String rdesc;
    private String humidity;
    private String updatetime;
    private String windpower;
    private String feelst;
    private String winddirect;
    private int rcode;
    private String rain;
    private String temperature;
    private String citycode;//城市ID

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    public String getAirpressure() {
        return airpressure;
    }

    public void setAirpressure(String airpressure) {
        this.airpressure = airpressure;
    }

    public String getPhenomena() {
        return phenomena;
    }

    public void setPhenomena(String phenomena) {
        this.phenomena = phenomena;
    }

    public String getRdesc() {
        return rdesc;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getWindpower() {
        return windpower;
    }

    public void setWindpower(String windpower) {
        this.windpower = windpower;
    }

    public String getFeelst() {
        return feelst;
    }

    public void setFeelst(String feelst) {
        this.feelst = feelst;
    }

    public String getWinddirect() {
        return winddirect;
    }

    public void setWinddirect(String winddirect) {
        this.winddirect = winddirect;
    }

    public int getRcode() {
        return rcode;
    }

    public void setRcode(int rcode) {
        this.rcode = rcode;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
