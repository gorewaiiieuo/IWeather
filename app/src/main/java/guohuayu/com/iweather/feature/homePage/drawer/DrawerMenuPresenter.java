package guohuayu.com.iweather.feature.homePage.drawer;

import android.content.Context;

import java.io.InvalidClassException;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import guohuayu.com.iweather.data.db.dao.WeatherDao;
import guohuayu.com.iweather.data.db.entities.weatherEntities.Weather;
import guohuayu.com.iweather.data.preference.PreferenceHelper;
import guohuayu.com.iweather.data.preference.WeatherSettings;
import guohuayu.com.iweather.di.component.DaggerPresenterComponent;
import guohuayu.com.iweather.di.module.ApplicationModule;
import guohuayu.com.iweather.di.scope.ActivityScoped;
import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2018/12/17.
 */
@ActivityScoped
public class DrawerMenuPresenter implements DrawerContract.Presenter{
    private DrawerContract.View mView;

    private CompositeSubscription subscriptions;

    @Inject
    WeatherDao weatherDao;

    @Inject
    public DrawerMenuPresenter(Context context, DrawerContract.View mView) {
        this.mView = mView;
        this.subscriptions = subscriptions;
        mView.setPresenter(this);

        DaggerPresenterComponent.builder()
                .applicationModule(new ApplicationModule(context))
                .build()
                .inject(this);
    }

    @Override
    public void subscribe() {
        loadSavedCities();
    }

    @Override
    public void unSubscribe() {
        subscriptions.clear();
    }

    @Override
    public void loadSavedCities() {
        try {
            Subscription subscription = Observable.just(weatherDao.queryAllSaveCity())//just表示返回只包含一个item的observable
                    .subscribeOn(Schedulers.io())//指定被观察者运行线程
                    .observeOn(AndroidSchedulers.mainThread())//指定观察者运行线程
                    .subscribe(weathers -> mView.displaySavedCities(weathers));
            subscriptions.add(subscription);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCity(String cityId) {
        Observable.just(deleteCityFromDBAndReturnCurrentCityId(cityId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(currentCityId ->{//当被观察者中删除了一个城市时，通知观察者执行->更新sharedPreference中当前城市操作
                    if(currentCityId == null){
                        return;
                    }
                    try {
                        PreferenceHelper.savePreference(WeatherSettings.SETTINGS_CURRENT_CITY_ID, currentCityId);
                    } catch (InvalidClassException e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void saveCurrentCityToPreference(String cityId) throws InvalidClassException {
        PreferenceHelper.savePreference(WeatherSettings.SETTINGS_CURRENT_CITY_ID, cityId);
    }

    private String deleteCityFromDBAndReturnCurrentCityId(String cityId){
        String currentCityId = PreferenceHelper.getSharedPreferences().getString(WeatherSettings.SETTINGS_CURRENT_CITY_ID.getmId(),"");

        try {
            weatherDao.delteById(cityId);
            if(cityId.equals(currentCityId)){//若删除的是当前城市需重新设置当前城市
                List<Weather> weatherList = weatherDao.queryAllSaveCity();
                if(weatherList != null && weatherList.size() > 0){
                    currentCityId = weatherList.get(0).getCityId();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentCityId;
    }
}
