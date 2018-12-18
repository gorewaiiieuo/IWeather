package guohuayu.com.iweather.feature.selectCity;

import dagger.Component;
import guohuayu.com.iweather.di.component.ApplicationComponent;
import guohuayu.com.iweather.di.scope.ActivityScoped;

/**
 * Created by Administrator on 2018/12/17.
 */
@ActivityScoped
@Component(modules = SelectCityModule.class, dependencies = ApplicationComponent.class)
public interface SelectCityComponent {
    void inject(SelectCityActivity selectCityActivity);
}
