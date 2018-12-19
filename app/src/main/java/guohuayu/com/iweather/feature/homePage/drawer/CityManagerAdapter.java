package guohuayu.com.iweather.feature.homePage.drawer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.baronzhang.android.library.util.DateConvertUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import guohuayu.com.iweather.R;
import guohuayu.com.iweather.base.BaseRecyclerViewAdapter;
import guohuayu.com.iweather.data.db.entities.weatherEntities.Weather;

/**
 * Created by Administrator on 2018/12/17.
 * 用于实现侧滑菜单中 已添加城市的显示和管理 的recyclerView
 */

public class CityManagerAdapter extends BaseRecyclerViewAdapter<CityManagerAdapter.ViewHolder>{

    private final List<Weather> weatherList;

    public CityManagerAdapter(List<Weather> weatherList){
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public CityManagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_city_manager, parent, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CityManagerAdapter.ViewHolder holder, int position) {
        Weather weather = weatherList.get(position);
        holder.city.setText(weather.getCityName());
        holder.temp.setText(new StringBuilder().append(weather.getWeatherForecasts().get(0).getTempMin()).append("~").append(weather.getWeatherForecasts().get(0).getTempMax()).append("℃").toString());
        holder.time.setText("发布于 " + DateConvertUtils.timeStampToDate(weather.getWeatherLive().getUpdatetime(), DateConvertUtils.DATA_FORMAT_PATTEN_YYYY_MM_DD_HH_MM));

        holder.delete.setOnClickListener(v ->{
            Weather removeWeather = weatherList.get(holder.getAdapterPosition());
            weatherList.remove(removeWeather);
            notifyItemRemoved(holder.getAdapterPosition());

            if (onItemClickListener != null && onItemClickListener instanceof OnCityManagerItemClickListener) {
                ((OnCityManagerItemClickListener)onItemClickListener).onDeleteClick(removeWeather.getCityId());
            }
        });

        int imgRes = 0;
        if(weather.getWeatherLive().getWeather().contains("暴雨")){
            imgRes = R.drawable.dabaoyu;
        }else if(weather.getWeatherLive().getWeather().contains("霾")){
            imgRes = R.drawable.mai;
        }else if(weather.getWeatherLive().getWeather().contains("雾")){
            imgRes = R.drawable.wu;
        }else if(weather.getWeatherLive().getWeather().contains("小雨")){
            imgRes = R.drawable.xiaoyu;
        }else if(weather.getWeatherLive().getWeather().contains("多云")){
            imgRes = R.drawable.duoyun;
        }else{
            imgRes = R.drawable.qing;
        }
        holder.weather.setImageResource(imgRes);
    }

    @Override
    public int getItemCount() {
        return weatherList == null ? 0 : weatherList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.item_tv_city)
        TextView city;
        @BindView(R.id.item_tv_temp)
        TextView temp;
        @BindView(R.id.item_tv_time)
        TextView time;
        @BindView(R.id.item_iv_weather)
        ImageView weather;
        @BindView(R.id.item_iv_delete)
        ImageView delete;

        public ViewHolder(View itemView, CityManagerAdapter adapter) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> adapter.onItemHolderClick(ViewHolder.this));
        }
    }

    //为cityManagerAdapter添加一个点击事件监听器的回调方法
    public interface OnCityManagerItemClickListener extends AdapterView.OnItemClickListener {
        void onDeleteClick(String cityId);
    }
}
