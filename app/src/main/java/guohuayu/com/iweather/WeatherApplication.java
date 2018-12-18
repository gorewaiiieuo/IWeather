package guohuayu.com.iweather;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import guohuayu.com.iweather.data.http.ApiClient;
import guohuayu.com.iweather.di.component.ApplicationComponent;
import guohuayu.com.iweather.di.component.DaggerApplicationComponent;
import guohuayu.com.iweather.di.module.ApplicationModule;

/**
 * Created by Administrator on 2018/12/12.
 * 这是一个用于dagger2的 applicationBean对象
 */

public class WeatherApplication extends Application{
    private static final String TAG = "WeatherApp";

    private ApplicationComponent applicationComponent;

    private static WeatherApplication weatherApplicationInstance;

    public static WeatherApplication getInstance() {
        return weatherApplicationInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        System.out.println("attachBaseContext");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("onCreate start");

//        if(BuildConfig.DEBUG){
//            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
//            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
//        }

        //对application注入器进行初始化
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        weatherApplicationInstance = this;

        //初始化Apiclient
        ApiClient.init();
        System.out.println("oncreate end");
    }


    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }
}
