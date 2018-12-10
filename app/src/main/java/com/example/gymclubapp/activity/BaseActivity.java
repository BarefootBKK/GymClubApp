package com.example.gymclubapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.gymclubapp.R;
import com.example.gymclubapp.broadcast.NetBroadcastReceiver;
import com.example.gymclubapp.config.NetConfig;
import com.example.gymclubapp.util.ActivityFunctionUtil;
import com.example.gymclubapp.util.ToastUtil;

public class BaseActivity extends AppCompatActivity implements NetBroadcastReceiver.NetEvent {
    public static NetBroadcastReceiver.NetEvent event;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFunctionUtil.getInstance().setStatusBar(this);      // 设置为沉浸式状态栏
        this.TAG = "类-" + this.getLocalClassName();               // 打印日志
    }

    /**
     * 设置toolbar
     * @param toolbarId
     * @param showHomeAsUp
     */
    protected void setActivityToolbar(int toolbarId, boolean showHomeAsUp, boolean isDisplayToolbarTitle) {
        setSupportActionBar((Toolbar) findViewById(toolbarId));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(showHomeAsUp);
            actionBar.setDisplayShowTitleEnabled(isDisplayToolbarTitle);
        }
    }

    /**
     * 点击事件监听器
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // 搜索
            case R.id.item_search:
                ActivityFunctionUtil.toStartActivity(this, SearchActivity.class,
                        R.drawable.ic_search, "search");
                break;
            // 训练记录
            case R.id.item_record:
                ActivityFunctionUtil.toStartActivity(this, TrainingRecordActivity.class,
                        R.drawable.ic_my_records, "MyRecord");
                break;
            // 消息通知
            case R.id.item_message:
                ActivityFunctionUtil.toStartActivity(this, TempActivity.class,
                        R.drawable.ic_msg, "Message");
                break;
            // 更多课程
            case R.id.item_more_course:
                ToastUtil.showToast(this, "还没有哦!");
                break;
            // 主页
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            // 相关教练
            case R.id.item_course_detail_relativeCoach:
                ToastUtil.showToast(this, "找不到诶");
                break;
            // 获取课程
            case R.id.item_go_get_course:
                startActivity(new Intent(this, MainActivity.class));
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: " + TAG);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: " + TAG);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: " + TAG);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: " + TAG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + TAG);
    }

    /**
     * 网络变化监听器
     * @param netMobile
     */
    @Override
    public void onNetChange(int netMobile) {
        NetConfig.CURRENT_NETWORK_TYPE = netMobile;
        if (netMobile < 0) {
            NetConfig.isNetConnect = false;
        } else {
            NetConfig.isNetConnect = true;
        }
    }
}
