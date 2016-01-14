package com.cniao5.cweibo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cniao5.cweibo.R;
import com.cniao5.cweibo.activities.ArticleCommentActivity;
import com.cniao5.cweibo.core.CWConstant;
import com.cniao5.cweibo.adapters.HomepageListAdapter;
import com.cniao5.cweibo.networks.ParameterKeySet;
import com.cniao5.cweibo.entities.HttpResponse;
import com.cniao5.cweibo.entities.StatusEntity;
import com.cniao5.cweibo.networks.BaseNetWork;
import com.cniao5.cweibo.networks.CWUrls;
import com.cniao5.cweibo.presenter.HomePresenter;
import com.cniao5.cweibo.presenter.HomePresenterImp;
import com.cniao5.cweibo.utils.DividerItemDecoration;
import com.cniao5.cweibo.utils.LogUtils;
import com.cniao5.cweibo.utils.SPUtils;
import com.cniao5.cweibo.views.PullToRefreshRecyclerView;
import com.cniao5.cweibo.views.mvpviews.BaseView;
import com.cniao5.cweibo.views.mvpviews.HomeView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.sina.weibo.sdk.net.WeiboParameters;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Project Name：CWeiBo
 * Describe：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，
 * 对课程与服务”吹毛求疵”般的要求，打造极致课程，是菜鸟窝不变的承诺
 * Author：<a href="http://www.cniao5.com">菜鸟窝</a>
 * Created By：yul
 * Modify：yul
 * Modify Time：下午10:48
 * Remark：
 */
public class HomeFragment extends BaseFragment implements HomeView{
    private PullToRefreshRecyclerView rlv;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.ItemDecoration mItemDecoration;
    private HomePresenter mPresenter;
    HomepageListAdapter mListAdapter;
    List<StatusEntity> mList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mPresenter = new HomePresenterImp(this);
        mList = new ArrayList<>();
        mListAdapter = new HomepageListAdapter(mList,getActivity());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rlv = (PullToRefreshRecyclerView) inflater.inflate(R.layout.v_common_recyclerview, container, false);
        init();
        mPresenter.loadData();
        return rlv;
    }

    private void init() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        rlv.getRefreshableView().setLayoutManager(mLayoutManager);
        mItemDecoration = new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        rlv.addItemDecoration(mItemDecoration);
        rlv.setAdapter(mListAdapter);
        rlv.setMode(PullToRefreshBase.Mode.BOTH);
        rlv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                mPresenter.loadData();
            }

            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                mPresenter.loadMore();
            }
        });
        mListAdapter.setOnItemClickListener(new HomepageListAdapter.OnItemClickListener() {
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getActivity(), ArticleCommentActivity.class);
                intent.putExtra(StatusEntity.class.getSimpleName(),mList.get(position));
                startActivity(intent);

            }
        });
    }


    public void onEventMainThread(Object event) {
        if(event instanceof Integer){
            int id  = (int) event;
            switch (id){
                case R.id.action_one:
                    mPresenter.requestHomeTimeLine();
                    break;
                case R.id.action_two:
                    mPresenter.requestUserTimeLine();
                    break;
            }
        }

    }

    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onSuccess(List<StatusEntity> list) {
        rlv.onRefreshComplete();
        mList.clear();;
        mList.addAll(list);
        mListAdapter.notifyDataSetChanged();
    }

    public void onError(String error) {
        rlv.onRefreshComplete();
        Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
    }
}
