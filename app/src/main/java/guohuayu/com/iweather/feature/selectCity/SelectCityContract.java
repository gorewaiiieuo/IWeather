package guohuayu.com.iweather.feature.selectCity;

import java.util.List;

import guohuayu.com.iweather.base.BasePresenter;
import guohuayu.com.iweather.base.BaseView;
import guohuayu.com.iweather.data.db.entities.City;

/**
 * Created by Administrator on 2018/12/17.
 */

public interface SelectCityContract {
    interface View extends BaseView<Presenter> {

        void displayCities(List<City> cities);
    }

    interface Presenter extends BasePresenter {

        void loadCities();
    }
}
