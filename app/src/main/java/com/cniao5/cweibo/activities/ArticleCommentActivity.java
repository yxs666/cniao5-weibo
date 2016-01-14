package com.cniao5.cweibo.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cniao5.cweibo.R;
import com.cniao5.cweibo.adapters.ArticleCommentAdapter;
import com.cniao5.cweibo.core.CWConstant;
import com.cniao5.cweibo.entities.CommentEntity;
import com.cniao5.cweibo.entities.HttpResponse;
import com.cniao5.cweibo.entities.StatusEntity;
import com.cniao5.cweibo.networks.BaseNetWork;
import com.cniao5.cweibo.networks.CWUrls;
import com.cniao5.cweibo.networks.ParameterKeySet;
import com.cniao5.cweibo.utils.SPUtils;
import com.cniao5.cweibo.views.PullToRefreshRecyclerView;
import com.cniao5.cweibo.views.mvpviews.ArticleCommentView;
import com.cniao5.cweibo.views.mvpviews.BaseView;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
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
 * Modify Time：5:59 PM
 * Remark：
 */
public class ArticleCommentActivity extends BaseActivity implements ArticleCommentView {
    private StatusEntity mStatusEntity;
    private PullToRefreshRecyclerView rlv;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArticleCommentAdapter mAdapter;
    private SPUtils mSPUtils;
    private List<CommentEntity> mDataSet;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbar().setTitle(R.string.title_weibo_detail);
        mStatusEntity = (StatusEntity) getIntent().getSerializableExtra(StatusEntity.class.getSimpleName());
        mSPUtils = SPUtils.getInstance(getApplicationContext());
        mDataSet = new ArrayList<>();
        initialize();
    }

    public int getLayoutId() {
        return R.layout.v_common_recyclerview;
    }

    private void initialize() {

        rlv = (PullToRefreshRecyclerView) findViewById(R.id.rlv);
        mLayoutManager = new LinearLayoutManager(this);
        rlv.setLayoutManager(mLayoutManager);
        mAdapter = new ArticleCommentAdapter(this, mStatusEntity, mDataSet);
        rlv.setAdapter(mAdapter);
        new BaseNetWork(this, CWUrls.COMMENT_SHOW) {
            public WeiboParameters onPrepare() {
                WeiboParameters parameters = new WeiboParameters(CWConstant.APP_KEY);
                parameters.put(ParameterKeySet.ID, mStatusEntity.id);
                parameters.put(ParameterKeySet.PAGE, 1);
                parameters.put(ParameterKeySet.COUNT, 10);
                parameters.put(ParameterKeySet.AUTH_ACCESS_TOKEN, mSPUtils.getToken().getToken());
                return parameters;
            }

            public void onFinish(HttpResponse response, boolean success) {
                if (success) {
                    Type type = new TypeToken<ArrayList<CommentEntity>>() {
                    }.getType();
                    JsonParser parser = new JsonParser();
                    JsonElement element = parser.parse(response.response);
                    if (element.isJsonArray()) {
                        List<CommentEntity> temp = new Gson().fromJson(element, type);
                        mDataSet.clear();
                        ;
                        mDataSet.addAll(temp);
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        }.get();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onSuccess(List<CommentEntity> commentEntity) {

    }
}
