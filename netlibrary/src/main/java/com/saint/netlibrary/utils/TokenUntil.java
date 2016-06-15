package com.saint.netlibrary.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.saint.netlibrary.BangHttpClient;

/**
 * Created by yyx on 16/4/15.
 */
public class TokenUntil {

    public static final String TOKEN_CACHE = "TOKEN_CACHE";
    public static final String TOKEN_CACHE_FILE = "TOKEN_CACHE_FILE";

    public static void saveToken(String token){
        SharedPreferences preferences = BangHttpClient.getContext().getSharedPreferences(TOKEN_CACHE_FILE,0);
        SharedPreferences.Editor editor = preferences.edit();
        if (!TextUtils.isEmpty(token)){
            editor.putString(TOKEN_CACHE, "Bearer " + token);
        }
        editor.commit();
    }

    public static String getToken(){
        SharedPreferences preferences = BangHttpClient.getContext().getSharedPreferences(TOKEN_CACHE_FILE,0);
        return preferences.getString(TOKEN_CACHE,"");
    }
}
