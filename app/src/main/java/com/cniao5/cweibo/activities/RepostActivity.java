package com.cniao5.cweibo.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.cniao5.cweibo.R;
import com.cniao5.cweibo.core.CWConstant;
import com.cniao5.cweibo.entities.HttpResponse;
import com.cniao5.cweibo.networks.BaseNetWork;
import com.cniao5.cweibo.networks.CWUrls;
import com.cniao5.cweibo.networks.ParameterKeySet;
import com.cniao5.cweibo.utils.LogUtils;
import com.cniao5.cweibo.utils.RichTextUtils;
import com.cniao5.cweibo.utils.SPUtils;
import com.sina.weibo.sdk.net.WeiboParameters;

import de.greenrobot.event.EventBus;

/**
 * Project Name：CWeiBo
 * Describe：
 * 菜鸟窝是一个只专注做Android开发技能的在线学习平台，课程以实战项目为主，
 * 对课程与服务”吹毛求疵”般的要求，打造极致课程，是菜鸟窝不变的承诺
 * Author：<a href="http://www.cniao5.com">菜鸟窝</a>
 * Created By：yul
 * Modify：yul
 * Modify Time：1:27 PM
 * Remark：
 */
public class RepostActivity extends BaseActivity {

    private EditText etContent;
    private long id;
    private String content;
    private String action;
    private String url;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getLongExtra(ParameterKeySet.ID, 0);
        action = getIntent().getAction();
        switch (action) {
            case "COMMENT":
                getToolbar().setTitle(R.string.lbl_comment);
                url =CWUrls.COMMENT_CREATE;
                break;
            case "REPOST":
                getToolbar().setTitle(R.string.lbl_repost);
                content = getIntent().getStringExtra(ParameterKeySet.STATUS);
                url =CWUrls.STATUS_REPOST;
                break;
        }
        initialize();
    }

    public int getLayoutId() {
        return R.layout.ac_repost;
    }

    private void initialize() {

        etContent = (EditText) findViewById(R.id.etContent);
        if (!TextUtils.isEmpty(content)) {
            etContent.setText(RichTextUtils.getRichText(getApplicationContext(),"//"+ content));

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_repost, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        post(etContent.getText().toString());
        return true;
    }

    private void post(final String string) {
        new BaseNetWork(getApplicationContext(),url) {
            public WeiboParameters onPrepare() {
                WeiboParameters weiboParameters = new WeiboParameters(CWConstant.APP_KEY);
                weiboParameters.put(ParameterKeySet.AUTH_ACCESS_TOKEN, SPUtils.getInstance(getApplicationContext())
                        .getToken().getToken());
                if(action.equals("REPOST")){
                    weiboParameters.put(ParameterKeySet.STATUS,string);

                }
                else {
                    weiboParameters.put(ParameterKeySet.COMMENT,  string);

                }
                weiboParameters.put(ParameterKeySet.ID, id);
                return weiboParameters;
            }

            public void onFinish(HttpResponse response, boolean success) {
                if (success) {
                    LogUtils.o("RepostActivity", response.response);
                    EventBus.getDefault().post("onFinish");
                    finish();
                }
            }
        }.post();
    }
}
