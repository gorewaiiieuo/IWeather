package guohuayu.com.iweather.feature.homePage;

import android.content.Context;
import android.content.Intent;
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

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

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

    private static final String STATICATION = "guohuayu.com.iweather.WeatherWidget.weatherWidget";

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
    @BindView(R.id.tv_level)
    TextView tv_level;
    AirQuality airQuality;
    @BindView(R.id.mHorizontalBarChart)
    BarChart mHorizontalBarChart;
    private float[] fAirQuality;

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
        fAirQuality = new float[6];

        //背景设置半透明
        cv_aq.getBackground().setAlpha(150);
        cv_forecast.getBackground().setAlpha(150);

        return rootView;
    }

    private void setHorizonBarChat() {
        //设置相关属性\
        mHorizontalBarChart.setTouchEnabled(false);
        mHorizontalBarChart.setDrawBarShadow(false);
        mHorizontalBarChart.setDrawValueAboveBar(true);
//        mHorizontalBarChart.getDescription().setEnabled(false);
        mHorizontalBarChart.setMaxVisibleValueCount(60);
        mHorizontalBarChart.setPinchZoom(true);
        mHorizontalBarChart.setDrawGridBackground(false);

        //x轴
        XAxis xl = mHorizontalBarChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        xl.setGranularity(10f);
        List<String> str = new ArrayList<>();
        str.add("CO浓度");
        str.add("SO2浓度");
        str.add("NO2浓度");
        str.add("O3浓度");
        str.add("Pm25浓度");
        str.add("Pm10浓度");
        xl.setValueFormatter(new MyXFormatter(str));

        //y轴
        YAxis yl = mHorizontalBarChart.getAxisLeft();
        yl.setDrawAxisLine(true);
        yl.setDrawGridLines(true);
        yl.setAxisMinimum(0f);

        //y轴
        YAxis yr = mHorizontalBarChart.getAxisRight();
        yr.setDrawAxisLine(true);
        yr.setDrawGridLines(false);
        yr.setAxisMinimum(0f);

        //设置数据
        setData(fAirQuality);
        mHorizontalBarChart.setFitBars(true);
        mHorizontalBarChart.animateY(2500);

        Legend l = mHorizontalBarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setXEntrySpace(4f);
    }

    private void setData(float[] aqi) {
        float barWidth = 1f;
        float spaceForBar = 10f;
        int count = aqi.length;
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        for (int i = 0; i < count; i++) {
            float val = aqi[i];
            yVals1.add(new BarEntry(i * spaceForBar, val));
        }
        BarDataSet set1;
        if (mHorizontalBarChart.getData() != null &&
                mHorizontalBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mHorizontalBarChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mHorizontalBarChart.getData().notifyDataChanged();
            mHorizontalBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yVals1, "          空气质量指数(mg/m3)");

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(barWidth);
            mHorizontalBarChart.setData(data);
        }
    }

    class MyXFormatter implements IAxisValueFormatter
    {
        private List<String> mValues;

        public MyXFormatter(List<String> values)
        {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            System.out.println((int)value+"******");
            if(((int)value >=0 && (int)(value/10) < mValues.size()))
                return mValues.get((int) (value/10));
            else
                return "";
        }

        @Override
        public int getDecimalDigits() {
            return mValues.size();
        }
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
        tv_level.setText(getLevel(airQuality.getAqi()));
        tv_advice.setText(airQuality.getPrimary().equals("无") ? "首要污染物：无" : "首要污染物： " + airQuality.getPrimary());

        fAirQuality = new float[]{Float.parseFloat(airQuality.getCo()), Float.parseFloat(airQuality.getSo2()),
                Float.parseFloat(airQuality.getNo2()), Float.parseFloat(airQuality.getO3())
                , airQuality.getPm25(), airQuality.getPm10()};
        setHorizonBarChat();


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

        //广播传递消息到桌面控件
        Intent intent = new Intent(STATICATION);
        Bundle bundle = new Bundle();
        bundle.putString("city",weather.getCityName());
        bundle.putString("temp",weatherForecasts.get(0).getTempMin()+"~"+weatherForecasts.get(0).getTempMax()+"℃");
        bundle.putString("weather",weather.getWeatherLive().getWeather());
        bundle.putString("wet",weather.getWeatherForecasts().get(0).getHumidity()+"%");
        bundle.putString("uv",weather.getWeatherForecasts().get(0).getUv());
        bundle.putString("wind",weather.getWeatherForecasts().get(0).getWind()+"级");
        bundle.putString("pm2.5",weather.getAirQuality().getPm25()+"μg/m³");
        intent.putExtras(bundle);
        this.getView().getContext().sendBroadcast(intent);
    }

    private String getLevel(int aqi) {
        if(aqi <=50){
            return "优";
        }else if(aqi <= 100){
            return "良好";
        }else if(aqi <= 150){
            return "轻度污染";
        }else if(aqi <= 200){
            return "重度污染";
        }else if(aqi <= 300){
            return "重度污染";
        }else {
            return "严重污染";
        }
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
