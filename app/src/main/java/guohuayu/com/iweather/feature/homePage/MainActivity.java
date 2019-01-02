package guohuayu.com.iweather.feature.homePage;

import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.guohuayu.android.library.util.ActivityUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import guohuayu.com.iweather.R;
import guohuayu.com.iweather.WeatherApplication;
import guohuayu.com.iweather.data.db.entities.weatherEntities.Weather;
import guohuayu.com.iweather.feature.homePage.drawer.DrawerMenuFragment;
import guohuayu.com.iweather.feature.homePage.drawer.DrawerMenuPresenter;

/*
* 首页 头部
* 未完成
* */
public class MainActivity extends AppCompatActivity
implements HomePageFragment.OnFragmentInteractionListener, DrawerMenuFragment.OnSelectCity{

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.tv_temp)
    TextView tv_temp;
    @BindView(R.id.tv_weather)
    TextView tv_weather;
    @BindView(R.id.tv_tempMin)
    TextView tv_tempMin;
    @BindView(R.id.tv_tempMax)
    TextView tv_tempMax;

    @Inject
    HomePagePresenter homePagePresenter;
    DrawerMenuPresenter drawerMenuPresenter;

    private String currentCityId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        //设置smartRefreshLayout头部
        ClassicsHeader header = new ClassicsHeader(this);
        header.setPrimaryColors(this.getResources().getColor(R.color.colorPrimary), Color.WHITE);
        this.smartRefreshLayout.setRefreshHeader(header);
        this.smartRefreshLayout.setOnRefreshListener(refreshLayout -> homePagePresenter.loadWeather(currentCityId, true));

        //在main_bar中监视drawerLayout的变化
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar,R.string.drawer_open, R.string.drawer_close);

        assert drawerLayout != null;
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        HomePageFragment homePageFragment = (HomePageFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if(homePageFragment == null){
            homePageFragment = HomePageFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), homePageFragment,
                    R.id.fragment_container);
        }

        DaggerHomePageComponent.builder()
                .applicationComponent(WeatherApplication.getInstance().getApplicationComponent())
                .homePageModule(new HomePageModule(homePageFragment))
                .build().inject(MainActivity.this);

        /*
        * 注入drawerMenuFragment
        * */
        DrawerMenuFragment drawerMenuFragment = (DrawerMenuFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container_drawer_menu);
        if(drawerMenuFragment == null){
            drawerMenuFragment = DrawerMenuFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), drawerMenuFragment, R.id.fragment_container_drawer_menu);
        }

        drawerMenuPresenter = new DrawerMenuPresenter(this, drawerMenuFragment);
    }

    //在此更新mainBar中的几个tv
    @Override
    public void updateMainBarTv(Weather weather) {
        currentCityId = weather.getCityId();
        smartRefreshLayout.finishRefresh();
        toolbar.setTitle(weather.getCityName());
        collapsingToolbarLayout.setTitle(weather.getCityName());

        tv_temp.setText(weather.getWeatherLive().getTemperature()+"℃");
        tv_weather.setText(weather.getWeatherLive().getWeather());
        tv_tempMin.setText(weather.getWeatherForecasts().get(0).getTempMin()+"℃");
        tv_tempMax.setText(weather.getWeatherForecasts().get(0).getTempMax()+"℃");

    }

    @Override
    public void addOrUpdateCityListInDrawerMenu(Weather weather) {
        drawerMenuPresenter.loadSavedCities();
    }

    @Override
    public void onSelect(String cityId) {
        assert drawerLayout != null;
        drawerLayout.closeDrawer(GravityCompat.START);

        new Handler().postDelayed(()->homePagePresenter.loadWeather(cityId, false),
                200);
    }
}
