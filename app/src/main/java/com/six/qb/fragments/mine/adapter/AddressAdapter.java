package com.six.qb.fragments.mine.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.saint.netlibrary.model.AddressDataItem;
import com.saint.netlibrary.utils.ConstantUtil;
import com.six.qb.R;
import com.six.qb.utils.ItemTouchHelperAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 志浩 on 2016/8/23.
 */
public class AddressAdapter  extends RecyclerView.Adapter<AddressAdapter.ViewHolder> implements ItemTouchHelperAdapter{

    private Context context;
    private List<AddressDataItem> list = new ArrayList<AddressDataItem>();
    private LayoutInflater inflater;
    public AddressAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<AddressDataItem> data){
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.address_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mAddress.setText(list.get(position).getSheng()+list.get(position).getShi()+list.get(position).getXian()+list.get(position).getJiedao());
        holder.mPhone.setText(list.get(position).getMobile());
        holder.mUser.setText(list.get(position).getShouhuoren());
        if (ConstantUtil.MOREN_ID == Integer.parseInt(list.get(position).getId())){
            holder.moren.setTextColor(ContextCompat.getColor(context,R.color.main));
        }else {
            holder.moren.setVisibility(View.GONE);
        }

        if (onItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClick(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size() == 0 ? 0 : list.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        list.remove(position);
        notifyItemRemoved(position);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_delete)
        public ImageView iSchedule;
        public View vBackground; // 背景
        public View vItem;

        @BindView(R.id.sj_address)
        TextView mAddress;
        @BindView(R.id.sj_name)
        TextView mUser;
        @BindView(R.id.sj_phone)
        TextView mPhone;
        @BindView(R.id.moren)
        TextView moren;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            iSchedule.setImageResource(R.drawable.delete_icon);
            vBackground = itemView.findViewById(R.id.linear_background);
            vItem = itemView.findViewById(R.id.content);
        }

    }

    public interface OnItemClickListener{
        void OnItemClick(View view ,int postion);
    }

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
