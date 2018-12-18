package guohuayu.com.iweather.data.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import guohuayu.com.iweather.WeatherApplication;

/**
 * Created by Administrator on 2018/12/17.
 */

public final class PreferenceHelper {
    private static final String SETTINGS_FILENAME = WeatherApplication.class.getPackage().getName();

    private static final List<ConfigurationListener> CONFIGURATION_LISTENERS = Collections.synchronizedList(new ArrayList<>());

    private PreferenceHelper(){
        super();
    }

    //设置sharedpreference默认值
    public static void loadDefaults() {
        try {
            Map<WeatherSettings, Object> defaultPrefs = new HashMap<>();
            WeatherSettings[] values = WeatherSettings.values();
            for(WeatherSettings value : values){
                defaultPrefs.put(value, value.getmDefaultValue());
            }
            savePreferences(defaultPrefs, true);
        } catch (InvalidClassException e) {
            System.out.println("loadDefaults failed");
        }
    }

    public static void addConfigurationListener(ConfigurationListener listener) {
        CONFIGURATION_LISTENERS.add(listener);
    }

    public static void removeConfigurationListener(ConfigurationListener listener) {
        CONFIGURATION_LISTENERS.remove(listener);
    }

    public static SharedPreferences getSharedPreferences() {
        return WeatherApplication.getInstance().getSharedPreferences(
                SETTINGS_FILENAME, Context.MODE_PRIVATE);
    }

    public static void savePreference(WeatherSettings pref, Object value) throws InvalidClassException {
        Map<WeatherSettings, Object> prefs = new HashMap<>();
        prefs.put(pref, value);
        savePreferences(prefs, false);
    }

    public static void savePreferences(Map<WeatherSettings, Object> prefs) throws InvalidClassException {

        savePreferences(prefs, false);
    }

    private static void savePreferences(Map<WeatherSettings, Object> prefs, boolean noSaveIfExists) throws InvalidClassException {

        SharedPreferences sp = getSharedPreferences();
        SharedPreferences.Editor editor = sp.edit();

        for (WeatherSettings pref : prefs.keySet()) {

            Object value = prefs.get(pref);

            if (noSaveIfExists && sp.contains(pref.getmId())) {
                continue;
            }

            //类型识别后将不同类型的value分别按对应的键值对存入sharedPreference
            if (value instanceof Boolean && pref.getmDefaultValue() instanceof Boolean) {
                editor.putBoolean(pref.getmId(), (Boolean) value);
            } else if (value instanceof String && pref.getmDefaultValue() instanceof String) {
                editor.putString(pref.getmId(), (String) value);
            } else if (value instanceof Set && pref.getmDefaultValue() instanceof Set) {
                editor.putStringSet(pref.getmId(), (Set<String>) value);
            } else if (value instanceof Integer && pref.getmDefaultValue() instanceof Integer) {
                editor.putInt(pref.getmId(), (Integer) value);
            } else if (value instanceof Float && pref.getmDefaultValue() instanceof Float) {
                editor.putFloat(pref.getmId(), (Float) value);
            } else if (value instanceof Long && pref.getmDefaultValue() instanceof Long) {
                editor.putLong(pref.getmId(), (Long) value);
            } else {
                //The object is not of the appropriate type
                System.out.println("Configuration error. InvalidClassException: %s");
                throw new InvalidClassException(String.format("%s: %s", pref.getmId(), value.getClass().getName()));
            }
        }

        editor.apply();

        if (CONFIGURATION_LISTENERS != null && CONFIGURATION_LISTENERS.size() > 0) {
            for (WeatherSettings pref : prefs.keySet()) {
                Object value = prefs.get(pref);
                for (ConfigurationListener listener : CONFIGURATION_LISTENERS) {
                    listener.onConfigurationChanged(pref, value);
                }
            }
        }
    }
}
