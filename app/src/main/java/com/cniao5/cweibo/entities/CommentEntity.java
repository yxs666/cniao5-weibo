package com.cniao5.cweibo.entities;

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
public class CommentEntity {
    public String created_at;
    public long id;
    public String text;
    public String source;
    public UserEntity user;
    public String mid;
    public String idStr;
    public CommentEntity reply_comment;

}
