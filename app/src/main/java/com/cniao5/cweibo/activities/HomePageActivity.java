package com.cniao5.cweibo.activities;

import android.content.Intent;
import android.media.MediaRouter;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.cniao5.cweibo.R;
import com.cniao5.cweibo.fragments.HomeFragment;
import com.cniao5.cweibo.fragments.MessageFragment;
import com.cniao5.cweibo.fragments.ProfileFragment;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.RequestListener;

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
public class HomePageActivity extends BaseActivity {
    private FrameLayout flContainer;
    private FragmentTabHost tabHost;
    private RadioButton rbHome;
    private RadioButton rbMessage;
    private RadioButton rbProfile;
    private RadioGroup rgTab;
    private Class fragment[];
    private int menu_id = R.menu.menu_home;
    private View coutom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbar().setDisplayHomeAsUpEnabled(false).setTitle(R.string.app_name);
        fragment = new Class[]{HomeFragment.class, MessageFragment.class, ProfileFragment.class};
        initialize();
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_home_page;
    }

    private void initialize() {

        flContainer = (FrameLayout) findViewById(R.id.flContainer);
        tabHost = (FragmentTabHost) findViewById(R.id.tabHost);
        rbHome = (RadioButton) findViewById(R.id.rbHome);
        rbMessage = (RadioButton) findViewById(R.id.rbMessage);
        rbProfile = (RadioButton) findViewById(R.id.rbProfile);
        rgTab = (RadioGroup) findViewById(R.id.rgTab);
        tabHost.setup(getApplicationContext(), getSupportFragmentManager(), R.id.flContainer);
        for (int i = 0; i < fragment.length; i++) {
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(String.valueOf(i)).setIndicator(String.valueOf(i));
            tabHost.addTab(tabSpec, fragment[i], null);
        }
        tabHost.setCurrentTab(0);
        rgTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbHome:
                        tabHost.setCurrentTab(0);
                        menu_id = R.menu.menu_home;
                        break;
                    case R.id.rbMessage:
                        tabHost.setCurrentTab(1);
                        menu_id = -1;
                        break;
                    case R.id.rbProfile:
                        tabHost.setCurrentTab(2);
                        menu_id = -1;
                        break;
                }
                supportInvalidateOptionsMenu();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menu_id == -1) {
            menu.clear();
        } else {
            getMenuInflater().inflate(menu_id, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        EventBus.getDefault().post(item.getItemId());
        return true;
    }


}
