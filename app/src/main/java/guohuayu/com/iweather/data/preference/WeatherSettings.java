package guohuayu.com.iweather.data.preference;

/**
 * Created by Administrator on 2018/12/17.
 * 用户设置的javaBean
 */

public enum WeatherSettings {
    SETTINGS_FIRST_USE("first_use", Boolean.TRUE),

    SETTINGS_CURRENT_CITY_ID("current_city_id", "");

    private final String mId;

    private final Object mDefaultValue;

    public String getmId() {
        return mId;
    }

    public Object getmDefaultValue() {
        return mDefaultValue;
    }

    WeatherSettings(String id, Object defaultValue){
        this.mId = id;
        this.mDefaultValue = defaultValue;
    }

    public static WeatherSettings fromId(String id){
        WeatherSettings[] values = values();
        for(WeatherSettings value : values){
            if(value.mId.equals(id)){
                return value;
            }
        }
        return null;
    }
}
