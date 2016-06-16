package com.six.qiangbao.wxapi;

import cn.sharesdk.wechat.utils.WXMediaMessage;
import cn.sharesdk.wechat.utils.WechatHandlerActivity;

/**
 * Created by yyx on 16/5/21.
 */
public class WXEntryActivity extends WechatHandlerActivity{

    @Override
    public void onGetMessageFromWXReq(WXMediaMessage msg) {
        super.onGetMessageFromWXReq(msg);
    }

    @Override
    public void onShowMessageFromWXReq(WXMediaMessage msg) {
        super.onShowMessageFromWXReq(msg);
    }



//    private void getToken(String code){
//        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
//                "appid=" + ConstantUtil.WECHAT_APPID+
//                "&secret=" + ConstantUtil.WECHAT_AppSecret+
//                        "&code=" +code+
//                        "&grant_type=authorization_code";
//        final ApiWrapper wrapper = new ApiWrapper();
//        Subscription subscription = wrapper.getWxToken(url)
//                .subscribe(newSubscriber(new Action1<WxAuth>() {
//                    @Override
//                    public void call(WxAuth wxAuth) {
//                        Logger.i(wxAuth.toString());
//                        final BmobUser.BmobThirdUserAuth authInfo = new BmobUser.BmobThirdUserAuth(
//                                BmobUser.BmobThirdUserAuth.SNS_TYPE_WEIXIN,wxAuth.getAccess_token()
//                        ,wxAuth.getExpires_in()+"",wxAuth.getOpenid());
//                        BmobUser.loginWithAuthData(WXEntryActivity.this, authInfo, new OtherLoginListener() {
//                            @Override
//                            public void onSuccess(JSONObject jsonObject) {
//                                Logger.i("登录成功!");
//                            }
//
//                            @Override
//                            public void onFailure(int i, String s) {
//                                Logger.i("登录失败");
//                            }
//                        });
//                    }
//                }));
//        mCompositeSubscription.add(subscription);
//    }
}
