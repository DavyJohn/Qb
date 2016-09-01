package com.six.qiangbao.fragments.cart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saint.netlibrary.model.HongbaoData;
import com.six.qiangbao.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 志浩 on 2016/8/25.
 */
public class PaymentHbAdapter extends RecyclerView.Adapter<PaymentHbAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<HongbaoData> list = new ArrayList<HongbaoData>();
    public PaymentHbAdapter(Context context){
        this.context =context;
        inflater = LayoutInflater.from(context);
    }
    public void data(List<HongbaoData> data){
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.hb_item_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mLimit.setText("满"+list.get(position).getLimit()+"可使用红包");
        holder.mHbMoney.setText("￥"+list.get(position).getMoney());
        holder.mValid.setText("有效期"+list.get(position).getExpire_at());

        if (onItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClick(holder.itemView,position);
                }
            });
        }
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

    public interface OnItemClickListener{
        void OnItemClick(View view,int postion);
    }

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
