package guohuayu.com.iweather.feature.homePage;

import android.content.Context;
import android.widget.Toast;

import com.guohuayu.android.library.util.RxSchedulerUtils;

import javax.inject.Inject;

import guohuayu.com.iweather.data.db.dao.WeatherDao;
import guohuayu.com.iweather.data.preference.PreferenceHelper;
import guohuayu.com.iweather.data.preference.WeatherSettings;
import guohuayu.com.iweather.data.repository.WeatherDataRepository;
import guohuayu.com.iweather.di.component.DaggerPresenterComponent;
import guohuayu.com.iweather.di.module.ApplicationModule;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2018/12/17.
 * 在此具体实现HomePageContract.Presenter接口中的subscribe（），unSubscribe（），loadWeather（）三个方法
 * 以完成APP所需的业务逻辑——从本地数据库或API获取天气信息
 */

//presenter的实现中又包含了一个IView并且依赖了Model
//Activity里只保留对IPresenter的调用，其它工作全部留到PresenterCompl中实现

/*
* 在这里presenter实现中保留了View的引用，因此在这里就可以直接进行UI操作了，
* 而不用在Activity里完成。这里使用了View引用，而不是直接使用Activity，
* 这样一来，如果在别的Activity里也需要用到相同的业务逻辑，
* 就可以直接复用这个presenter实现类了（一个Activity可以包含一个以上的Presenter，总之，需要什么业务就new什么样的Presenter，是不是很灵活（＠￣︶￣＠））
* */
public class HomePagePresenter implements HomePageContract.Presenter{

    private final Context context;
    private final HomePageContract.View weatherView; //通过持有view来允许UI操作

    private CompositeSubscription subscriptions;

    @Inject
    WeatherDao weatherDao;

    @Inject
    HomePagePresenter(Context context, HomePageContract.View weatherView){
        this.context = context;
        this.weatherView = weatherView;
        this.subscriptions = new CompositeSubscription();
        weatherView.setPresenter(this);

        //在此注入本presenter注解
        DaggerPresenterComponent.builder()
                .applicationModule(new ApplicationModule(context))
                .build().inject(this);
    }

    @Override
    public void subscribe() {
        String cityId = PreferenceHelper.getSharedPreferences().getString(WeatherSettings.SETTINGS_CURRENT_CITY_ID.getmId(),"");
        loadWeather(cityId, false);
    }

    @Override
    public void unSubscribe() {

    }

    /*
     *
     * 核心 当从数据仓库接收的回来的消息完成本地存储后，
     * 若发现weather发生改变，weather作为被观察会通知观察者->执行displayWeatherInformation
     *
     * */
    @Override
    public void loadWeather(String cityId, boolean refreshNow) {
        Subscription subscription = WeatherDataRepository.getWeather(context, cityId, weatherDao, refreshNow)
                .compose(RxSchedulerUtils.normalSchedulersTransformer())
                .subscribe(weatherView::displayWeatherInformation,throwable -> {
                    Toast.makeText(context,throwable.getMessage(),Toast.LENGTH_SHORT).show();
                });
        subscriptions.add(subscription);
    }
}
