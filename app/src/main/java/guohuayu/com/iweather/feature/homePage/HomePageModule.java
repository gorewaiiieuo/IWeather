package guohuayu.com.iweather.feature.homePage;

import dagger.Module;
import dagger.Provides;
import guohuayu.com.iweather.di.scope.ActivityScoped;

/**
 * Created by Administrator on 2018/12/17.
 * 在此提供HomePage的module
 */

@Module
public class HomePageModule {
    private HomePageContract.View mView;

    public HomePageModule(HomePageContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @ActivityScoped
    HomePageContract.View provideHomePageContractView(){
        return mView;
    }
}
