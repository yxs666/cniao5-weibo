package com.cniao5.cweibo.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.cniao5.cweibo.utils.LogUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Project Name：CWeiBo
 * Describe：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，
 * 对课程与服务”吹毛求疵”般的要求，打造极致课程，是菜鸟窝不变的承诺
 * Author：<a href="http://www.cniao5.com">菜鸟窝</a>
 * Created By：yul
 * Modify：yul
 * Modify Time：10:47 PM
 * Remark：
 */
public class PullToRefreshRecyclerView extends PullToRefreshBase<RecyclerView> {
    public PullToRefreshRecyclerView(Context context) {
        super(context);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView recyclerView = new RecyclerView(context, attrs);
        recyclerView.setId(com.handmark.pulltorefresh.library.R.id.recyclerview);
        return recyclerView;
    }

    protected boolean isReadyForPullEnd() {
        if (mRefreshableView.getChildCount() == 0) {
            return true;
        }
        int count = mRefreshableView.getChildCount();
        View view = mRefreshableView.getChildAt(count - 1);
        int position = mRefreshableView.getChildLayoutPosition(view);
        if(position >= mRefreshableView.getAdapter().getItemCount()-1)
        {
            return view.getBottom() <= mRefreshableView.getBottom();
        }
        return false;
    }

    protected boolean isReadyForPullStart() {
        if (mRefreshableView.getChildCount() == 0) {
            return true;
        }
        View view = mRefreshableView.getChildAt(0);
        int position = mRefreshableView.getChildLayoutPosition(view);
        if (position == 0) {
            return view.getTop() == mRefreshableView.getTop();
        }
        return false;
    }
    public void setLayoutManager(RecyclerView.LayoutManager manager){
        mRefreshableView.setLayoutManager(manager);
    }
    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration){
        mRefreshableView.addItemDecoration(itemDecoration);
    }
    public void setAdapter(RecyclerView.Adapter adapter){
        mRefreshableView.setAdapter(adapter);
    }
}
