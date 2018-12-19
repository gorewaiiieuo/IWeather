package guohuayu.com.iweather.feature.homePage;

import dagger.Component;
import guohuayu.com.iweather.WelcomeActivity;
import guohuayu.com.iweather.di.component.ApplicationComponent;
import guohuayu.com.iweather.di.scope.ActivityScoped;

/**
 * Created by Administrator on 2018/12/17.
 */

@ActivityScoped
@Component(modules = HomePageModule.class, dependencies = ApplicationComponent.class)
public interface HomePageComponent {

    void inject(MainActivity  mainActivity);
}
