package com.six.qiangbao.fragments.all;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.saint.netlibrary.model.ShopListData;
import com.six.qiangbao.R;
import com.six.qiangbao.utils.ConstantUtil;
import com.squareup.picasso.Picasso;

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

    private List<ShopListData> listDatas = new ArrayList<>();
    private LayoutInflater inflater;
    public AllAdapter(Context context ) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addData( List<ShopListData> list){
        listDatas.clear();

        if (list.get(0).sum == String.valueOf(0)){

        }else {
            listDatas.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.all_shop_menu_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (listDatas.get(position).sum !=String.valueOf(0) ){
            Picasso.with(context).load(ConstantUtil.IMAGE_HEAD+listDatas.get(position).thumb).into(holder.mImage);
            holder.mTextUserName.setText(listDatas.get(position).title);
            holder.mTextPrice.setText("价值："+listDatas.get(position).money);
            holder.mProgressBar.setMax(Integer.parseInt(listDatas.get(position).zongrenshu));
            holder.mProgressBar.setProgress(Integer.parseInt(listDatas.get(position).canyurenshu));
            holder.mTextAll.setText(listDatas.get(position).zongrenshu);
            holder.mTextCanyu.setText(listDatas.get(position).canyurenshu);
            holder.mTextShenYu.setText(listDatas.get(position).shenyurenshu);
        }

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
        return listDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.all_shop_image)
        ImageView mImage;
        @Bind(R.id.shop_name)
        TextView mTextUserName;
        @Bind(R.id.shop_price)
        TextView mTextPrice;
        @Bind(R.id.all_progress)
        ProgressBar mProgressBar;
        @Bind(R.id.canyu_number)
        TextView mTextCanyu;
        @Bind(R.id.all_number)
        TextView mTextAll;
        @Bind(R.id.shenyu_number)
        TextView mTextShenYu;
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
