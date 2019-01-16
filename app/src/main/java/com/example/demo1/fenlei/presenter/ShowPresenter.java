package com.example.demo1.fenlei.presenter;

import com.example.demo1.bean.ShopShowBean;
import com.example.demo1.fenlei.model.FenModel;
import com.example.demo1.fenlei.view.ShowView;
import com.example.demo1.inter.ICallBack;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ShowPresenter {

    private FenModel fenModel;
    private ShowView showView;

    public  void  attach(ShowView showView){
        this.showView=showView;
        fenModel=new FenModel();
    }
    public void getShow(String url){
        Type type=new TypeToken<ShopShowBean>(){}.getType();
        fenModel.getFen(url, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                ShopShowBean shopShowBean= (ShopShowBean) obj;
                showView.getShow(shopShowBean);
            }

            @Override
            public void onFailed(Exception e) {

            }
        },type);
    }
    public  void detach(){
        if (showView!=null){
            showView=null;
        }
    }
}
