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
import com.example.demo1.bean.ShopShowBean;
import com.example.demo1.utils.StringUtils;

import java.util.List;

public class ShowAdapter  extends RecyclerView.Adapter<ShowAdapter.ViewHolder> {
    private Context context;
    private List<ShopShowBean.DataBean> list;

    public ShowAdapter(Context context, List<ShopShowBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View  itemView=View.inflate(context, R.layout.item_show_shop,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ShopShowBean.DataBean dataBean = list.get(i);
        String images = dataBean.getImages();
        String[] split = images.split("\\|");
        Glide.with(context).load(StringUtils.Https2Http(split[0])).into(viewHolder.imgShow);
        viewHolder.tvShowName.setText(dataBean.getTitle());
        viewHolder.tvPrice.setText(dataBean.getPrice()+"");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(dataBean.getPscid());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgShow;
        private final TextView tvShowName;
        private final TextView tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgShow = itemView.findViewById(R.id.img_show);
            tvShowName = itemView.findViewById(R.id.tv_showname);
            tvPrice = itemView.findViewById(R.id.tv_showprice);
        }
    }
    public  interface  OnClickListener{
        void  onClick(int pscid);
    }
    private RightAdapter.OnClickListener onClickListener;

    public  void setOnClickListener(RightAdapter.OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }
}
