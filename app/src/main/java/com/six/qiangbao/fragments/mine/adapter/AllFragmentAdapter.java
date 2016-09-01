package com.six.qiangbao.fragments.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.saint.netlibrary.model.ListItems;
import com.six.qiangbao.R;
import com.six.qiangbao.utils.ConstantUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 志浩 on 2016/8/23.
 */
public class AllFragmentAdapter extends RecyclerView.Adapter<AllFragmentAdapter.ViewHolder> {

    private List<ListItems> listItemses = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    public AllFragmentAdapter (Context context){
        this.context= context;
        inflater = LayoutInflater.from(context);
    }
    public void data(List<ListItems> list){
        listItemses.clear();
        listItemses.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = inflater.inflate(R.layout.all_shop_menu_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(ConstantUtil.IMAGE_HEAD+listItemses.get(position).getThumb()).into(holder.mImage);
        holder.mTextUserName.setText(listItemses.get(position).getShopname());
        holder.mTextPrice.setText(listItemses.get(position).getMoney());
        holder.mProgressBar.setMax(Integer.parseInt(listItemses.get(position).getZongrenshu()));
        holder.mProgressBar.setProgress(Integer.parseInt(listItemses.get(position).getCanyurenshu()));
        holder.mTextCanyu.setText(listItemses.get(position).getCanyurenshu());
        holder.mTextAll.setText(listItemses.get(position).getZongrenshu());
        holder.mTextShenYu.setText(listItemses.get(position).getShenyurenshu());
    }

    @Override
    public int getItemCount() {
        return listItemses.size() == 0?0:listItemses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.all_shop_image)
        ImageView mImage;
        @BindView(R.id.shop_name)
        TextView mTextUserName;
        @BindView(R.id.shop_price)
        TextView mTextPrice;
        @BindView(R.id.all_progress)
        ProgressBar mProgressBar;
        @BindView(R.id.canyu_number)
        TextView mTextCanyu;
        @BindView(R.id.all_number)
        TextView mTextAll;
        @BindView(R.id.shenyu_number)
        TextView mTextShenYu;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
