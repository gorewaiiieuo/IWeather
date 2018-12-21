package guohuayu.com.iweather.feature.homePage;

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
import guohuayu.com.iweather.data.db.entities.weatherEntities.LifeIndex;

/**
 * Created by Administrator on 2018/12/17.
 * 未实现 未实现 未实现
 * 用于完成显示今天生活指数 如晨练指数 洗车指数等 的recyclerView
 */

public class LifeIndexAdapter extends BaseRecyclerViewAdapter<LifeIndexAdapter.ViewHolder>{

    private List<LifeIndex> lifeIndices;

    public LifeIndexAdapter(List<LifeIndex> lifeIndices) {
        this.lifeIndices = lifeIndices;
    }

    @NonNull
    @Override
    public LifeIndexAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lifeindex, parent, false);
        return new ViewHolder(rootView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull LifeIndexAdapter.ViewHolder holder, int position) {
        LifeIndex lifeIndex = lifeIndices.get(position);

        holder.tv_detailKey.setText(lifeIndex.getName());
        holder.tv_detailVal.setText(lifeIndex.getIndex());

        if(lifeIndex.getName().equals("舒适度")){
            holder.iv_detailIcon.setImageResource(R.drawable.shushi);
        }else if(lifeIndex.getName().equals("洗车")){
            holder.iv_detailIcon.setImageResource(R.drawable.xiche);
        }else if(lifeIndex.getName().equals("旅游")){
            holder.iv_detailIcon.setImageResource(R.drawable.lvyou);
        }else if(lifeIndex.getName().equals("运动")){
            holder.iv_detailIcon.setImageResource(R.drawable.yundong);
        }else if(lifeIndex.getName().equals("感冒")){
            holder.iv_detailIcon.setImageResource(R.drawable.ganmao);
        }else if(lifeIndex.getName().equals("穿衣")){
            holder.iv_detailIcon.setImageResource(R.drawable.chuanyi);
        }
    }

    @Override
    public int getItemCount() {
        return lifeIndices == null ? 0 : lifeIndices.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_indexIcon)
        ImageView iv_detailIcon;
        @BindView(R.id.tv_indexKey)
        TextView tv_detailKey;
        @BindView(R.id.tv_indexVal)
        TextView tv_detailVal;

        public ViewHolder(View itemView, LifeIndexAdapter adapter) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> adapter.onItemHolderClick(LifeIndexAdapter.ViewHolder.this));
        }
    }
}
