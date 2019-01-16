package com.example.demo1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.demo1.adapter.BannerAdapter;
import com.example.demo1.bean.ShopShowBean;
import com.example.demo1.fenlei.presenter.ShowPresenter;
import com.example.demo1.fenlei.view.ShowView;
import com.example.demo1.inter.Apis;
import com.example.demo1.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XiangqingActivity extends AppCompatActivity  implements ShowView {

    @BindView(R.id.vp_banner)
    ViewPager  vpBanner;
    @BindView(R.id.tv_xiangqingname)
    TextView  tvXiangqingName;
    @BindView(R.id.tv_xiangqingprice)
    TextView  tvXiangqingPrice;
    private ShowPresenter showPresenter;
    private int pscid;
    private List<ShopShowBean.DataBean> showlist=new ArrayList<>();
    private BannerAdapter bannerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        ButterKnife.bind(this,this);

        //得到intent传过来的值
        Intent intent=getIntent();
        //得到的值
        pscid = intent.getIntExtra("pscid",pscid);

        bannerAdapter = new BannerAdapter(this,showlist);

        //初始化p层
        showPresenter = new ShowPresenter();
        showPresenter.attach(this);
        showPresenter.getShow(Apis.ShowUrl+pscid);
    }

    @Override
    public void getShow(ShopShowBean shopShowBean) {

        final List<ShopShowBean.DataBean> data = shopShowBean.getData();
        final ShopShowBean.DataBean dataBean = data.get(0);
        vpBanner.setAdapter(bannerAdapter);
        tvXiangqingName.setText(dataBean.getTitle());
        tvXiangqingPrice.setText(dataBean.getPrice());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showPresenter.detach();
    }
}
