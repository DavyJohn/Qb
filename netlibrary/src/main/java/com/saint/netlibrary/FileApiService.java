package com.saint.netlibrary;


import com.saint.netlibrary.model.Avatar;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by zzh on 16-4-17.
 */
public interface FileApiService {
    @Multipart
    @POST("/x/u/userphotoup")
    Observable<Response<Avatar>> Avatar(@Part("Filedata\"; filename=\"Filedata.jpg\" ") RequestBody Filedata);

    /**
     * 应用头像
     * */
    @FormUrlEncoded
    @POST("/x/u/userphotoinsert")
    Observable<Response<Avatar>> apply(@Field("x") int x,
                                      @Field("y") int y,
                                      @Field("w") int w,
                                      @Field("h") int h,
                                      @Field("img")String filename,
                                      @Field("submit") int submit);
}
