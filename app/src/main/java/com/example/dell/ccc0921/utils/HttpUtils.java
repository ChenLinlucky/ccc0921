package com.example.dell.ccc0921.utils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtils {
    private static HttpUtils httpUtils;
    private final OkHttpClient okHttpClient;

    public HttpUtils(){
        okHttpClient = new OkHttpClient();
    }

    public static HttpUtils getstance() {
        if(httpUtils==null){
            synchronized (HttpUtils.class){
                if(httpUtils==null){
                    httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;
    }
    public void getdta(String path, Callback callback){
        Request request = new Request.Builder().url(path).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
