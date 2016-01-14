package com.cniao5.cweibo.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cniao5.cweibo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name：CWeiBo
 * Describe：
 * Created By：yul
 * Modify：yul
 * Modify Time：10:24 AM
 * Remark：
 */
public class DemoActivity extends BaseActivity {
    private List<ActivityInfo> labels;
    private RecyclerView rv;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbar().hide();
        /*String path= getIntent().getStringExtra("android.intent.category.simple");
        Log.d("DemoActivity", "onCreate() returned: " + path);*/
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory("android.intent.category.simple");
        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);
        labels = new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            //取得标题，也就是 lable
           // String label =list.get(i).activityInfo;
            //Log.d("DEo", "onCreate() called with: " + "savedInstanceState = [" + label + "]");
            labels.add(list.get(i).activityInfo);

        }
        initialize();
    }

    @Override
    public int getLayoutId() {
        return R.layout.v_common_recyclerview;
    }

    private void initialize() {

        rv = (RecyclerView) findViewById(R.id.rlv);
        mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(mLinearLayoutManager);
        RecyclerView.Adapter mAdapter = new ActivityAdapter(labels,this);
        rv.setAdapter(mAdapter);
    }

    class ActivityEntity{
        public String label;
        public String name;
    }
}
class ActivityAdapter extends RecyclerView.Adapter{
    private List<ActivityInfo> mDataSet;
    private Context mContext;
    public ActivityAdapter(List<ActivityInfo> dataSet,Context context) {
        mDataSet = dataSet;
        mContext=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.v_simple_tv,parent,false);
        return new DemoHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  DemoHolder){
            ((DemoHolder) holder).tv.setText(mDataSet.get(position).loadLabel(mContext.getPackageManager()));
        }

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
    class DemoHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        public DemoHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
            tv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClassName(mContext,mDataSet.get(getAdapterPosition()).name);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
