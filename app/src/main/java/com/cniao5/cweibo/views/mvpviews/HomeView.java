package com.cniao5.cweibo.views.mvpviews;

import android.app.Activity;

import com.cniao5.cweibo.entities.StatusEntity;

import java.util.List;

/**
 * Project Name：CWeiBo
 * Describe：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，
 * 对课程与服务”吹毛求疵”般的要求，打造极致课程，是菜鸟窝不变的承诺
 * Author：<a href="http://www.cniao5.com">菜鸟窝</a>
 * Created By：yul
 * Modify：yul
 * Modify Time：10:07 PM
 * Remark：
 */
public interface HomeView extends BaseView{
    void onSuccess(List<StatusEntity> list);
}
