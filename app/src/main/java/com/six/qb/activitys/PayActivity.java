package com.six.qb.activitys;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.six.qb.BaseActivity;
import com.six.qb.R;
import com.six.qb.utils.AppManager;
import com.six.qb.utils.BillUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.beecloud.BCCache;
import cn.beecloud.BCPay;
import cn.beecloud.BCQuery;
import cn.beecloud.async.BCCallback;
import cn.beecloud.async.BCResult;
import cn.beecloud.entity.BCBillOrder;
import cn.beecloud.entity.BCPayResult;
import cn.beecloud.entity.BCQueryBillResult;
import cn.beecloud.entity.BCReqParams;

/**
 * Created by 志浩 on 2016/8/22.
 */
public class PayActivity extends BaseActivity {
    private final static String TAG = PayActivity.class.getSimpleName();


    //记录一下是否是PayPal支付
    private boolean isPayPal;
    private String toastMsg;
    @BindView(R.id.end_money)
    EditText mEditText;
    @BindView(R.id.pay_bar)
    Toolbar mToolbar;
    @OnClick(R.id.btn_send) void send(){
        pay();
    }

    private ProgressDialog loadingDialog;

    //支付结果返回入口
    BCCallback bcCallback = new BCCallback() {
        @Override
        public void done(final BCResult bcResult) {
            final BCPayResult bcPayResult = (BCPayResult)bcResult;
            //此处关闭loading界面
            loadingDialog.dismiss();

            //根据你自己的需求处理支付结果
            String result = bcPayResult.getResult();

            /*
              注意！
              所有支付渠道建议以服务端的状态金额为准，此处返回的RESULT_SUCCESS仅仅代表手机端支付成功
            */
            Message msg = mHandler.obtainMessage();
            //单纯的显示支付结果
            msg.what = 2;
            if (result.equals(BCPayResult.RESULT_SUCCESS)) {
                toastMsg = "用户支付成功";

                //如果是PayPal，手机端支付完成后还需要向BeeCloud服务器发送同步请求，并校验支付结果
                if (isPayPal) {
                    //如果是PayPal，detail info里面包含订单的json字符串
                    final String syncStr = bcPayResult.getDetailInfo();
                    isPayPal = false;
                    Log.w(TAG, "start to sync PayPal result to BeeCloud server...");

                    loadingDialog.show();
                    //由于同步过程中需要向PayPal服务器请求token，请求失败的几率比较高，此处设置了三次循环
                    BCCache.executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            int i = 0;
                            BCPayResult syncResult;
                            for (; i < 3; i++) {
                                Log.w(TAG, String.format("sync for %d time(s)", i+1));
                                syncResult = BCPay.getInstance(PayActivity.this).syncPayPalPayment(syncStr);

                                if (syncResult.getResult().equals(BCPayResult.RESULT_SUCCESS)) {
                                    Log.w(TAG, "sync succ!!!");
                                    Log.w(TAG, "this bill id can be stored for query by id: " + syncResult.getId());
                                    break;
                                } else {
                                    Log.e(TAG, "sync fail reason: " + syncResult.getErrCode() + " # " +
                                            syncResult.getErrMsg() + " # " + syncResult.getDetailInfo());
                                }
                            }

                            loadingDialog.dismiss();

                            //注意，如果一直失败，你需要将该json串保留起来，下次继续同步，否者在你在BeeCloud控制台看不到这笔订单
                            if (i == 3) {
                                Log.e(TAG, "BAD result!!! Sync failed for three times!!!");
                                Log.w(TAG, "please store the json string to somewhere for later sync: " + syncStr);
                            }
                        }
                    });
                }
            } else if (result.equals(BCPayResult.RESULT_CANCEL))
                toastMsg = "用户取消支付";
            else if (result.equals(BCPayResult.RESULT_FAIL)) {
                toastMsg = "支付失败, 原因: " + bcPayResult.getErrCode() +
                        " # " + bcPayResult.getErrMsg() +
                        " # " + bcPayResult.getDetailInfo();

                /**
                 * 你发布的项目中不应该出现如下错误，此处由于支付宝政策原因，
                 * 不再提供支付宝支付的测试功能，所以给出提示说明
                 */
                if (bcPayResult.getErrMsg().equals("PAY_FACTOR_NOT_SET") &&
                        bcPayResult.getDetailInfo().startsWith("支付宝参数")) {
                    toastMsg = "支付失败：由于支付宝政策原因，故不再提供支付宝支付的测试功能，给您带来的不便，敬请谅解";
                }

                /**
                 * 以下是正常流程，请按需处理失败信息
                 */
                Log.e(TAG, toastMsg);

                if (bcPayResult.getErrMsg().equals(BCPayResult.FAIL_PLUGIN_NOT_INSTALLED)) {
                    //银联需要重新安装控件
                    msg.what = 1;
                }

            } else if (result.equals(BCPayResult.RESULT_UNKNOWN)) {
                //可能出现在支付宝8000返回状态
                toastMsg = "订单状态未知";
            } else {
                toastMsg = "invalid return";
            }

            mHandler.sendMessage(msg);


            if (bcPayResult.getId() != null) {
                //你可以把这个id存到你的订单中，下次直接通过这个id查询订单
                Log.w(TAG, "bill id retrieved : " + bcPayResult.getId());

                //根据ID查询，此处只是演示如何通过id查询订单，并非支付必要部分
                getBillInfoByID(bcPayResult.getId());
            }

        }
    };

    // Defines a Handler object that's attached to the UI thread.
    // 通过Handler.Callback()可消除内存泄漏警告
    private Handler mHandler = new Handler(new Handler.Callback() {
        /**
         * Callback interface you can use when instantiating a Handler to avoid
         * having to implement your own subclass of Handler.
         *
         * handleMessage() defines the operations to perform when
         * the Handler receives a new Message to process.
         */
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    break;
                case 2:
                    Toast.makeText(PayActivity.this, toastMsg, Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_main_layout);
        AppManager.getAppManager().addActivity(mContext);
        toolbar();

    }

    private void toolbar(){
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.left_icon);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void pay(){

        Map<String,String> mapOptional = new HashMap<String, String>();
        mapOptional.put("客户端", "安卓");
        mapOptional.put("consumptioncode", "consumptionCode");
        mapOptional.put("money", "2");
        BCPay.getInstance(PayActivity.this).reqAliPaymentAsync(
                "为了联盟安卓支付宝支付测试",
                1,
                BillUtils.genBillNum(),
                mapOptional,
                bcCallback);

    }

    void getBillInfoByID(String id) {

        BCQuery.getInstance().queryBillByIDAsync(id,
                new BCCallback() {
                    @Override
                    public void done(BCResult result) {
                        BCQueryBillResult billResult = (BCQueryBillResult) result;

                        Log.d(TAG, "------ response info ------");
                        Log.d(TAG, "------getResultCode------" + billResult.getResultCode());
                        Log.d(TAG, "------getResultMsg------" + billResult.getResultMsg());
                        Log.d(TAG, "------getErrDetail------" + billResult.getErrDetail());

                        if (billResult.getResultCode() != 0)
                            return;

                        Log.d(TAG, "------- bill info ------");
                        BCBillOrder billOrder = billResult.getBill();
                        Log.d(TAG, "订单唯一标识符：" + billOrder.getId());
                        Log.d(TAG, "订单号:" + billOrder.getBillNum());
                        Log.d(TAG, "订单金额, 单位为分:" + billOrder.getTotalFee());
                        Log.d(TAG, "渠道类型:" + BCReqParams.BCChannelTypes.getTranslatedChannelName(billOrder.getChannel()));
                        Log.d(TAG, "子渠道类型:" + BCReqParams.BCChannelTypes.getTranslatedChannelName(billOrder.getSubChannel()));
                        Log.d(TAG, "订单是否成功:" + billOrder.getPayResult());

                        if (billOrder.getPayResult())
                            Log.d(TAG, "渠道返回的交易号，未支付成功时，是不含该参数的:" + billOrder.getTradeNum());
                        else
                            Log.d(TAG, "订单是否被撤销，该参数仅在线下产品（例如二维码和扫码支付）有效:"
                                    + billOrder.getRevertResult());

                        Log.d(TAG, "订单创建时间:" + new Date(billOrder.getCreatedTime()));
                        Log.d(TAG, "扩展参数:" + billOrder.getOptional());
                        Log.w(TAG, "订单是否已经退款成功(用于后期查询): " + billOrder.getRefundResult());
                        Log.w(TAG, "渠道返回的详细信息，按需处理: " + billOrder.getMessageDetail());

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BCPay.clear();
    }
}
