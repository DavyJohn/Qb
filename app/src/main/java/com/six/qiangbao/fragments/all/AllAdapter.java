package com.six.qiangbao.fragments.all;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.six.qiangbao.R;
import com.six.qiangbao.utils.ConstantUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 志浩 on 2016/6/17.
 */
public class AllAdapter extends RecyclerView.Adapter<AllAdapter.ViewHolder> {

    private Context context;
    private List<Map<String, String>> listShop = new ArrayList<>();
    private List<Map<String, String>> listPrice = new ArrayList<>();
    private LayoutInflater inflater;

    public AllAdapter(Context context, List listshop, List lisprice) {
        this.context = context;
        this.listShop = listshop;
        this.listPrice = lisprice;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.all_shop_menu_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTextName.setText(listShop.get(position).get("shop"));
        holder.mTextPrice.setText(listPrice.get(position).get("price"));

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClick(holder.itemView, position);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listPrice.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.shop_name)
        TextView mTextName;
        @Bind(R.id.shop_price)
        TextView mTextPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(View view, int postion);
    }

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
