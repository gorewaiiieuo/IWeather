package guohuayu.com.iweather.feature.homePage.drawer;

import java.io.InvalidClassException;
import java.util.List;

import guohuayu.com.iweather.base.BasePresenter;
import guohuayu.com.iweather.base.BaseView;
import guohuayu.com.iweather.data.db.entities.weatherEntities.Weather;

/**
 * Created by Administrator on 2018/12/17.
 * 侧滑菜单的契约类
 */

public interface DrawerContract {
    interface View extends BaseView<DrawerMenuPresenter>{
        void displaySavedCities(List<Weather> weatherList);
    }

    interface Presenter extends BasePresenter{
        void loadSavedCities();

        void deleteCity(String cityId);

        void saveCurrentCityToPreference(String cityId) throws InvalidClassException;
    }
}
