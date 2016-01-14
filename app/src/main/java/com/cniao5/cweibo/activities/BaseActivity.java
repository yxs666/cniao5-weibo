package com.cniao5.cweibo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.cniao5.cweibo.R;
import com.cniao5.cweibo.views.ToolbarX;

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
public abstract class BaseActivity extends AppCompatActivity {

    private RelativeLayout rlContent;
    private Toolbar toolbar;
    private ToolbarX mToolbarX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_baselayout);
        initialize();
        View v= getLayoutInflater().inflate(getLayoutId(), rlContent, false);
        rlContent.addView(v);
        mToolbarX = new ToolbarX(toolbar,this);
    }
    public  abstract  int getLayoutId();

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.anmi_in_right_left,R.anim.anmi_out_right_left);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_in_left_right,R.anim.anit_out_left_right);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        overridePendingTransition(R.anim.anmi_in_right_left, R.anim.anmi_out_right_left);
    }

    private void initialize() {

        rlContent = (RelativeLayout) findViewById(R.id.rlContent);
        toolbar = (Toolbar) findViewById(R.id.toolbar);


    }
    public  ToolbarX getToolbar(){
        if(null==mToolbarX){
            mToolbarX = new ToolbarX(toolbar,this);
        }
        return mToolbarX;
    }
}
