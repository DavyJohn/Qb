package com.six.qb.fragments.newst;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.saint.netlibrary.model.ListItemsData;
import com.six.qb.R;
import com.six.qb.utils.ConstantUtil;
import com.six.qb.utils.DensityUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 志浩 on 2016/6/20.
 */
public class NewstAdapter extends RecyclerView.Adapter<NewstAdapter.ViewHolder> {

    private List<ListItemsData> listdata = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public NewstAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<ListItemsData> list) {
        listdata.clear();
        listdata.addAll(list);
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.latest_recycler_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.mTextUser.setText(listdata.get(position).q_user);
        holder.mTextPrice.setText("价值："+listdata.get(position).money);
        holder.mTextTime.setText("揭晓时间："+listdata.get(position).q_end_time);
        Picasso.with(context).load(ConstantUtil.IMAGE_HEAD+listdata.get(position).userphoto).config(Bitmap.Config.RGB_565).resize(DensityUtil.dip2px(context,250),DensityUtil.dip2px(context,250)).centerCrop().into(holder.mImageUser);
        Picasso.with(context).load(ConstantUtil.IMAGE_HEAD+listdata.get(position).thumb).config(Bitmap.Config.RGB_565).resize(DensityUtil.dip2px(context,250),DensityUtil.dip2px(context,250)).centerCrop().into(holder.imageShop);
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
        return listdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.latest_image)
        ImageView imageShop;
        @BindView(R.id.username)
        TextView mTextUser;
        @BindView(R.id.latest_shop_price)
        TextView mTextPrice;
        @BindView(R.id.latest_shop_time)
        TextView mTextTime;
        @BindView(R.id.latest_user)
        ImageView mImageUser;

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
