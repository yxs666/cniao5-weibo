package com.cniao5.cweibo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * Project Name：CWeiBo
 * Describe：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，
 * 对课程与服务”吹毛求疵”般的要求，打造极致课程，是菜鸟窝不变的承诺
 * Author：<a href="http://www.cniao5.com">菜鸟窝</a>
 * Created By：yul
 * Modify：yul
 * Modify Time：下午11:01
 * Remark：
 */
public class SPUtils {
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mEditor;
    private static SPUtils instance;
    private static final String SP_NAME = "CWEIBO";
    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private static final String IS_LOGIN = "IS_LOGIN";

    private SPUtils() {

    }

    public static SPUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (SPUtils.class) {
                instance = new SPUtils();
                mSharedPreferences = context.getSharedPreferences(SP_NAME, Activity.MODE_PRIVATE);
                mEditor = mSharedPreferences.edit();
            }
        }
        return instance;
    }

    public void saveToken(Oauth2AccessToken accessToken) {
        mEditor.putString(ACCESS_TOKEN, new Gson().toJson(accessToken)).commit();
        mEditor.putBoolean(IS_LOGIN, true).commit();
    }
    public  Oauth2AccessToken  getToken(){
        String json = mSharedPreferences.getString(ACCESS_TOKEN,"");
        if(TextUtils.isEmpty(json)){
            return null;
        }
        return new Gson().fromJson(json,Oauth2AccessToken.class);
    }

    public boolean isLogin() {
        return mSharedPreferences.getBoolean(IS_LOGIN, false);
    }

}
