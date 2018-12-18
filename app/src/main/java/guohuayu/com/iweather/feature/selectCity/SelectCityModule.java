package guohuayu.com.iweather.feature.selectCity;

import dagger.Module;
import dagger.Provides;
import guohuayu.com.iweather.di.scope.ActivityScoped;

/**
 * Created by Administrator on 2018/12/17.
 */
@Module
public class SelectCityModule {
    private SelectCityContract.View view;

    public SelectCityModule(SelectCityContract.View view) {
        this.view = view;
    }

    @Provides
    @ActivityScoped
    SelectCityContract.View provideSelectCityContractView() {
        return view;
    }
}
