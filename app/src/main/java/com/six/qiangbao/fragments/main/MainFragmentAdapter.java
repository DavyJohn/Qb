package com.six.qiangbao.fragments.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.saint.netlibrary.APIService;
import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.ShopListData;
import com.six.qiangbao.BaseFragment;
import com.six.qiangbao.R;
import com.six.qiangbao.utils.ConstantUtil;
import com.squareup.picasso.Picasso;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Created by 志浩 on 2016/8/15.
 */
public class MainFragmentAdapter extends RecyclerView.Adapter<MainFragmentAdapter.ViewHolder>{
    public Context context;
    private List<ShopListData> list = new ArrayList<ShopListData>();
    private LayoutInflater inflater;

    public MainFragmentAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void addDATA(List<ShopListData> listdata) {
        if (ConstantUtil.isClean == 1){
            list.clear();
        }else if (ConstantUtil.isClean == 0){
        }
            list.addAll(listdata);
            notifyDataSetChanged();
    }

    @Override
    public MainFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.main_fragment_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTextTitle.setText(list.get(position).title);
        String s;
        String numz = list.get(position).zongrenshu;
        String numc = list.get(position).canyurenshu;
        if (list.get(position).canyurenshu == String.valueOf(0)) {
            s = String.valueOf(0);
        }else {
            float num = (float) Integer.valueOf(numc) / (float) Integer.valueOf(numz) * 100;
            DecimalFormat df = new DecimalFormat("0");
            s = df.format(num);
        }
        holder.mTextNum.setText("参与人数" + s + "%");
        holder.bar.setMax(100);
        holder.bar.setProgress(Integer.parseInt(s));
        Picasso.with(context).load(ConstantUtil.IMAGE_HEAD + list.get(position).thumb).config(Bitmap.Config.RGB_565).into( holder.mImage);

        holder.mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"btn",Toast.LENGTH_SHORT).show();
            }
        });

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClick(holder.itemView, position);
                }
            });
        }

        if (onItemBtnClickListener != null){
            holder.mBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemBtnClickListener.OnItemBtnClick(holder.mBtn, position,holder.mImage);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }



    public interface OnItemClickListener {
        void OnItemClick(View view, int postion);
    }

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemBtnClickListener {
        void OnItemBtnClick(View view, int postion,ImageView image);
    }

    public OnItemBtnClickListener onItemBtnClickListener;

    public void setOnItemBtnClickListener(OnItemBtnClickListener onItemBtnClickListener) {
        this.onItemBtnClickListener = onItemBtnClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextTitle, mTextNum;
        ProgressBar bar;
        ImageView mImage;
        Button mBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextTitle = (TextView) itemView.findViewById(R.id.main_fragment_item_title);
            mTextNum = (TextView) itemView.findViewById(R.id.main_fragment_item_progress_num);
            mImage = (ImageView) itemView.findViewById(R.id.main_fragment_item_image);
            mBtn = (Button) itemView.findViewById(R.id.main_fragment_item_btn);
            bar = (ProgressBar) itemView.findViewById(R.id.main_fragment_progress);


        }
    }
}
