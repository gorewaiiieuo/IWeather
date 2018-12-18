package guohuayu.com.iweather.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import guohuayu.com.iweather.WeatherApplication;
/**
 * Created by Administrator on 2018/12/12.
 */

@Module   //APP级别的module对象
public class ApplicationModule {
    private Context context;

    public ApplicationModule(Context context){
        this.context = context;
    }

    //对应ApplicationComponent
    @Provides
    @Singleton
    WeatherApplication provideApplication(){
        return (WeatherApplication) context.getApplicationContext();
    }

    @Provides
    @Singleton
    Context provideContext(){
        return context;
    }
}
