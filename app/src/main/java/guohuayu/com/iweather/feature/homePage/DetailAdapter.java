package guohuayu.com.iweather.feature.homePage;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import guohuayu.com.iweather.base.BaseRecyclerViewAdapter;
import guohuayu.com.iweather.data.WeatherDetail;

/**
 * Created by Administrator on 2018/12/17.
 * 未实现 未实现 未实现
 * 用于完成显示今天的天气详情 如体感温度 湿度 紫外线指数 降水概率等 的recyclerView
 */

public class DetailAdapter extends BaseRecyclerViewAdapter<DetailAdapter.ViewHolder>{
    private List<WeatherDetail> details;

    public DetailAdapter( List<WeatherDetail> details){
        this.details = details;
    }

    @NonNull
    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
