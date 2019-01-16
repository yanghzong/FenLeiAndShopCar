package com.example.demo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.demo1.adapter.RightAdapter;
import com.example.demo1.adapter.ShowAdapter;
import com.example.demo1.bean.ShopShowBean;
import com.example.demo1.fenlei.presenter.ShowPresenter;
import com.example.demo1.fenlei.view.ShowView;
import com.example.demo1.inter.Apis;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowShopActivity extends AppCompatActivity  implements ShowView {
    @BindView(R.id.rv_show)
    RecyclerView  rvShow;

    private List<ShopShowBean.DataBean> showlist=new ArrayList<>();
    private ShowAdapter showAdapter;
    private ShowPresenter showPresenter;
    private int pscid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_shop);
        ButterKnife.bind(this,this);

        //得到intent传过来的值
        Intent intent=getIntent();
        //得到的值
        pscid = intent.getIntExtra("pscid",pscid);
        //初始化p层
        initPresenter();
        //初始化adapter
        initAdapter();
    }

    private void initPresenter() {
        showPresenter = new ShowPresenter();
        showPresenter.attach(this);
        showPresenter.getShow(Apis.ShowUrl+pscid);

    }

    private void initAdapter() {
        showAdapter = new ShowAdapter(this,showlist);
        rvShow.setAdapter(showAdapter);
        rvShow.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        showAdapter.setOnClickListener(new RightAdapter.OnClickListener() {
            @Override
            public void onClick(int pscid) {
                Intent intent=new Intent(ShowShopActivity.this,XiangqingActivity.class);
                intent.putExtra("pscid",pscid);
                startActivity(intent);
            }
        });
    }


    @Override
    public void getShow(ShopShowBean shopShowBean) {
        List<ShopShowBean.DataBean> data = shopShowBean.getData();
        if (data!=null){
            showlist.clear();
            showlist.addAll(data);
            showAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showPresenter.detach();
    }
}
