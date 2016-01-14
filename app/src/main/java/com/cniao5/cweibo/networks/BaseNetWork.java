package com.cniao5.cweibo.networks;

import android.content.Context;

import com.cniao5.cweibo.entities.HttpResponse;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;

/**
 * Project Name：CWeiBo
 * Describe：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，
 * 对课程与服务”吹毛求疵”般的要求，打造极致课程，是菜鸟窝不变的承诺
 * Author：<a href="http://www.cniao5.com">菜鸟窝</a>
 * Created By：yul
 * Modify：yul
 * Modify Time：8:59 PM
 * Remark：
 */
public abstract class BaseNetWork {
    private AsyncWeiboRunner mAsyncWeiboRunner;
    private String url;

    public BaseNetWork(Context context, String url) {
        mAsyncWeiboRunner = new AsyncWeiboRunner(context);
        this.url = url;
    }

    private RequestListener mRequestListener = new RequestListener() {
        public void onComplete(String s) {
            boolean success = false;
            HttpResponse response = new HttpResponse();
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(s);
            if (element.isJsonObject()) {
                JsonObject object = element.getAsJsonObject();
                if (object.has("error_code")) {
                    response.code = object.get("error_code").getAsInt();
                }
                if ((object.has("error"))) {
                    response.message = object.get("error").getAsString();

                }
                if (object.has("statuses")) {
                    response.response = object.get("statuses").toString();
                    success = true;

                } else if (object.has("users")) {
                    response.response = object.get("users").toString();
                    success = true;


                } else if (object.has("comments")) {
                    response.response = object.get("comments").toString();
                    success = true;

                } else {
                    response.response = s;
                    success = true;
                }
            }
            onFinish(response, success);
        }

        public void onWeiboException(WeiboException e) {
            HttpResponse response = new HttpResponse();
            response.message = e.getMessage();
            onFinish(response, false);

        }
    };

    public void get() {
        mAsyncWeiboRunner.requestAsync(url, onPrepare(), "GET", mRequestListener);
    }

    public void post() {
        mAsyncWeiboRunner.requestAsync(url, onPrepare(), "POST", mRequestListener);
    }

    public void delete() {
        mAsyncWeiboRunner.requestAsync(url, onPrepare(), "DELETE", mRequestListener);

    }

    public abstract WeiboParameters onPrepare();

    public abstract void onFinish(HttpResponse response, boolean success);
}
