package guohuayu.com.iweather.feature.homePage.drawer;

import dagger.Module;
import dagger.Provides;
import guohuayu.com.iweather.di.scope.ActivityScoped;

/**
 * Created by Administrator on 2018/12/17.
 */
@Module
public class DrawerMenuModule {
    private DrawerContract.View view;

    public DrawerMenuModule(DrawerContract.View view) {
        this.view = view;
    }

    @Provides
    @ActivityScoped
    DrawerContract.View provideCityManagerContactView() {
        return view;
    }
}
