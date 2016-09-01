package com.six.qiangbao.fragments.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saint.netlibrary.model.ItmesData;
import com.six.qiangbao.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 志浩 on 2016/8/23.
 */
public class ZuRightAdapter extends RecyclerView.Adapter<ZuRightAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<ItmesData> data = new ArrayList<>();
    public ZuRightAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void data(List<ItmesData> list){
        data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.zuright_item_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextMoney.setText(data.get(position).getMoney());
        holder.mTexttime.setText(data.get(position).getTime());
        holder.mTextType.setText(data.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.xiaofei_money)
        TextView mTextMoney;
        @BindView(R.id.xiaofei_time)
        TextView mTexttime;
        @BindView(R.id.xiaofei_type)
        TextView mTextType;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
