package com.example.demo1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo1.R;
import com.example.demo1.bean.FirstBean;

import java.util.List;

import butterknife.ButterKnife;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {
    private Context context;
    private List<FirstBean.DataBean>  list;

    public LeftAdapter(Context context, List<FirstBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView=View.inflate(context,R.layout.item_left,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final FirstBean.DataBean dataBean = list.get(i);
        viewHolder.tvLeft.setText(dataBean.getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(dataBean.getCid());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvLeft;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLeft = itemView.findViewById(R.id.tv_left);
        }
    }

    public  interface  OnClickListener{
        void  onClick(int cid);
    }
    private OnClickListener onClickListener;

    public  void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }
}
