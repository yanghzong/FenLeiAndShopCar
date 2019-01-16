package com.example.demo1.fenlei.model;

import com.example.demo1.inter.ICallBack;
import com.example.demo1.utils.HttpUtils;

import java.lang.reflect.Type;

public class FenModel {
    public  void  getFen(String url, ICallBack callBack, Type type){
        HttpUtils.getInistance().get(url,callBack,type);
    }
}
