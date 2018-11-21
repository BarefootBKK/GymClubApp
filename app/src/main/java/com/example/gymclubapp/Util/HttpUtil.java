package com.example.gymclubapp.util;

import android.util.Log;

import com.example.gymclubapp.config.HttpConfig;
import com.example.gymclubapp.config.ServerConfig;
import com.example.gymclubapp.entity.MyResponse;
import com.example.gymclubapp.entity.User;
import com.example.gymclubapp.interfaces.HttpCallbackListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.GenericArrayType;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {

    public static void sendHttpRequest(final String address, final
    HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpsURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpsURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);

                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if (listener != null) {
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();

    }

    public static Response sendOkHttpRequestByPOST(String address, RequestBody requestBody, int method) throws IOException{
        OkHttpClient client = new OkHttpClient();
        client.newBuilder()
                .connectTimeout(100, TimeUnit.MILLISECONDS)
                .readTimeout(100, TimeUnit.MILLISECONDS)
                .build();
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        return client.newCall(request).execute();
    }

    public static Response sendOkHttpRequestByGET(String address) throws IOException{
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.MILLISECONDS)
                .readTimeout(100, TimeUnit.MILLISECONDS)
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
}
