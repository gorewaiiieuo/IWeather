package guohuayu.com.iweather.feature.homePage;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import guohuayu.com.iweather.R;
import guohuayu.com.iweather.base.BaseRecyclerViewAdapter;
import guohuayu.com.iweather.data.db.entities.weatherEntities.WeatherForecast;

/**
 * Created by Administrator on 2018/12/17.
 * 未完成 未完成 未完成
 * 用于完成 显示未来七天天气信息(星期几 温度范围 天气) 的recyclerView
 */

public class ForecastAdapter extends BaseRecyclerViewAdapter<ForecastAdapter.ViewHolder>{
    private List<WeatherForecast> weatherForecasts;

    public ForecastAdapter(List<WeatherForecast> weatherForecasts){
        this.weatherForecasts = weatherForecasts;
    }

    @NonNull
    @Override
    public ForecastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weather_forecast, parent, false);
        return new ViewHolder(rootView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastAdapter.ViewHolder holder, int position) {
        WeatherForecast forecast = weatherForecasts.get(position);

        holder.tv_forecastWeek.setText(forecast.getWeek());
        holder.tv_forecastTemp.setText(forecast.getTempMin()+ "℃~" + forecast.getTempMax()+"℃");

        String weather = forecast.getWeatherDay();
        int imgRes = 0;
        if(weather.contains("暴雨")){
            imgRes = R.drawable.dabaoyu;
        }else if(weather.contains("霾")){
            imgRes = R.drawable.mai;
        }else if(weather.contains("雾")){
            imgRes = R.drawable.wu;
        }else if(weather.contains("小雨")){
            imgRes = R.drawable.xiaoyu;
        }else if(weather.contains("多云")){
            imgRes = R.drawable.duoyun;
        }else{
            imgRes = R.drawable.qing;
        }

        holder.iv_forecastWeather.setImageResource(imgRes);
    }

    @Override
    public int getItemCount() {
        return weatherForecasts == null ? 0 : weatherForecasts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_forecastWeather)
        ImageView iv_forecastWeather;
        @BindView(R.id.tv_forecastTemp)
        TextView tv_forecastTemp;
        @BindView(R.id.tv_forecastWeek)
        TextView tv_forecastWeek;

        public ViewHolder(View itemView, ForecastAdapter adapter) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> adapter.onItemHolderClick(ForecastAdapter.ViewHolder.this));
        }
    }
}
