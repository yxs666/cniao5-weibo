package com.cniao5.cweibo.networks;

/**
 * Project Name：CWeiBo
 * Describe：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，
 * 对课程与服务”吹毛求疵”般的要求，打造极致课程，是菜鸟窝不变的承诺
 * Author：<a href="http://www.cniao5.com">菜鸟窝</a>
 * Created By：yul
 * Modify：yul
 * Modify Time：8:57 PM
 * Remark：
 */
public class CWUrls {
    private static final String PREFIX ="https://api.weibo.com/2/";
    public static final String HOME_TIME_LINE =PREFIX+"statuses/home_timeline.json";
    public static final String USER_TIME_LINE =PREFIX+"statuses/user_timeline.json";
    public static final String STATUS_REPOST =PREFIX+"statuses/repost.json";
    public static final String COMMENT_CREATE =PREFIX+"comments/create.json";
    public static final String COMMENT_SHOW =PREFIX+"comments/show.json";



}
