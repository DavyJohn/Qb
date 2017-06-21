package com.six.qb.fragments.cart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.six.qb.R;
import com.six.qb.utils.ConstantUtil;
import com.six.qb.utils.ShopCartData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 志浩 on 2016/8/20.
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<ShopCartData> dataList = new ArrayList<>();
    public CartAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    public void sqlData(List<ShopCartData> list){
        dataList.clear();
        dataList.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cart_fragment_item_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Picasso.with(context).load(ConstantUtil.IMAGE_HEAD+dataList.get(position).getImage()).into(holder.image);
        holder.mTextAcount.setText(dataList.get(position).getGonum());
        holder.mTvName.setText(dataList.get(position).getName());

        if (onAddClickListener !=null){
            holder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onAddClickListener.OnAddClick(holder.add,position,holder.mTextAcount);
                }
            });
        }

        if (onRemoveClickListener != null){
            holder.remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRemoveClickListener.OnRemoveClick(holder.remove,position,holder.mTextAcount);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_pic)
        ImageView image;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.iv_add)
        ImageView add;
        @BindView(R.id.tv_acount)
        TextView mTextAcount;
        @BindView(R.id.iv_remove)
        ImageView remove;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnAddClickListener{
        void OnAddClick(View view,int postion,TextView text);
    }

    public OnAddClickListener onAddClickListener;

    public void setOnAddClickListener(OnAddClickListener onAddClickListener){
        this.onAddClickListener = onAddClickListener;
    }

    public interface OnRemoveClickListener{
        void OnRemoveClick(View view,int postion,TextView text);
    }

    public OnRemoveClickListener onRemoveClickListener;

    public void setOnRemoveClickListener(OnRemoveClickListener onRemoveClickListener){
        this.onRemoveClickListener = onRemoveClickListener;
    }
}
