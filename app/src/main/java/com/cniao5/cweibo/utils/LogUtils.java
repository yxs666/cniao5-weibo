package com.cniao5.cweibo.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * Project Name：CWeiBo
 * Describe：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，
 * 对课程与服务”吹毛求疵”般的要求，打造极致课程，是菜鸟窝不变的承诺
 * Author：<a href="http://www.cniao5.com">菜鸟窝</a>
 * Created By：yul
 * Modify：yul
 * Modify Time：9:29 PM
 * Remark：
 */
public class LogUtils {
    private static final String TAG = "cniao5";
    public static void e(String message){
        if(!TextUtils.isEmpty(message)){
            Log.e(TAG,message);
        }
    }
    public static void d(String message){
        if(!TextUtils.isEmpty(message)){
            Log.d(TAG, message);
        }
    }
    public static void o(String tag,String message){
        if(!TextUtils.isEmpty(message)){
            Log.d(tag,message);
        }
    }
}
