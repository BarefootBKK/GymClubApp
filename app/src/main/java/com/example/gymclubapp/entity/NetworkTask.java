package com.example.gymclubapp.entity;

import android.os.AsyncTask;
import android.util.Log;

import com.example.gymclubapp.config.ErrorCodeConfig;
import com.example.gymclubapp.config.HttpConfig;
import com.example.gymclubapp.interfaces.HttpListener;
import com.example.gymclubapp.util.HttpUtil;

import java.io.IOException;

import okhttp3.RequestBody;

public class NetworkTask extends AsyncTask<RequestBody, Integer, Integer> {

    private HttpListener listener;
    private String address;
    private int method;
    private String jsonData;

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
    protected void onPostExecute(Integer code) {
        if (code == 0) {
            listener.onMessage(jsonData);
            listener.onSuccess();
        } else {
            listener.onFailure(code);
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Integer doInBackground(RequestBody... requestBodies) {
        try {
            Log.d("测试", "doInBackground: ");
            String response;
            if (method == HttpConfig.POST) {
                response = HttpUtil.sendOkHttpRequestByPOST(address, requestBodies[0], method).body().string();
            } else {
                response = HttpUtil.sendOkHttpRequestByGET(address).body().string();
            }
            if (response.isEmpty()) {
                return ErrorCodeConfig.NET_DATA_LOSS;
            } else {
                jsonData = response;
                return 0;
            }
        } catch (IOException e) {
            return ErrorCodeConfig.SERVER_UNAVAILABLE;
        }
    }
}
