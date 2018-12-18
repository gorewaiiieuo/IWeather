package guohuayu.com.iweather.feature.homePage;

import guohuayu.com.iweather.base.BasePresenter;
import guohuayu.com.iweather.base.BaseView;
import guohuayu.com.iweather.data.db.entities.weatherEntities.Weather;

/**
 * Created by Administrator on 2018/12/17.
 * 主界面的契约接口 包含View 和 Presenter
 * 在此将界面逻辑和业务逻辑分离 实现解耦
 */

public interface HomePageContract {
    interface View extends BaseView<Presenter>{
        void displayWeatherInformation(Weather weather);//天气信息展示的界面逻辑
    }

    interface Presenter extends BasePresenter{
        void loadWeather(String cityId, boolean refreshNow);//天气信息加载的业务逻辑
    }
}
