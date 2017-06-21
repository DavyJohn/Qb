package com.six.qiangbao.fragments.mine;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.saint.netlibrary.model.Mine;
import com.six.qiangbao.R;
import com.six.qiangbao.fragments.mine.fragment.IngFragment;
import com.six.qiangbao.login.LoginActivity;
import com.six.qiangbao.utils.ConstantUtil;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by 志浩 on 2016/6/16.
 */
public class MineAdapter extends RecyclerView.Adapter<MineAdapter.ViewHolder> {

    private List<Integer> icon = new ArrayList<>();
    private List<String> text = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;
    private String phone, imageurl, experience, level, balance, name;

    public MineAdapter(Context context, List<String> listte, List<Integer> listic) {
        this.context = context;
        this.icon = listic;
        this.text = listte;
        inflater = LayoutInflater.from(context);
    }

    private static final int HEADER_ITEM_FLAG = -1;
    private static final int NORMAL_ITEM_FLAG = 1;
    private static final int END_ITEM_FLAG = 2;

    public void adddata(Mine mine) {
        name = mine.getUsername();
        imageurl = mine.getImg();
        experience = mine.getJingyan();
        level = mine.getYungoudj();
        balance = mine.getMoney();
        phone = mine.getMobile();
    }

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
                holder = new ViewHolder(view, HEADER_ITEM_FLAG);
            } else if (ConstantUtil.isMineChange == 1) {
                View view = inflater.inflate(R.layout.mine_login__layout, parent, false);
                holder = new ViewHolder(view, HEADER_ITEM_FLAG);
            }

        } else if (viewType == END_ITEM_FLAG) {
            View view = inflater.inflate(R.layout.mine_end_recycler_layout, parent, false);
            holder = new ViewHolder(view, END_ITEM_FLAG);
        } else if (viewType == NORMAL_ITEM_FLAG) {
            View view = inflater.inflate(R.layout.mine_normal_recycler_layout, parent, false);
            holder = new ViewHolder(view, NORMAL_ITEM_FLAG);
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

        if (position == 0 && ConstantUtil.isMineChange == 1) {
            Picasso.with(context).load(ConstantUtil.IMAGE_HEAD + imageurl).into(holder.mImageUser);
            holder.mTextMoney.setText(balance);
            holder.mTextLevel.setText(level);
            holder.mTextEx.setText("经验：" + experience);
            if (name==null) {
                holder.mTextUserName.setText(phone);
            } else {
                holder.mTextUserName.setText(name);
            }

            if (onImageClickListener !=null){
                holder.mImageUser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onImageClickListener.OnImageClick(holder.mImageUser,position);
                    }
                });
            }

            if (onChongZClickListener != null){
                holder.mBtn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onChongZClickListener.onChongZClick(holder.mBtn2,position);
                    }
                });
            }

            if (onYaoqinClickListener != null){
                holder.mBtn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onYaoqinClickListener.OnYaoqinClick(holder.mBtn1,position);
                    }
                });
            }

        } else if (position == 7) {
            holder.mTextEnd.setText("本公司拥有一切解释权");
        } else  if (position>0&&position<7){
            holder.mTextContent.setText(text.get(position - 1));
            Picasso.with(context).load(icon.get(position - 1)).into(holder.mImageNormal);
        }else if (position == 0&&ConstantUtil.isMineChange == 0){
            //可省略
        }

    }

    @Override
    public int getItemCount() {
        return text.size() + 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //未登录p = 0;
        Button mBtnLogin;
        //登录状态p =1；
        ImageView mImageUser;
        TextView mTextUserName;
        TextView mTextLevel;
        TextView mTextEx;
        TextView mTextMoney;
        Button mBtn1;
        Button mBtn2;

        //normal
        TextView mTextContent;
        ImageView mImageNormal;

        //end
        TextView mTextEnd;
        LinearLayout login;

        public ViewHolder(View itemView, int type) {
            super(itemView);
            if (type == HEADER_ITEM_FLAG) {
                if (ConstantUtil.isMineChange == 0) {
                    mBtnLogin = (Button) itemView.findViewById(R.id.mine_login);
                    mBtnLogin.setClickable(false);
                } else if (ConstantUtil.isMineChange == 1) {
                    login = (LinearLayout) itemView.findViewById(R.id.lin_login);
                    mTextUserName = (TextView) itemView.findViewById(R.id.mine_username);
                    mImageUser = (ImageView) itemView.findViewById(R.id.mine_userimage);
                    mTextLevel = (TextView) itemView.findViewById(R.id.text_leave);
                    mTextEx = (TextView) itemView.findViewById(R.id.mine_experience);
                    mTextMoney = (TextView) itemView.findViewById(R.id.mine_money);
                    mBtn1 = (Button) itemView.findViewById(R.id.btn1);
                    mBtn2 = (Button) itemView.findViewById(R.id.btn2);
                }

            } else if (type == NORMAL_ITEM_FLAG) {
                mTextContent = (TextView) itemView.findViewById(R.id.mine_normal_content);
                mImageNormal = (ImageView) itemView.findViewById(R.id.mine_normal_image);
            } else if (type == END_ITEM_FLAG) {
                mTextEnd = (TextView) itemView.findViewById(R.id.mine_end_text);
            }

        }
    }

    /**
     * item点击
     */
    public interface OnItemClickListener {
        void OnItemClick(View view, int postion);
    }

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * item中imageview的点击
     */

    public interface OnImageClickListener {
        void OnImageClick(View view, int postion);
    }

    public OnImageClickListener onImageClickListener;

    public void setOnImageClickListener(OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }

    /**
     * item中邀请码的点击
     */

    public interface OnYaoqinClickListener {
        void OnYaoqinClick(View view, int postion);
    }

    public OnYaoqinClickListener onYaoqinClickListener;

    public void setOnYaoqinClickListener(OnYaoqinClickListener onYaoqinClickListener) {
        this.onYaoqinClickListener = onYaoqinClickListener;
    }

    /**
     * item中充值的点击
     */

    public interface OnChongZClickListener {
        void onChongZClick(View view, int postion);
    }

    public OnChongZClickListener onChongZClickListener;

    public void setOnChongZClickListener(OnChongZClickListener onChongZClickListener) {
        this.onChongZClickListener = onChongZClickListener;
    }
}
