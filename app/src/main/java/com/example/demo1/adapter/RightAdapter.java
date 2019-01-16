package com.example.demo1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.demo1.R;
import com.example.demo1.bean.TwoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RightAdapter  extends RecyclerView.Adapter<RightAdapter.ViewHolder> {
    private Context context;
    private List<TwoBean.DataBean.ListBean> list;

    public RightAdapter(Context context, List<TwoBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View  itemView=View.inflate(context, R.layout.item_right,null);
        ButterKnife.bind(this,itemView);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final TwoBean.DataBean.ListBean listBean = list.get(i);
        Glide.with(context).load(listBean.getIcon()).into(viewHolder.imgRight);
        viewHolder.tvRight.setText(listBean.getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(listBean.getPscid());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView  imgRight;
        TextView  tvRight;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRight= itemView.findViewById(R.id.tv_right);
            imgRight= itemView.findViewById(R.id.img_right);
        }
    }
    public  interface  OnClickListener{
        void  onClick(int pscid);
    }
    private OnClickListener onClickListener;

    public  void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }
}
