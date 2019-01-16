package com.example.demo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.demo1.adapter.LeftAdapter;
import com.example.demo1.adapter.RightAdapter;
import com.example.demo1.bean.FirstBean;
import com.example.demo1.bean.ShopShowBean;
import com.example.demo1.bean.TwoBean;
import com.example.demo1.fenlei.presenter.FenPresenter;
import com.example.demo1.fenlei.view.FenView;
import com.example.demo1.inter.Apis;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  implements FenView {
    @BindView(R.id.rv_left)
    RecyclerView  rvLeft;
    @BindView(R.id.rv_right)
    RecyclerView  rvRight;
    private List<FirstBean.DataBean> leftlist=new ArrayList<>();
    private List<TwoBean.DataBean.ListBean> rightlist=new ArrayList<>();
    private RightAdapter rightAdapter;
    private LeftAdapter leftAdapter;
    private FenPresenter fenPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this,this);
        //初始化p层
        initPresenter();
        //初始化adapter;
        initAdapter();

    }

    private void initAdapter() {
        leftAdapter = new LeftAdapter(this,leftlist);
        rvLeft.setAdapter(leftAdapter);
        rvLeft.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        leftAdapter.setOnClickListener(new LeftAdapter.OnClickListener() {
            @Override
            public void onClick(int cid) {
                fenPresenter.getRight(Apis.TwoUrl+cid);
            }
        });
        rightAdapter = new RightAdapter(this,rightlist);
        rvRight.setAdapter(rightAdapter);
        rvRight.setLayoutManager(new GridLayoutManager(this,3));
        rightAdapter.setOnClickListener(new RightAdapter.OnClickListener() {
            @Override
            public void onClick(int pscid) {
                Intent intent = new Intent(MainActivity.this,ShowShopActivity.class);
                intent.putExtra("pscid",pscid);
                startActivity(intent);
            }
        });
    }

    private void initPresenter() {
        fenPresenter = new FenPresenter();
        fenPresenter.attach(this);
        fenPresenter.getLeft(Apis.FirstUrl);

    }

    @Override
    public void getLeft(FirstBean firstBean) {
        List<FirstBean.DataBean> data = firstBean.getData();
        if (data!=null){
            leftlist.clear();
            leftlist.addAll(data);
            leftAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void getRight(TwoBean twoBean) {
        List<TwoBean.DataBean> data = twoBean.getData();
        if (data!=null){
            rightlist.clear();
            rightlist.addAll(data.get(0).getList());
            rightAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fenPresenter.detach();
    }
}
