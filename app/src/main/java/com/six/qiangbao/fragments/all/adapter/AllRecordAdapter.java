package com.six.qiangbao.fragments.all.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.six.qiangbao.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 志浩 on 2016/6/20.
 */
public class AllRecordAdapter extends RecyclerView.Adapter<AllRecordAdapter.ViewHolder> {

    private Context context;
    private List<Map<String, String>> list = new ArrayList<>();
    private LayoutInflater inflater;
    public AllRecordAdapter(Context context){
        this.context=context;
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<Map<String,String>> data){
        list.addAll(data);
        notifyDataSetChanged();

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        TextView mText = new TextView(parent.getContext());
        mText.setId(R.id.all_record_jilu);
        holder = new ViewHolder(mText);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mText.setText(String.valueOf(list.get(position)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.all_record_jilu)
        TextView mText;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
