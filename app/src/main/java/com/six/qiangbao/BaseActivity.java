package com.six.qiangbao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;
import com.saint.netlibrary.BangHttpClient;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import butterknife.ButterKnife;
import cn.beecloud.BeeCloud;
import io.realm.Realm;
import rx.Subscriber;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yyx on 16/5/20.
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */
    protected CompositeSubscription mCompositeSubscription;

    private MaterialDialog progressDialog;
    protected Activity mContext;
    private Realm realm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCompositeSubscription = new CompositeSubscription();
        mContext = this;
        realm = Realm.getDefaultInstance();

    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 含有Bundle通过Action跳转界面
     **/
    public void startActivity(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 含有Bundle通过Class打开编辑界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 通过Action跳转界面
     **/
    public void startActivity(String action) {
        startActivity(action, null);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
        mCompositeSubscription.unsubscribe();
        realm.close();
    }

    protected void showDialog(String title,String msg){
        new MaterialDialog.Builder(this)
                .title(title)
                .content(msg)
                .positiveText(R.string.dialog_ok)
                .show();
    }

    protected void showProgressDialog(String msg){
        if (progressDialog != null){
            progressDialog.show();
        }else {
            progressDialog = new MaterialDialog.Builder(this)
                    .title("")
                    .content(msg)
                    .progress(true,2)
                    .build();
            progressDialog.show();
        }
    }

    protected void dismissProgressDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }

    /**
     * 创建观察者
     *
     * @param onNext
     * @param <T>
     * @return
     */
    protected <T> Subscriber newSubscriber(final Action1<? super T> onNext) {
        return new Subscriber<T>() {


            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                if (e instanceof BangHttpClient.APIException) {
                    BangHttpClient.APIException exception = (BangHttpClient.APIException) e;
                } else if (e instanceof SocketTimeoutException) {
                } else if (e instanceof ConnectException) {
                }
            }

            @Override
            public void onNext(T t) {
                if (!mCompositeSubscription.isUnsubscribed()) {
                    onNext.call(t);
                }
            }

        };
    }
}
