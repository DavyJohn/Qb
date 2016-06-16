package com.six.qiangbao;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.saint.netlibrary.BangHttpClient;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yyx on 16/5/20.
 */
public class BaseFragment extends Fragment {

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */
    protected CompositeSubscription mCompositeSubscription;

    private MaterialDialog progressDialog;

    protected Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        mCompositeSubscription = new CompositeSubscription();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeSubscription.unsubscribe();
    }

    protected void showDialog(String title,String msg){
        new MaterialDialog.Builder(context)
                .title(title)
                .content(msg)
                .positiveText(R.string.dialog_ok)
                .show();
    }

    protected void showProgressDialog(String msg){
        if (progressDialog != null){
            progressDialog.show();
        }else {
            progressDialog = new MaterialDialog.Builder(context)
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
