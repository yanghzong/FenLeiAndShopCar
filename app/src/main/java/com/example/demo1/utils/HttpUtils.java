package com.example.demo1.utils;

import android.os.Handler;

import com.example.demo1.inter.ICallBack;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtils {
    private static  volatile  HttpUtils inistance;
    private OkHttpClient client;
    private Handler handler=new Handler();

    private HttpUtils(){
        client=new OkHttpClient();
    }

    public static  HttpUtils getInistance(){
        //双重锁
        if (inistance==null){
            synchronized (HttpUtils.class){
                if (inistance==null){
                    inistance=new HttpUtils();
                }
            }
        }
        return inistance;
    }
    public  void  get(String url, final ICallBack callBack, final Type type){
        Request request=new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailed(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson=new Gson();
                final Object o = gson.fromJson(string, type);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(o);
                    }
                });
            }
        });

    }
}
