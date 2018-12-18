package guohuayu.com.iweather.data.preference;

/**
 * Created by Administrator on 2018/12/17.
 */

public interface ConfigurationListener {
    void onConfigurationChanged(WeatherSettings prefs, Object newValue);
}
