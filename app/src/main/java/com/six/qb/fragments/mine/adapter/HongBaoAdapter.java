package com.six.qb.fragments.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saint.netlibrary.model.HongbaoData;
import com.six.qb.R;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 志浩 on 2016/8/24.
 */
public class HongBaoAdapter extends RecyclerView.Adapter<HongBaoAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    public HongBaoAdapter (Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    private List<HongbaoData> list = new ArrayList<HongbaoData>();
    public void data(List<HongbaoData> data){
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public HongBaoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.hb_item_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HongBaoAdapter.ViewHolder holder, int position) {
        holder.mLimit.setText("满"+list.get(position).getLimit()+"可使用红包");
        holder.mHbMoney.setText("￥"+list.get(position).getMoney());
        holder.mValid.setText("有效期"+list.get(position).getExpire_at());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.limit)
        TextView mLimit;
        @BindView(R.id.valid)
        TextView mValid;
        @BindView(R.id.hb_money)
        TextView mHbMoney;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
