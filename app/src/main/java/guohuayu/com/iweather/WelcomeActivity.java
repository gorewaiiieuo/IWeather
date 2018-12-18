package guohuayu.com.iweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.InvalidClassException;

import guohuayu.com.iweather.data.db.CityDatabaseHelper;
import guohuayu.com.iweather.data.preference.PreferenceHelper;
import guohuayu.com.iweather.data.preference.WeatherSettings;
import guohuayu.com.iweather.feature.homePage.MainActivity;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*
*未完成
* 用于实现启动前的 欢迎界面
* */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Observable.just(initAppData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> gotoMainPage());
    }

    private void gotoMainPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /*
    * 初始化APP数据
    * */
    private String initAppData(){
        PreferenceHelper.loadDefaults();
        //初次打开APP时预设一个当前城市
        if(PreferenceHelper.getSharedPreferences().getBoolean(WeatherSettings.SETTINGS_FIRST_USE.getmId(),false)){
            try {
                PreferenceHelper.savePreference(WeatherSettings.SETTINGS_FIRST_USE, false);
                PreferenceHelper.savePreference(WeatherSettings.SETTINGS_CURRENT_CITY_ID, "101040100");
            } catch (InvalidClassException e) {
                e.printStackTrace();
            }
        }
        //加载已添加的城市列表
        CityDatabaseHelper.importCityDB();
        return null;
    }

}
