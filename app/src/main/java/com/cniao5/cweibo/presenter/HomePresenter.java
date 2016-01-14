package com.cniao5.cweibo.presenter;

import com.cniao5.cweibo.adapters.HomepageListAdapter;

/**
 * Project Name：CWeiBo
 * Describe：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，
 * 对课程与服务”吹毛求疵”般的要求，打造极致课程，是菜鸟窝不变的承诺
 * Author：<a href="http://www.cniao5.com">菜鸟窝</a>
 * Created By：yul
 * Modify：yul
 * Modify Time：9:56 PM
 * Remark：
 */
public interface HomePresenter {
    void loadData();
    void loadMore();
    void requestHomeTimeLine();
    void requestUserTimeLine();
}
