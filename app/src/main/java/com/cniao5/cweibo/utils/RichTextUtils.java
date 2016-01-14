package com.cniao5.cweibo.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;

import com.cniao5.cweibo.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Project Name：CWeiBo
 * Describe：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，
 * 对课程与服务”吹毛求疵”般的要求，打造极致课程，是菜鸟窝不变的承诺
 * Author：<a href="http://www.cniao5.com">菜鸟窝</a>
 * Created By：yul
 * Modify：yul
 * Modify Time：7:57 PM
 * Remark：
 */
public class RichTextUtils {
    private static String regex = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]";
    private static final Pattern WEB_PATTERN = Pattern.compile(regex);
    private static final Pattern MENTION_PATTERN = Pattern.compile("@[\\u4e00-\\u9fa5a-zA-Z0-9_-]+");
    public static SpannableString getRichText(Context context,String text){
        SpannableString string = new SpannableString(text);
        if(!TextUtils.isEmpty(text)){
            final int link_color = ContextCompat.getColor(context, R.color.cw_blue);
            final int mention_color = ContextCompat.getColor(context, R.color.cw_blue);
            Matcher matcher= WEB_PATTERN.matcher(text);
            while (matcher.find()){
                final String url  = matcher.group();
                string.setSpan(new ClickableSpan() {
                    public void onClick(View widget) {
                        //LogUtils.d(url);
                    }

                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setUnderlineText(false);
                        ds.setColor(link_color);
                    }
                }, matcher.start(), matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            Matcher matcher_two= MENTION_PATTERN.matcher(text);
            while (matcher_two.find()){
                final String url  = matcher_two.group();
                string.setSpan(new ClickableSpan() {
                    public void onClick(View widget) {
                        //LogUtils.d(url);
                    }

                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setUnderlineText(false);
                        ds.setColor(mention_color);
                    }
                }, matcher_two.start(), matcher_two.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        return  string;
    }
}
