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
        void displaySavedCities(List<Weather> weatherList);//notify adapter
    }

    interface Presenter extends BasePresenter{
        void loadSavedCities();//加载全部已保存的城市的天气信息

        void deleteCity(String cityId);//删除城市 同时更新当前城市

        void saveCurrentCityToPreference(String cityId) throws InvalidClassException;//在此更新道歉城市到sharedPreference
    }
}
