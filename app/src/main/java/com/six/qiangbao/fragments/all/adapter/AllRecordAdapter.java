package com.six.qiangbao.fragments.all.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.saint.netlibrary.model.GoodsQbJuData;
import com.six.qiangbao.R;
import com.six.qiangbao.utils.ConstantUtil;
import com.six.qiangbao.utils.DateUtil;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 志浩 on 2016/6/20.
 */
public class AllRecordAdapter extends RecyclerView.Adapter<AllRecordAdapter.ViewHolder> {

    private Context context;
    private List<GoodsQbJuData> list = new ArrayList<>();
    private LayoutInflater inflater;
    public AllRecordAdapter(Context context){
        this.context=context;
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<GoodsQbJuData> data){
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        View view = inflater.inflate(R.layout.all_record_item_layout,parent,false);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(ConstantUtil.IMAGE_HEAD+list.get(position).getUphoto()).into(holder.image);
        holder.gonumber.setText("抢宝了"+list.get(position).getGonumber()+"人次");
        holder.mAdderss.setText(list.get(position).getIp());
        Double time = Double.parseDouble(list.get(position).getTime());
        String result = DateUtil.formatData("yyyy-MM-dd HH:mm:ss",time);
        holder.time.setText(result);
        holder.uid.setText(list.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.uid_image)
        ImageView image;
        @BindView(R.id.uid_address)
        TextView mAdderss;
        @BindView(R.id.uid_gonumber)
        TextView gonumber;
        @BindView(R.id.uid_time)
        TextView time;
        @BindView(R.id.uid)
        TextView uid;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
