package com.six.qiangbao.fragments.newst;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.saint.netlibrary.model.ListItemsData;
import com.six.qiangbao.R;
import com.six.qiangbao.utils.ConstantUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextUser.setText(listdata.get(position).q_user);
        holder.mTextPrice.setText("价值："+listdata.get(position).money);
        holder.mTextTime.setText("揭晓时间："+listdata.get(position).q_end_time);
        Picasso.with(context).load(ConstantUtil.IMAGE_HEAD+listdata.get(position).thumb).into(holder.imageShop);
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.latest_image)
        ImageView imageShop;
        @Bind(R.id.username)
        TextView mTextUser;
        @Bind(R.id.latest_shop_price)
        TextView mTextPrice;
        @Bind(R.id.latest_shop_time)
        TextView mTextTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
