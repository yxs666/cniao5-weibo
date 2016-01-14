package com.cniao5.cweibo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Project Name：CWeiBo
 * Describe：
 * Author：yul
 * Created Time：下午3:14
 * Modify：yul
 * Modify Time：下午3:14
 * Remark：
 */
public class TimeFormatUtils {

    public static String  parseToYYMMDD(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy",Locale.ENGLISH);
        Date date = null;
        Calendar calendar= Calendar.getInstance();
        try {
            date= sdf.parse(time);
            calendar.setTime(date);
            calendar.setTimeZone(TimeZone.getDefault());
            calendar.getTimeInMillis();
           return converToSimpleStrDate(calendar.getTimeInMillis());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String converToSimpleStrDate(long date) {

        long current = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat;
        long offSet = (current - date) / 1000;
        Date dt = new Date(date);
        String returnData;
        if (offSet < 5 * 60) {
            returnData = "刚刚";
        } else if (offSet >= 5 * 60 && offSet < 60 * 60) {
            returnData = offSet / 60 + "分钟前";
        } else if (offSet >= 60 * 60 && offSet < 60 * 60 * 24) {
            returnData = (offSet / 60 / 60) + "小时前";
        } else if (offSet >= 60 * 60 * 24 && offSet < 60 * 60 * 24 * 2) {
            returnData = "昨天";
        } else if (offSet >= 60 * 60 * 24 && offSet < 60 * 60 * 24 * 30) {
            //前天
            simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
            returnData = simpleDateFormat.format(dt);
        } else if (offSet >= 60 * 60 * 24 * 30 && offSet < (60 * 60 * 24 * 30 * 365)) {
            //31天前
            simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
            returnData = simpleDateFormat.format(dt);
        } else {
            //一年前
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            returnData = simpleDateFormat.format(dt);
        }

        return returnData;
    }
}
