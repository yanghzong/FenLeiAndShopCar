package com.example.demo1.fenlei.presenter;

import com.example.demo1.bean.FirstBean;
import com.example.demo1.bean.ShopShowBean;
import com.example.demo1.bean.TwoBean;
import com.example.demo1.fenlei.model.FenModel;
import com.example.demo1.fenlei.view.FenView;
import com.example.demo1.inter.ICallBack;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class FenPresenter {
    private FenModel fenModel;
    private FenView fenView;

    public  void  attach(FenView fenView){
        this.fenView=fenView;
        fenModel=new FenModel();
    }

    public void getLeft(String url){
        Type type=new TypeToken<FirstBean>(){}.getType();
        fenModel.getFen(url, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                  FirstBean firstBean= (FirstBean) obj;
                  fenView.getLeft(firstBean);
            }

            @Override
            public void onFailed(Exception e) {

            }
        },type);
    }
    public void getRight(String url){
        Type type=new TypeToken<TwoBean>(){}.getType();
        fenModel.getFen(url, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                TwoBean twoBean= (TwoBean) obj;
                fenView.getRight(twoBean);
            }

            @Override
            public void onFailed(Exception e) {

            }
        },type);
    }
    public  void detach(){
        if (fenView!=null){
            fenView=null;
        }
    }

}
