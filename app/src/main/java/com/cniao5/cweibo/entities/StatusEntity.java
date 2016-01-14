package com.cniao5.cweibo.entities;

import java.io.Serializable;
import java.util.List;

/**
 * Project Name：CWeiBo
 * Describe：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，
 * 对课程与服务”吹毛求疵”般的要求，打造极致课程，是菜鸟窝不变的承诺
 * Author：<a href="http://www.cniao5.com">菜鸟窝</a>
 * Created By：yul
 * Modify：yul
 * Modify Time：下午10:34
 * Remark：
 */
public class StatusEntity implements Serializable{

    /**
     * created_at : Fri Nov 13 22:25:03 +0800 2015
     * id : 3908844872557493
     * mid : 3908844872557493
     * idstr : 3908844872557493
     * text : 为什么有些人不愿和别人打成一片？这个回答很有道理。
     * source_allowclick : 0
     * source_type : 1
     * source : <a href="http://weibo.com/" rel="nofollow">微博 weibo.com</a>
     * favorited : false
     * truncated : false
     * in_reply_to_status_id :
     * in_reply_to_user_id :
     * in_reply_to_screen_name :
     */


    /**
     * created_at : Fri Sep 11 13:58:22 +0800 2015
     * id : 3885886929945955
     * mid : 3885886929945955
     * idstr : 3885886929945955
     * text : 好听、好看、零距离的音乐互动直播平台，没错，就是陌陌现场，亲爱的朋友们，今晚21:00我在陌陌现场等你来。
     * source_allowclick : 0
     * source_type : 1
     * source : <a href="http://app.weibo.com/t/feed/5yiHuw" rel="nofollow">iPhone 6 Plus</a>
     * favorited : false
     * truncated : false
     * in_reply_to_status_id :
     * in_reply_to_user_id :
     * in_reply_to_screen_name :
     * pic_urls : [{"thumbnail_pic":"http://ww3.sinaimg.cn/thumbnail/82059686gw1evyh1wd3eqj20ku3joh1w.jpg"}]
     * thumbnail_pic : http://ww3.sinaimg.cn/thumbnail/82059686gw1evyh1wd3eqj20ku3joh1w.jpg
     * bmiddle_pic : http://ww3.sinaimg.cn/bmiddle/82059686gw1evyh1wd3eqj20ku3joh1w.jpg
     * original_pic : http://ww3.sinaimg.cn/large/82059686gw1evyh1wd3eqj20ku3joh1w.jpg
     * geo : null
     */

    public String created_at;
    public long id;
    public String mid;
    public String idstr;
    public String text;
    public int source_allowclick;
    public int source_type;
    public String source;
    public boolean favorited;
    public boolean truncated;
    public String in_reply_to_status_id;
    public String in_reply_to_user_id;
    public String in_reply_to_screen_name;
    public String thumbnail_pic;
    public String bmiddle_pic;
    public String original_pic;
    public Object geo;
    public List<PicUrlsEntity> pic_urls;
    public UserEntity user;
    public StatusEntity retweeted_status;
    public  int  reposts_count;
    public  int  comments_count;
    public  int  attitudes_count;

    public int deleted;
}
