package com.cniao5.cweibo.presenter;

import com.cniao5.cweibo.core.CWConstant;
import com.cniao5.cweibo.entities.HttpResponse;
import com.cniao5.cweibo.entities.StatusEntity;
import com.cniao5.cweibo.networks.BaseNetWork;
import com.cniao5.cweibo.networks.CWUrls;
import com.cniao5.cweibo.networks.ParameterKeySet;
import com.cniao5.cweibo.utils.LogUtils;
import com.cniao5.cweibo.utils.SPUtils;
import com.cniao5.cweibo.views.mvpviews.BaseView;
import com.cniao5.cweibo.views.mvpviews.HomeView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sina.weibo.sdk.net.WeiboParameters;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Project Name：CWeiBo
 * Describe：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，
 * 对课程与服务”吹毛求疵”般的要求，打造极致课程，是菜鸟窝不变的承诺
 * Author：<a href="http://www.cniao5.com">菜鸟窝</a>
 * Created By：yul
 * Modify：yul
 * Modify Time：10:01 PM
 * Remark：
 */
public class HomePresenterImp implements HomePresenter {
    private String url = CWUrls.HOME_TIME_LINE;
    private int page = 1;
    WeiboParameters mParameters;
    private SPUtils mSPUtils;
    private List<StatusEntity> mEntityList;
    private HomeView mHomeView;

    public HomePresenterImp(HomeView homeView) {
        mHomeView = homeView;
        mEntityList = new ArrayList<>();
        mSPUtils = SPUtils.getInstance(mHomeView.getActivity());
        mParameters = new WeiboParameters(CWConstant.APP_KEY);
    }

    public void loadData() {
        page = 1;
        loadData(false);
    }

    public void loadMore() {
        page++;
        loadData(true);
    }

    public void requestHomeTimeLine() {
        url = CWUrls.HOME_TIME_LINE;
        loadData();
    }

    public void requestUserTimeLine() {
        url = CWUrls.USER_TIME_LINE;
        loadData();
    }

    private void loadData(final boolean loadMore) {
        new BaseNetWork(mHomeView.getActivity(), url) {
            public WeiboParameters onPrepare() {
                mParameters.put(ParameterKeySet.AUTH_ACCESS_TOKEN, mSPUtils.getToken().getToken());
                mParameters.put(ParameterKeySet.PAGE, page);
                mParameters.put(ParameterKeySet.COUNT, 3);
                return mParameters;
            }

            public void onFinish(HttpResponse response, boolean success) {
                if (success) {
                    LogUtils.d(response.response);
                    Type type = new TypeToken<ArrayList<StatusEntity>>() {
                    }.getType();
                    List<StatusEntity> list = new Gson().fromJson(response.response, type);
                    if (!loadMore) {
                        mEntityList.clear();
                    }
                    mEntityList.addAll(list);
                    mHomeView.onSuccess(mEntityList);
                } else {
                    mHomeView.onError(response.message);
                }
            }
        }.get();

    }

}
