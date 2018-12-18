package guohuayu.com.iweather.feature.homePage;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import guohuayu.com.iweather.base.BaseRecyclerViewAdapter;

/**
 * Created by Administrator on 2018/12/17.
 * 未实现 未实现 未实现
 * 用于完成显示今天生活指数 如晨练指数 洗车指数等 的recyclerView
 */

public class LifeIndexAdapter extends BaseRecyclerViewAdapter<LifeIndexAdapter.ViewHolder>{
    @NonNull
    @Override
    public LifeIndexAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LifeIndexAdapter.ViewHolder holder, int position) {

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
