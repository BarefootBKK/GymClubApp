package com.example.gymclubapp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.gymclubapp.R;
import com.example.gymclubapp.config.NetConfig;
import com.example.gymclubapp.config.ServerConfig;
import com.example.gymclubapp.entity.MyResponse;
import com.example.gymclubapp.entity.NetworkTask;
import com.example.gymclubapp.interfaces.HttpListener;
import com.example.gymclubapp.interfaces.NetConnectionListener;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {

    public static Response sendOkHttpRequestByPOST(String address, RequestBody requestBody) throws IOException{
        OkHttpClient client = new OkHttpClient();
        client.newBuilder()
                .connectTimeout(1000, TimeUnit.MILLISECONDS)
                .readTimeout(1000, TimeUnit.MILLISECONDS)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        return client.newCall(request).execute();
    }

    public static Response sendOkHttpRequestByGET(String address) throws IOException{
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(1000, TimeUnit.MILLISECONDS)
                .readTimeout(1000, TimeUnit.MILLISECONDS)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .build();
        return client.newCall(request).execute();
    }

    public static MyResponse getResponseData(String jsonData) {
        MyResponse myResponse = new MyResponse(0, "");
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            myResponse.setCode(jsonObject.getInt("code"));
            myResponse.setData(jsonObject.getString("data"));
        } catch (Exception e) { }
        return myResponse;
    }

    /**
     * 带监听接口的testConnection
     * @param context
     * @param connectionListener
     */
    public static void testConnection(final Context context, NetConnectionListener connectionListener) {
        ServerConfig.initServerConfig(context.getResources().openRawResource(R.raw.ip));
        testIP(context, 0, connectionListener);
    }

    private static void testIP(final Context context, final int position, final NetConnectionListener connectionListener) {
        String ip = ServerConfig.getIpFromList(position);
        if (ip.isEmpty()) {
            ToastUtil.showToast(context, "无法连接服务器！");
            return;
        } else {
            ServerConfig.updateIP(position);
            NetworkTask networkTask = new NetworkTask(ServerConfig.getAddress("/test"), NetConfig.GET, new HttpListener() {
                @Override
                public void onSuccess(String jsonData) {
                    ServerConfig.isConnected = true;
                    if (connectionListener != null) {
                        connectionListener.onConnected();
                    }
                    return;
                }
                @Override
                public void onFailure(int failure_code, String failure_data) {
                    if (failure_code == 101) {
                        testIP(context, position + 1, connectionListener);
                    }
                }
            });
            networkTask.execute();
        }
    }

    /**
     * 获取网络状态
     * @param context
     * @return
     */
    public static int getNetWorkState(Context context) {
         // 得到连接管理器对象
         ConnectivityManager connectivityManager = (ConnectivityManager) context .getSystemService(Context.CONNECTIVITY_SERVICE);
         NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
         if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
             if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_WIFI)) {
                return NetConfig.NETWORK_WIFI;
             } else if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_MOBILE)) {
                return NetConfig.NETWORK_MOBILE;
             }
         } else {
            return NetConfig.NETWORK_NONE;
         }
         return NetConfig.NETWORK_NONE;
    }

    /**
     * 是否已连接网络
     * @param context
     * @return
     */
    public static boolean isNetConnect(Context context) {
        if (getNetWorkState(context) < 0) {
            return false;
        } else {
            return true;
        }
    }
}
