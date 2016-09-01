package com.saint.netlibrary;

import com.saint.netlibrary.model.AddAddressSuccess;
import com.saint.netlibrary.model.AddShopCarResult;
import com.saint.netlibrary.model.AddressDataItem;
import com.saint.netlibrary.model.Avatar;
import com.saint.netlibrary.model.BannerData;
import com.saint.netlibrary.model.CarList;

import com.saint.netlibrary.model.ChangeNameResultInfo;
import com.saint.netlibrary.model.DelCartItemResult;
import com.saint.netlibrary.model.GoodsQbJuData;
import com.saint.netlibrary.model.HongbaoData;
import com.saint.netlibrary.model.LatestAnnouncement;
import com.saint.netlibrary.model.Login;
import com.saint.netlibrary.model.Mine;
import com.saint.netlibrary.model.MobileCheck;
import com.saint.netlibrary.model.MyQbJu;
import com.saint.netlibrary.model.PayForResultData;
import com.saint.netlibrary.model.RechargeData;
import com.saint.netlibrary.model.ShopDetails;
import com.saint.netlibrary.model.ShopListData;
import com.saint.netlibrary.model.WqGoods;
import com.saint.netlibrary.model.XIaoFeiJilu;

import java.io.File;
import java.util.List;

import butterknife.OnClick;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;

/**
 * Created by yyx on 16/3/1.
 */
public class ApiWrapper extends BangHttpClient {

//    public Observable<WxAuth> getWxToken(String url){
//        return getService().getWxToken(url)
//                .compose(this.<WxAuth>applySchedulers());
//    }

    /**
     * 获取USER_DATA
     */
    public Observable<Mine> me(){
        return getService().me().compose(this.<Mine>applySchedulers());
    }

    /**
     * login
     */

    public Observable<Login> login(String username, String password) {
        return getService().login(username, password).compose(this.<Login>applySchedulers());
    }

    /**
     * 最新揭晓
     */

    public Observable<LatestAnnouncement> announcement(String start, String number) {
        return getService().announcement(start, number).compose(this.<LatestAnnouncement>applySchedulers());
    }

    /**
     * 所有商品
     */
    public Observable<List<ShopListData>> shopList(String cat, String sel, String page) {
        return getService().shoplist(cat, sel, page).compose(this.<List<ShopListData>>applySchedulers());
    }

    /**
     * 商品分类
     */
    public Observable<List<CarList>> carList() {
        return getService().carList().compose(this.<List<CarList>>applySchedulers());
    }

    /**
     * 商品详情
     */
    public Observable<ShopDetails> shopDetails(String id) {
        return getService().shopDetails(id).compose(this.<ShopDetails>applySchedulers());
    }

    public Observable<WqGoods> shopUrl(String url) {
        return getService().shopUrl(url).compose(this.<WqGoods>applySchedulers());
    }

    /**
     * 注册
     */
    public Observable<String> getCode(String submit, String name, String userpassword, String userpassword2) {
        return getService().getCode(submit, name, userpassword, userpassword2).compose(this.<String>applySchedulers());

    }

    /**
     * 验证验证码
     */
    public Observable<MobileCheck> CheckMobileCode(String submit, String check_code, String checkcodes) {
        return getService().Check_code(submit, check_code, checkcodes).compose(this.<MobileCheck>applySchedulers());
    }

    /**
     * 获取验证码
     */

    public Observable<MobileCheck> getCode(String check_code) {
        return getService().getCheck(check_code).compose(this.<MobileCheck>applySchedulers());
    }

    /**
     * 重获验证码
     */
    public Observable<MobileCheck> reCode(String check_code) {
        return getService().reCode(check_code).compose(this.<MobileCheck>applySchedulers());

    }

    /**
     * 首页幻灯片
     */

    public Observable<BannerData> bannerdata() {
        return getService().bannerdata().compose(this.<BannerData>applySchedulers());
    }

    /**
     * 往期商品
     * */
    public Observable<WqGoods> wqgoods(String id){
        return getService().wqgoods(id).compose(this.<WqGoods>applySchedulers());
    }

    /**\
     * 我的抢宝记录
     * */

    public Observable<MyQbJu> ju(String start,String per_page,String is_count,String status){
        return getService().ju(start,per_page,is_count,status).compose(this.<MyQbJu>applySchedulers());
    }

    /**
     * 已获得的商品
     * */

    public Observable<MyQbJu> owned(String start ,String per_page){
        return getService().owned(start,per_page).compose(this.<MyQbJu>applySchedulers());
    }

    /**
     * 列出所有收货地址
     * */
    public Observable<List<AddressDataItem>> alladdress(){
        return getService().alladdress().compose(this.<List<AddressDataItem>>applySchedulers());
    }

    /**
     * 新增收货地址
     * */

    public Observable<AddAddressSuccess> postaddress(String sheng,String shi,String xian,String jiedao,String youbian,
                                                     String shouhuoren,String mobile,String shifoufahuo,  String submit){
        return getService().postaddress(sheng,shi,xian,jiedao,youbian,shouhuoren,mobile,shifoufahuo,submit).compose(this.<AddAddressSuccess>applySchedulers());
    }

    /**
     * 加入购物车
     * */

    public Observable<AddShopCarResult> addcar(String id ,String num){
        return getService().addcar(id,num).compose(this.<AddShopCarResult>applySchedulers());
    }

    public Observable<AddShopCarResult> addcarnum(String id ,String num,String from){
        return getService().addcartnum(id,num,from).compose(this.<AddShopCarResult>applySchedulers());
    }
    /**
     *删除购物车商品
     * */

    public Observable<DelCartItemResult> delcart(String id){
        return getService().delcart(id).compose(this.<DelCartItemResult>applySchedulers());
    }

    /**
     * 邀请码
     * */
    public Observable<String> invitation(String yaoqingren){
        return getService().invitation(yaoqingren).compose(this.<String>applySchedulers());
    }

    /**
     *余额充值
     * */

    public Observable<RechargeData> pay(Double amount){
        return getService().pay(amount).compose(this.<RechargeData>applySchedulers());
    }

    /**
     * 修改用户名和签名
     * */

    public Observable<ChangeNameResultInfo> changnameresult(String username,String submit){
        return getService().changnameresult(username,submit).compose(this.<ChangeNameResultInfo>applySchedulers());
    }

    /**
     * 上传头像文件
     * */

    public Observable<Avatar> avatar(RequestBody Filedata){
        return getFileService().Avatar(Filedata).compose(this.<Avatar>applySchedulers());
    }

    /**
     * 应用头像
     * */
    public Observable<Avatar> apply(int x,int y,int w,int h,String img,int submit){
        return getFileService().apply(x,y,w,h,img,submit).compose(this.<Avatar>applySchedulers());
    }

    /**
     *将指定地址设为默认地址
     * */

    public Observable<Avatar> moren(String id){
        return getService().moren(id).compose(this.<Avatar>applySchedulers());
    }

    /**
     * 删除地址
     * */

    public Observable<Avatar> del (String id){
        return getService().del(id).compose(this.<Avatar>applySchedulers());
    }

    /**
     * 消费记录
     * */

    public Observable<XIaoFeiJilu> xiaofei(String start,String per_page){
        return  getService().xiaofei(start,per_page).compose(this.<XIaoFeiJilu>applySchedulers());
    }

    /**
     * 查看红包
     * */
    public Observable<List<HongbaoData>> haobao(String order_money,String used,String valid,String invalid){
        return getService().hongbao(order_money,used,valid,invalid).compose(this.<List<HongbaoData>>applySchedulers());
    }

    /**
     * 所有抢宝记录
     * */

    public Observable<List<GoodsQbJuData>> qbdata(String id){
        return getService().qbdata(id).compose(this.<List<GoodsQbJuData>>applySchedulers());
    }

    /**
     * 实际支付
     * */

    public Observable<PayForResultData> pay(){
        return getService().pay().compose(this.<PayForResultData>applySchedulers());
    }

    public Observable<DelCartItemResult> payfor(String method,String bank,String money,String fufen,String code,String hongbao){
        return getService().payfor(method,bank,money,fufen,code,hongbao).compose(this.<DelCartItemResult>applySchedulers());
    }

}
