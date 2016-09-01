package com.saint.netlibrary;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.saint.netlibrary.utils.ConstantUtil;
import com.saint.netlibrary.utils.ExceptionUtil;
import com.saint.netlibrary.utils.PersistentCookieStore;
import com.saint.netlibrary.utils.TokenUntil;

import java.io.File;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Route;
import okhttp3.internal.framed.Header;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by yyx on 16/3/1.
 */
public class BangHttpClient {

    private static Retrofit retrofit;
    private static APIService service;
    private static FileApiService fileApiService;
    private static Context context;
    public static void init(Context c){
        context = c;
    }

    public static Context getContext(){
        return context;
    }

    public static boolean isConnected() {
        boolean flag = false;
        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conn.getActiveNetworkInfo();
        if (info != null && info.isConnected()){
            flag = true;
        }
        return flag;
    }

    public static APIService getService(){
        if (service == null){
            service = getRetrofit().create(APIService.class);
        }
        return service;
    }

    public static FileApiService getFileService(){
        if (fileApiService == null){
            fileApiService = getFileRetrofit().create(FileApiService.class);
        }
        return fileApiService;
    }

    private static Retrofit getFileRetrofit(){
        if (retrofit == null){
            FileHttpLoggingInterceptor interceptor = new FileHttpLoggingInterceptor(new FileHttpLoggingInterceptor.Logger(){
                @Override
                public void log(String message) {
                }
            });
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(ConstantUtil.API_HOST)
                    .addConverterFactory(MyGsonConverter.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }

        return retrofit;
    }

    private static Retrofit getRetrofit(){
        if (retrofit == null){
            FileHttpLoggingInterceptor interceptor = new FileHttpLoggingInterceptor();
            interceptor.setLevel(FileHttpLoggingInterceptor.Level.BODY);

            Interceptor mTokenInterceptor = new Interceptor() {
                @Override public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request originalRequest = chain.request();
                    String cacheControl = originalRequest.cacheControl().toString();
                    if (!isConnected()) {
                        originalRequest = originalRequest.newBuilder()
                                .cacheControl(CacheControl.FORCE_CACHE)
                                .header(ConstantUtil.CONTENT_TYPE, ConstantUtil.CONTENT_TYPE_JSON).build();
                    }
                    if (!TextUtils.isEmpty(TokenUntil.getToken())) {
                        originalRequest = originalRequest.newBuilder()
                                .header(ConstantUtil.CONTENT_TYPE,ConstantUtil.CONTENT_TYPE_JSON)
                                .build();
                    }
                    okhttp3.Response response = chain.proceed(originalRequest);
                    if (isConnected()) {
                        response = response.newBuilder()
                                .header("Cache-Control", cacheControl)
                                .removeHeader("Pragma").build();
                    }else {
                        response = response.newBuilder()
                                .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                                .removeHeader("Pragma")
                                .build();
                    }
                    return response;
                }
            };

            Authenticator mAuthenticator = new Authenticator() {
                @Override
                public Request authenticate(Route route, okhttp3.Response response) throws IOException {
                    return null;
                }
            };

            ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(),new SharedPrefsCookiePersistor(context));
            File cacheFile = new File(context.getCacheDir(),"httpcache");
            Cache cache = new Cache(cacheFile,1024*1024*10);//10M缓存
            OkHttpClient client = new OkHttpClient.Builder()
                    .cookieJar(cookieJar)
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(mTokenInterceptor)
                    .addInterceptor(mTokenInterceptor)
                    .authenticator(mAuthenticator)
                    .cache(cache)
                    .build();
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(ConstantUtil.API_HOST)
                    .addConverterFactory(MyGsonConverter.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static boolean alreadyHasAuthorizationHeader(okhttp3.Request request){
        return TextUtils.isEmpty(request.header(ConstantUtil.AUTHORIZZTION)) ? false :true;
    }

    /**
     * 对网络接口返回的Response进行分割操作
     *
     * @param response
     * @param <T>
     * @return
     */
    public <T> Observable<T> flatResponse(final Response<T> response) {
        return Observable.create(new Observable.OnSubscribe<T>() {

            @Override
            public void call(Subscriber<? super T> subscriber) {
                if (response.isSuccessful()) {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(response.body());
                    }
                } else {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onError(new APIException(response.code()));
                    }
                    return;
                }

                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }

            }
        });
    }

    /**
     * 自定义异常，当接口返回的{@link Response#code}大于300时，需要跑出此异常
     * eg：登陆时验证码错误；参数为传递等
     */
    public static class APIException extends Exception {
        public int code;
        public String message;

        public APIException(int code) {
            this.code = code;
            this.message = ExceptionUtil.throwExceptionMessage(code);
        }

        @Override
        public String getMessage() {
            return message;
        }
    }

    final Observable.Transformer transformer = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return ((Observable) observable).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(new Func1() {
                        @Override
                        public Object call(Object response) {
                            return flatResponse((Response<Object>)response);
                        }
                    });

        }
    };

    protected <T> Observable.Transformer<Response<T>, T> applySchedulers() {
        return (Observable.Transformer<Response<T>, T>) transformer;
    }

}
