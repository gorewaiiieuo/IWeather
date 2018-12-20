package guohuayu.com.iweather.feature.homePage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import guohuayu.com.iweather.R;
import guohuayu.com.iweather.base.BaseFragment;
import guohuayu.com.iweather.data.WeatherDetail;
import guohuayu.com.iweather.data.db.entities.weatherEntities.AirQuality;
import guohuayu.com.iweather.data.db.entities.weatherEntities.LifeIndex;
import guohuayu.com.iweather.data.db.entities.weatherEntities.Weather;
import guohuayu.com.iweather.data.db.entities.weatherEntities.WeatherForecast;

/*
* 在此完成主界面设计
* 未完成
* */
public class HomePageFragment extends BaseFragment implements HomePageContract.View{

    private Unbinder unbinder;

    private Weather weather;

    private List<WeatherDetail> weatherDetails;
    private List<WeatherForecast> weatherForecasts;
    private List<LifeIndex> lifeIndices;

    private OnFragmentInteractionListener onFragmentInteractionListener;

    private HomePageContract.Presenter presenter;

    //天气详情
    @BindView(R.id.rv_detail)
    RecyclerView rv_detail;
    private DetailAdapter detailAdapter;

    private LifeIndexAdapter lifeIndexAdapter;

    public static HomePageFragment newInstance(){
        return new HomePageFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            onFragmentInteractionListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home_page2, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        //天气详情
        weatherDetails = new ArrayList<>();
        rv_detail.setNestedScrollingEnabled(false);
        rv_detail.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        detailAdapter = new DetailAdapter(weatherDetails);
        rv_detail.setAdapter(detailAdapter);
        detailAdapter.setOnItemClickListener((adapterView, view, i, l)->{});

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        assert presenter != null;
        presenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unSubscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setPresenter(HomePageContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayWeatherInformation(Weather weather) {
        this.weather = weather;

        onFragmentInteractionListener.updateMainBarTv(weather);

        //AirQuality airQuality = weather.getAirQuality();
        //设置空气质量相关textview

        weatherDetails.clear();
        weatherDetails.addAll(createWeatherDetail(weather));
        detailAdapter.notifyDataSetChanged();
//
//        lifeIndices.clear();
//        lifeIndices.addAll(weather.getLifeIndexes());
//        lifeIndexAdapter.notifyDataSetChanged();

        //onFragmentInteractionListener.addOrUpdateCityListInDrawerMenu(weather);
    }

    private List<WeatherDetail> createWeatherDetail(Weather weather) {
        List<WeatherDetail> details = new ArrayList<>();
        //风力 湿度 紫外线指数 降水概率
        WeatherDetail detail1 = new WeatherDetail(R.drawable.richu, "日出时间", weather.getWeatherForecasts().get(0).getSunrise());
        WeatherDetail detail2 = new WeatherDetail(R.drawable.riluo, "日落时间", weather.getWeatherForecasts().get(0).getSunset());
        WeatherDetail detail3 = new WeatherDetail(R.drawable.shidu, "相对湿度", weather.getWeatherForecasts().get(0).getHumidity()+"%");
        WeatherDetail detail4 = new WeatherDetail(R.drawable.ziwaixian, "紫外级别", weather.getWeatherForecasts().get(0).getUv());
        WeatherDetail detail5 = new WeatherDetail(R.drawable.jiangshuigailv, "降雨概率", weather.getWeatherForecasts().get(0).getPop()+"%");
        WeatherDetail detail6 = new WeatherDetail(R.drawable.richu, "风向风力", weather.getWeatherForecasts().get(0).getWind());

        details.add(detail1);
        details.add(detail2);
        details.add(detail3);
        details.add(detail4);
        details.add(detail5);
        details.add(detail6);

        return details;
    }

//    private List<WeatherDetail> createWeatherDetail(Weather weather) {
//        List<WeatherDetail> details = new ArrayList<>();
//
//        details.add(new WeatherDetail())
//    }



    public interface OnFragmentInteractionListener {
        /*
        * //更新main_bar中几个tv
        *
        * @param weather 天气数据
        * */
        void updateMainBarTv(Weather weather);

        /**
         * 更新完天气数据同时需要刷新侧边栏的已添加的城市列表
         *
         * @param weather 天气数据
         */
        void addOrUpdateCityListInDrawerMenu(Weather weather);
    }
}
