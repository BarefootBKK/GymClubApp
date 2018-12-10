package com.example.gymclubapp.entity;

import android.os.AsyncTask;
import android.util.Log;

import com.example.gymclubapp.config.NetConfig;
import com.example.gymclubapp.config.ServerConfig;
import com.example.gymclubapp.interfaces.HttpListener;
import com.example.gymclubapp.util.HttpUtil;

import java.io.IOException;
import java.net.SocketTimeoutException;

import okhttp3.RequestBody;

public class NetworkTask extends AsyncTask<RequestBody, Integer, MyResponse> {

    private HttpListener listener;
    private String address;
    private int method;

    public NetworkTask(String address, int method, HttpListener listener) {
        this.address = address;
        this.listener = listener;
        this.method = method;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(MyResponse response) {
        if (response.getCode() == 200) {
            listener.onSuccess(response.getData());
        } else {
            listener.onFailure(response.getCode(), response.getData());
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected MyResponse doInBackground(RequestBody... requestBodies) {
        try {
            String jsonData;
            if (method == NetConfig.POST) {
                jsonData = HttpUtil.sendOkHttpRequestByPOST(address, requestBodies[0]).body().string();
            } else {
                jsonData = HttpUtil.sendOkHttpRequestByGET(address).body().string();
            }
            Log.d("测试", "doInBackground: " + jsonData);
            if (jsonData.isEmpty()) {
                return new MyResponse(202, getErrorMessage("数据传输出错！"));
            } else {
                return HttpUtil.getResponseData(jsonData);
            }
        } catch (SocketTimeoutException e) {
            return new MyResponse(101, "无法连接服务器：" + ServerConfig.getAddress());
        }
        catch (IOException e) {
            return new MyResponse(100, getErrorMessage(e.toString()));
        }
    }

    private String getErrorMessage(String error_msg) {
        return "{\"error_msg\": \"" + error_msg +"\"}";
    }
}
