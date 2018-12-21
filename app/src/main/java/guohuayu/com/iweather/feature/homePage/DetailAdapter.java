package guohuayu.com.iweather.feature.homePage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import guohuayu.com.iweather.R;
import guohuayu.com.iweather.base.BaseRecyclerViewAdapter;
import guohuayu.com.iweather.data.WeatherDetail;

/**
 * Created by Administrator on 2018/12/17.
 * 用于完成显示今天的天气详情 风力 湿度 紫外线指数 降水概率 日出 日落时间 的recyclerView
 */

public class DetailAdapter extends BaseRecyclerViewAdapter<DetailAdapter.ViewHolder>{
    private List<WeatherDetail> details;
    private Context context;

    public DetailAdapter(List<WeatherDetail> details, Context context){
        this.details = details;
        this.context = context;
    }

    @NonNull
    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weather_detail, parent, false);
        return new ViewHolder(rootView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.ViewHolder holder, int position) {
        WeatherDetail weatherDetail = details.get(position);
        holder.iv_detailIcon.setImageResource(weatherDetail.getIconResourceId());
        holder.tv_detailKey.setText(weatherDetail.getKey());
        holder.tv_detailVal.setText(weatherDetail.getValue());
    }

    @Override
    public int getItemCount() {
        return details == null ? 0 : details.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_detailIcon)
        ImageView iv_detailIcon;
        @BindView(R.id.tv_detailKey)
        TextView tv_detailKey;
        @BindView(R.id.tv_detailVal)
        TextView tv_detailVal;

        public ViewHolder(View itemView, DetailAdapter adapter) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> adapter.onItemHolderClick(ViewHolder.this));
        }
    }
}
