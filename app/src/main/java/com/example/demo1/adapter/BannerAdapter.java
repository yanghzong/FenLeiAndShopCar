package com.example.demo1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.demo1.bean.ShopShowBean;
import com.example.demo1.utils.StringUtils;

import java.util.List;

public class BannerAdapter extends PagerAdapter {
     private Context context;
     private List<ShopShowBean.DataBean> showlist;

    public BannerAdapter(Context context, List<ShopShowBean.DataBean> showlist) {
        this.context = context;
        this.showlist = showlist;
    }

    @Override
    public int getCount() {
        return showlist.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView img = new ImageView(context);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        String images = showlist.get(position).getImages();
        String[] split = images.split("\\|");
        Glide.with(context).load(StringUtils.Https2Http(split[0])).into(img);
        container.addView(img);
        return img;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
