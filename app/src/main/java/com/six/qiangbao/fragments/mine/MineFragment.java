package com.six.qiangbao.fragments.mine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.saint.netlibrary.ApiWrapper;
import com.saint.netlibrary.model.Mine;
import com.six.qiangbao.BaseFragment;
import com.six.qiangbao.R;
import com.six.qiangbao.activitys.InvitationActivity;
import com.six.qiangbao.activitys.MainActivity;
import com.six.qiangbao.activitys.PayActivity;
import com.six.qiangbao.activitys.PersionalInfoActivity;
import com.six.qiangbao.fragments.mine.activity.AddressActivity;
import com.six.qiangbao.fragments.mine.activity.HbActivity;
import com.six.qiangbao.fragments.mine.activity.JuActivity;
import com.six.qiangbao.fragments.mine.activity.SettingActivity;
import com.six.qiangbao.fragments.mine.activity.ShopActivity;
import com.six.qiangbao.fragments.mine.activity.ZuActivity;
import com.six.qiangbao.login.LoginActivity;
import com.six.qiangbao.utils.ConstantUtil;
import com.six.qiangbao.utils.DividerDecoration;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import rx.Subscription;
import rx.functions.Action1;


/**
 * Created by yyx on 16/5/20.
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.me_toolbar)Toolbar toolbar;
    @BindView(R.id.mine_recycler)
    RecyclerView mRecycler;

    private static final String [] text = {"我的抢宝记录","获得的商品","账户明细","收获地址管理","我的红包","分享"};
    private static final Integer [] icon = {R.drawable.jilu,R.drawable.shop,R.drawable.zu
            ,R.drawable.address,R.drawable.hb,R.drawable.share};
    private List<String> textlist = new ArrayList<>();
    private List<Integer> iconlist = new ArrayList<>();
    private MineAdapter adapter;


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    data();
                    break;
            }
        }
    };

    Thread mineThread = new Thread(){
        @Override
        public void run() {
            Message mine = new Message();
            mine.what = 0;
            handler.sendMessage(mine);
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mine_fragment_layout,container,false);
        ButterKnife.bind(this,rootView);
        toolbar.setTitle("");
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        initRecycler();
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void  initRecycler(){
        iconlist.clear();
        textlist.clear();
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecycler.addItemDecoration(new DividerDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        for (int i = 0;i<text.length;i++){
            iconlist.add(icon[i]);
        }
        for (int i =0 ;i<text.length;i++){
            textlist.add(text[i]);
        }
        adapter = new MineAdapter(getActivity(),textlist,iconlist);

        adapter.setOnImageClickListener(new MineAdapter.OnImageClickListener() {
            @Override
            public void OnImageClick(View view, int postion) {
                Intent intent = new Intent(getActivity(), PersionalInfoActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(),"image",Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnChongZClickListener(new MineAdapter.OnChongZClickListener() {
            @Override
            public void onChongZClick(View view, int postion) {
                Intent intent = new Intent(getActivity(), PayActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(),"充值",Toast.LENGTH_SHORT).show();

            }
        });

        adapter.setOnYaoqinClickListener(new MineAdapter.OnYaoqinClickListener() {
            @Override
            public void OnYaoqinClick(View view, int postion) {
                Intent intent =  new Intent(getActivity(),InvitationActivity.class);
                startActivity(intent);

            }
        });
        adapter.setOnItemClickListener(new MineAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int postion) {
                if (postion == 0 && ConstantUtil.isMineChange == 0){
                    Intent intent = new Intent();
                    intent.setClass(getActivity(),LoginActivity.class);
                    startActivity(intent);
                }
                if (postion == 1){
                    if ( ConstantUtil.isMineChange == 0){
                        Intent intent = new Intent();
                        intent.setClass(getActivity(),LoginActivity.class);
                        startActivity(intent);
                    }else {
                        Intent intent = new Intent();
                        intent.setClass(getActivity(),JuActivity.class);
                        startActivity(intent);
                    }
                }
                if (postion == 2){
                    if (ConstantUtil.isMineChange == 0){
                        Intent intent = new Intent();
                        intent.setClass(getActivity(),LoginActivity.class);
                        startActivity(intent);
                    }else {
                        Intent intent = new Intent();
                        intent.setClass(getActivity(),ShopActivity.class);
                        startActivity(intent);
                    }

                }
                if (postion == 3){
                    if (ConstantUtil.isMineChange == 0){
                        Intent intent = new Intent();
                        intent.setClass(getActivity(),LoginActivity.class);
                        startActivity(intent);
                    }else {
                        Intent intent = new Intent();
                        intent.setClass(getActivity(),ZuActivity.class);
                        startActivity(intent);
                    }

                }
                if (postion == 4){
                    if (ConstantUtil.isMineChange ==0){
                        Intent intent = new Intent();
                        intent.setClass(getActivity(),LoginActivity.class);
                        startActivity(intent);
                    }else {
                        Intent intent = new Intent();
                        intent.setClass(getActivity(),AddressActivity.class);
                        startActivity(intent);
                    }
                }
                if (postion == 5){
                    if (ConstantUtil.isMineChange == 0){
                        Intent intent = new Intent();
                        intent.setClass(getActivity(),LoginActivity.class);
                        startActivity(intent);
                    }else {
                        Intent intent = new Intent();
                        intent.setClass(getActivity(),HbActivity.class);
                        startActivity(intent);
                    }

                }
                if (postion == 6){
                    if (ConstantUtil.isMineChange == 0){
                        Intent intent = new Intent();
                        intent.setClass(getActivity(),LoginActivity.class);
                        startActivity(intent);
                    }else {
                        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
                        dialog.show();
                        Window mWindow = dialog.getWindow();
                        mWindow.setGravity(Gravity.BOTTOM);
                        mWindow.setContentView(R.layout.share_layout);
                        mWindow.findViewById(R.id.share1).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(),"share1",Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                        mWindow.findViewById(R.id.share2).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(),"share2",Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                        mWindow.findViewById(R.id.share3).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(),"share3",Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                        mWindow.findViewById(R.id.share4).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(),"share4",Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                        mWindow.findViewById(R.id.share5).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(),"share5",Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                    }

                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.set_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.setting){
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onStart() {
        super.onStart();
        mineThread.run();
        initRecycler();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void lazyLoad() {

    }

    private void data(){
        final ApiWrapper wrapper = new ApiWrapper();
        Subscription subscription = wrapper.me()
                .subscribe(newSubscriber(new Action1<Mine>() {
                    @Override
                    public void call(Mine mine) {
                        System.out.print(mine);
                        if (mine.getUid().equals(null)){
                            ConstantUtil.isMineChange = 0;
                        }else {
                            ConstantUtil.isMineChange = 1;
                            adapter.adddata(mine);
                        }
                        mRecycler.setAdapter(adapter);
                    }
                }));
        mCompositeSubscription.add(subscription);
    }


}
