package guohuayu.com.iweather.di.component;

import javax.inject.Singleton;

import dagger.Component;
import guohuayu.com.iweather.di.module.ApplicationModule;
import guohuayu.com.iweather.feature.homePage.HomePagePresenter;
import guohuayu.com.iweather.feature.homePage.drawer.DrawerMenuPresenter;
import guohuayu.com.iweather.feature.selectCity.SelectCityPresenter;
//import guohuayu.com.iweather.feature.homePage.drawer.DrawerMenuPresenter;

/**
 * //格式要求 对应项目中三种presenter
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface PresenterComponent {
    void inject(HomePagePresenter presenter);

    void inject(SelectCityPresenter presenter);

    void inject(DrawerMenuPresenter presenter);
}
