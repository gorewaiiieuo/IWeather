package guohuayu.com.iweather.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.AdapterView;

/**
 * 为recyclerView.adpater提供一个统一的父类，为其添加一个item点击监听器\
 */

public abstract class BaseRecyclerViewAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T>{
    protected AdapterView.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    //???
    protected void onItemHolderClick(RecyclerView.ViewHolder itemHolder){
        if(onItemClickListener != null){
            onItemClickListener.onItemClick(null, itemHolder.itemView,
                    itemHolder.getAdapterPosition(), itemHolder.getItemId());
        }else{
            throw new IllegalStateException("Please call setOnItemClickListener method set the click event listeners");
        }
    }
}
