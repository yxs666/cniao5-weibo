package com.cniao5.cweibo.activities;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.cniao5.cweibo.R;
import com.cniao5.cweibo.entities.PicUrlsEntity;

import uk.co.senab.photoview.PhotoView;

/**
 * Project Name：CWeiBo
 * Describe：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，
 * 对课程与服务”吹毛求疵”般的要求，打造极致课程，是菜鸟窝不变的承诺
 * Author：<a href="http://www.cniao5.com">菜鸟窝</a>
 * Created By：yul
 * Modify：yul
 * Modify Time：8:46 PM
 * Remark：
 */
public class PhotoViewActivity extends BaseActivity {
    private PhotoView photoview;
    private PicUrlsEntity mPicUrlsEntity;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbar().hide();
        mPicUrlsEntity = (PicUrlsEntity) getIntent().getSerializableExtra(PicUrlsEntity.class.getSimpleName());
        initialize();
    }

    public int getLayoutId() {
        return R.layout.ac_photoview;
    }

    private void initialize() {
        photoview = (PhotoView) findViewById(R.id.photoview);
        Glide.with(this).load(mPicUrlsEntity.original_pic).asBitmap().fitCenter().into(photoview);
    }
}
