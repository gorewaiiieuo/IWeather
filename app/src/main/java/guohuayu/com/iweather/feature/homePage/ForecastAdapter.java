package guohuayu.com.iweather.feature.homePage;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import guohuayu.com.iweather.R;
import guohuayu.com.iweather.base.BaseRecyclerViewAdapter;

/**
 * Created by Administrator on 2018/12/17.
 * 未完成 未完成 未完成
 * 用于完成 显示未来七天天气信息 的recyclerView
 */

public class ForecastAdapter extends BaseRecyclerViewAdapter<ForecastAdapter.ViewHolder>{
    @NonNull
    @Override
    public ForecastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{


//          模板如下
//        @BindView(R.id.detail_icon_image_view)
//        ImageView detailIconImageView;
//        @BindView(R.id.detail_key_text_view)
//        TextView detailKeyTextView;
//        @BindView(R.id.detail_value_text_view)
//        TextView detailValueTextView;
//
//        ViewHolder(View itemView, DetailAdapter adapter) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//            itemView.setOnClickListener(v -> adapter.onItemHolderClick(DetailAdapter.ViewHolder.this));
//        }

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
