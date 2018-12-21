package guohuayu.com.iweather.feature.homePage;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
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
    @BindView(R.id.cv_detail)
    CardView cv_detail;

    //未来七天预报
    @BindView(R.id.rv_forecast)
    RecyclerView rv_forecast;
    private ForecastAdapter forecastAdapter;
    @BindView(R.id.cv_forecast)
    CardView cv_forecast;

    //空气质量
    @BindView(R.id.cv_aq)
    CardView cv_aq;
    @BindView(R.id.tv_AQI)
    TextView tv_aqi;
    @BindView(R.id.tv_primary)
    TextView tv_advice;
    @BindView(R.id.tv_pm25)
    TextView tv_pm25;
    AirQuality airQuality;

    //生活指数
    @BindView(R.id.rv_index)
    RecyclerView rv_index;
    private LifeIndexAdapter lifeIndexAdapter;
    @BindView(R.id.cv_index)
    CardView cv_index;

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
        detailAdapter = new DetailAdapter(weatherDetails, getActivity());
        rv_detail.setAdapter(detailAdapter);
        detailAdapter.setOnItemClickListener((adapterView, view, i, l)->{});

        //未来七天预报
        weatherForecasts = new ArrayList<>();
        rv_forecast.setNestedScrollingEnabled(false);
        rv_forecast.setLayoutManager(new LinearLayoutManager(getActivity()));
        forecastAdapter = new ForecastAdapter(weatherForecasts);
        rv_forecast.setAdapter(forecastAdapter);
        forecastAdapter.setOnItemClickListener((adapterView, view, i, l)->{});

        //生活指数
        lifeIndices = new ArrayList<>();
        rv_index.setNestedScrollingEnabled(false);
        rv_index.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        lifeIndexAdapter = new LifeIndexAdapter(lifeIndices);
        rv_index.setAdapter(lifeIndexAdapter);
        lifeIndexAdapter.setOnItemClickListener((adapterView, view, i, l)->{
            Toast.makeText(getActivity(),lifeIndices.get(i).getDetails(), Toast.LENGTH_SHORT).show();
        });

        //空气质量 展开收起
        airQuality = new AirQuality();
        cv_aq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //施工到此
                String[] airQuality = new String[]{weather.getAirQuality().getCo(),weather.getAirQuality().getSo2(),weather.getAirQuality().getNo2(),
                        weather.getAirQuality().getO3(), weather.getAirQuality().getPm25()+"", weather.getAirQuality().getPm10()+""};
                Bundle bundle = new Bundle();
                bundle.putStringArray("airQuality",airQuality);
                System.out.println(weather.getAirQuality().getCo());
            }
        });

        //背景设置半透明
        cv_aq.getBackground().setAlpha(150);
        cv_forecast.getBackground().setAlpha(150);

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

        //空气质量
        AirQuality airQuality = weather.getAirQuality();
        tv_aqi.setText(airQuality.getAqi()+"");
        tv_pm25.setText(airQuality.getPm25()+"");
        tv_advice.setText(airQuality.getPrimary().equals("无") ? "首要污染物：无" : "首要污染物： " + airQuality.getPrimary());

        weatherDetails.clear();
        weatherDetails.addAll(createWeatherDetail(weather));
        detailAdapter.notifyDataSetChanged();

        weatherForecasts.clear();
        for(int i = 1; i < 7; i++){
            weatherForecasts.add(weather.getWeatherForecasts().get(i));
        }
        forecastAdapter.notifyDataSetChanged();

        lifeIndices.clear();
        lifeIndices.addAll(weather.getLifeIndexes());
        lifeIndexAdapter.notifyDataSetChanged();

        airQuality = weather.getAirQuality();

        onFragmentInteractionListener.addOrUpdateCityListInDrawerMenu(weather);
    }

    private List<WeatherDetail> createWeatherDetail(Weather weather) {
        List<WeatherDetail> details = new ArrayList<>();
        //风力 湿度 紫外线指数 降水概率
        WeatherDetail detail1 = new WeatherDetail(R.drawable.richu, "日出时间", weather.getWeatherForecasts().get(0).getSunrise());
        WeatherDetail detail2 = new WeatherDetail(R.drawable.riluo, "日落时间", weather.getWeatherForecasts().get(0).getSunset());
        WeatherDetail detail3 = new WeatherDetail(R.drawable.shidu, "相对湿度", weather.getWeatherForecasts().get(0).getHumidity()+"%");
        WeatherDetail detail4 = new WeatherDetail(R.drawable.ziwaixian, "紫外级别", weather.getWeatherForecasts().get(0).getUv());
        WeatherDetail detail5 = new WeatherDetail(R.drawable.jiangshuigailv, "降雨概率", weather.getWeatherForecasts().get(0).getPop()+"%");
        WeatherDetail detail6 = new WeatherDetail(R.drawable.richu, "风向风力", weather.getWeatherForecasts().get(0).getWind()+"级");

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
