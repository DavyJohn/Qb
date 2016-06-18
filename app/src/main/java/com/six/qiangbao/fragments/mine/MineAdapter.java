package com.six.qiangbao.fragments.mine;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.six.qiangbao.R;
import com.six.qiangbao.login.LoginActivity;
import com.six.qiangbao.utils.ConstantUtil;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 志浩 on 2016/6/16.
 */
public class MineAdapter extends RecyclerView.Adapter<MineAdapter.ViewHolder> {

    private List<Integer> icon = new ArrayList<>();
    private List<String> text = new ArrayList<>();

    private LayoutInflater inflater;
    private Context context;

    public MineAdapter(Context context, List listte,List listic) {
        this.context = context;
        this.icon = listic;
        this.text = listte;
        inflater = LayoutInflater.from(context);
    }

    private static final int HEADER_ITEM_FLAG = -1;
    private static final int NORMAL_ITEM_FLAG = 1;
    private static final int END_ITEM_FLAG = 2;

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_ITEM_FLAG;
        } else if (position == text.size() + 1) {
            return END_ITEM_FLAG;
        } else {
            return NORMAL_ITEM_FLAG;
        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        if (viewType == HEADER_ITEM_FLAG) {
            if (ConstantUtil.isMineChange == 0) {
                View view = inflater.inflate(R.layout.mine_login_un_layout, parent, false);
                holder = new ViewHolder(view,HEADER_ITEM_FLAG);
            } else {
                View view = inflater.inflate(R.layout.mine_login__layout, parent, false);
                holder = new ViewHolder(view,HEADER_ITEM_FLAG);
            }
        } else if (viewType == END_ITEM_FLAG) {
            View view = inflater.inflate(R.layout.mine_end_recycler_layout, parent, false);
            holder = new ViewHolder(view,END_ITEM_FLAG);
        } else if (viewType == NORMAL_ITEM_FLAG) {
            View view = inflater.inflate(R.layout.mine_normal_recycler_layout, parent, false);
            holder = new ViewHolder(view,NORMAL_ITEM_FLAG);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnItemClick(holder.itemView, position);

                }
            });
        }
        if (position == 0) {

        }else  if (position == 7) {
            holder.mTextEnd.setText("本公司拥有一切解释权");
        } else {
            holder.mTextContent.setText(text.get(position-1));
            Picasso.with(context).load(icon.get(position-1)).into(holder.mImageNormal);
        }


    }

    @Override
    public int getItemCount() {
        return text.size() + 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //未登录 p = 0;
        Button mBtnLogin;
        //登录状态p =0；
        ImageView mImageUser;
        TextView mTextUserName;
        TextView mTextLeave;
        TextView mTextEx;
        TextView mTextMoney;
        Button mBtn1;
        Button mBtn2;

        //normal
        TextView mTextContent;
        ImageView mImageNormal;

        //end
        TextView mTextEnd;

        public ViewHolder(View itemView ,int type) {
            super(itemView);
            if (type == HEADER_ITEM_FLAG){
                mBtnLogin = (Button) itemView.findViewById(R.id.mine_login);
                mTextUserName = (TextView) itemView.findViewById(R.id.mine_username);
                mImageUser = (ImageView) itemView.findViewById(R.id.mine_userimage);
                mTextLeave = (TextView) itemView.findViewById(R.id.text_leave);
                mTextEx  = (TextView) itemView.findViewById(R.id.mine_experience);
                mTextMoney = (TextView) itemView.findViewById(R.id.mine_money);
                mBtn1 = (Button) itemView.findViewById(R.id.btn1);
                mBtn2 = (Button) itemView.findViewById(R.id.btn2);
                mBtnLogin.setClickable(false);
            }else if (type == NORMAL_ITEM_FLAG){
                mTextContent = (TextView) itemView.findViewById(R.id.mine_normal_content);
                mImageNormal = (ImageView) itemView.findViewById(R.id.mine_normal_image);
            }else if (type == END_ITEM_FLAG){
                mTextEnd = (TextView) itemView.findViewById(R.id.mine_end_text);
            }

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
