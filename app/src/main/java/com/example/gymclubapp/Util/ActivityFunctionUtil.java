package com.example.gymclubapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import com.example.gymclubapp.R;
import com.example.gymclubapp.config.BasicConfig;
import com.example.gymclubapp.config.CacheConfig;

public class ActivityFunctionUtil {

    private static boolean isLogin = false;  // 是否已登录

    public static ActivityFunctionUtil getInstance() {
        return new ActivityFunctionUtil();
    }

    /**
     * 获取应用状态
     * @return
     */
    public static boolean getIsLogin() {
        return isLogin;
    }

    /**
     * 设置应用状态
     * @param isLogin
     */
    public static void setIsLogin(boolean isLogin) {
        ActivityFunctionUtil.isLogin = isLogin;
    }

    /**
     * 向TabLayout中添加label
     * @param tabLayout
     * @param labelList
     */
    public static void addLabelToTabLayout(TabLayout tabLayout, int focusIndex, String... labelList) {

        for (int i = 0; i < labelList.length; i++) {
            String label = labelList[i];
            if (i == focusIndex - 1)
                tabLayout.addTab(tabLayout.newTab().setText(label), true);
            else
                tabLayout.addTab(tabLayout.newTab().setText(label), false);
        }
    }

    /**
     * 设置为沉浸式状态栏
     */
    public void setStatusBar(Activity activity) {
        boolean useThemeStatusBarColor = false;
        boolean useStatusBarColor = true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            if (useThemeStatusBarColor) {
                activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity, R.color.colorAccent));//设置状态栏背景色
            } else {
                activity.getWindow().setStatusBarColor(Color.TRANSPARENT);//透明
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = activity.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        } else {
            Toast.makeText(activity, "低于4.4的android系统版本不存在沉浸式状态栏", Toast.LENGTH_SHORT).show();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && useStatusBarColor) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    /**
     *
     * @param context
     * @param targetClass
     * @param resId
     * @param dataArray
     */
    public static void toStartActivity(Context context, Class targetClass, int resId, String... dataArray) {
        Intent intent = new Intent(context, targetClass);
        intent.putExtra("extra_data", dataArray);
        intent.putExtra("extra_res", resId);
        context.startActivity(intent);
    }

    public static void toStartActivityByParcelable(Context context, Class targetClass, Parcelable parcelable) {
        Intent intent = new Intent(context, targetClass);
        intent.putExtra(BasicConfig.INTENT_DATA_NAME, parcelable);
        context.startActivity(intent);
    }

    public static void saveDataWithSPByBoolean(Context context, String reference_name, String key_name, boolean state) {
        SharedPreferences.Editor editor = context.getSharedPreferences(reference_name, Context.MODE_PRIVATE).edit();
        editor.putBoolean("isLogin", state);
        editor.commit();
    }

    public static Object getDataWithSP(Context context, String reference_name, String key_name, int type) {
        SharedPreferences reader = context.getSharedPreferences(reference_name, Context.MODE_PRIVATE);
        if (type == CacheConfig.SP_BOOLEAN) {
            return reader.getBoolean(key_name, false);
        } else if (type == CacheConfig.SP_STRING) {
            return reader.getString(key_name, null);
        } else if (type == CacheConfig.SP_INTEGER) {
            return reader.getInt(key_name, -1);
        } else if (type == CacheConfig.SP_FLOAT) {
            return reader.getFloat(key_name, -1);
        }
        return null;
    }
}
