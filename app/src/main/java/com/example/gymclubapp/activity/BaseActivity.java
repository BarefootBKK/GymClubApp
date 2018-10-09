package com.example.gymclubapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentController;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymclubapp.R;
import com.example.gymclubapp.util.ActivityFunctionUtil;

public class BaseActivity extends AppCompatActivity {

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_search:
                ActivityFunctionUtil.toStartActivity(this, SearchActivity.class,
                        R.drawable.ic_search, "search");
                break;
            case R.id.item_record:
                ActivityFunctionUtil.toStartActivity(this, TrainingRecordActivity.class,
                        R.drawable.ic_my_records, "MyRecord");
                break;
            case R.id.item_message:
                ActivityFunctionUtil.toStartActivity(this, TempActivity.class,
                        R.drawable.ic_msg, "Message");
                break;
            case R.id.item_more_course:
                ActivityFunctionUtil.toStartActivity(this, MoreCourseActivity.class, -1, "");
                break;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            // 相关教练
            case R.id.item_course_detail_relativeCoach:
                if (((TextView) findViewById(R.id.headingCourseDetails))
                        .getText().equals("瑜伽塑身")) {
                    ActivityFunctionUtil.toStartActivity(this, CoachDetailActivity.class, -1, "");
                    this.finish();
                } else {
                    Toast.makeText(this, "找不到欸", Toast.LENGTH_SHORT).show();
                }
                break;
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
}
