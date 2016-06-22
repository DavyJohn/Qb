package com.six.qiangbao.fragments.all.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saint.netlibrary.model.ShopDetailsQData;
import com.six.qiangbao.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 志浩 on 2016/6/22.
 */
public class QbHistoryAdapter extends RecyclerView.Adapter<QbHistoryAdapter.ViewHolder> {

    private List<ShopDetailsQData> listdata = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    public QbHistoryAdapter(Context context){
        this.context=context;
        inflater = LayoutInflater.from(context);
    }

    public void addList(List<ShopDetailsQData> list){
        listdata.clear();
        listdata.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        LinearLayout rootView = new LinearLayout(parent.getContext());
        rootView.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,60);
        rootView.setLayoutParams(params);
        TextView mText = new TextView(parent.getContext());
        mText.setTextSize(16);
        mText.setPadding(5,5,5,5);
        mText.setId(R.id.history_text);
        mText.setGravity(Gravity.CENTER|Gravity.LEFT);
        rootView.addView(mText);
        holder = new ViewHolder(rootView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
            if (listdata.get(position).url.equals("")){
                holder.mText.setTextColor(Color.parseColor("#F16079"));
            }
                holder.mText.setText(listdata.get(position).title);


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
        @Bind(R.id.history_text)
        TextView mText;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
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
