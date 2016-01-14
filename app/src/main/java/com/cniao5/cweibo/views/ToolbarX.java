package com.cniao5.cweibo.views;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.cniao5.cweibo.R;

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
public class ToolbarX {
    private Toolbar mToolbar;
    private AppCompatActivity mActivity;
    private ActionBar mActionBar;
    private RelativeLayout rlCustom;

    public ToolbarX(Toolbar toolbar, AppCompatActivity activity) {
        mToolbar = toolbar;
        rlCustom = (RelativeLayout) mToolbar.findViewById(R.id.rlCustom);
        mActivity = activity;
        mActivity.setSupportActionBar(mToolbar);
        mActionBar = mActivity.getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
    }
    public ToolbarX setTitle(String text){
        mActionBar.setTitle(text);
        return this;
    }
    public ToolbarX setSubTitle(String text){
        mActionBar.setSubtitle(text);
        return this;


    }
    public ToolbarX setTitle(int resId){
        mActionBar.setTitle(resId);
        return this;

    }
    public ToolbarX setSubTitle(int resId){
        mActionBar.setSubtitle(resId);
        return this;

    }
    public ToolbarX setNavigationOnClickListener(View.OnClickListener listener){
        mToolbar.setNavigationOnClickListener(listener);
        return this;
    }
    public ToolbarX setNavigationIcon(int resId){
        mToolbar.setNavigationIcon(resId);
        return this;
    }
    public ToolbarX setDisplayHomeAsUpEnabled(boolean show){
        mActionBar.setDisplayHomeAsUpEnabled(show);
        return this;
    }
    public ToolbarX setCustomView(View view){
        rlCustom.removeAllViews();
        rlCustom.addView(view);
        return this;
    }
    public ToolbarX hide(){
        mActionBar.hide();
        return this;
    }

}
