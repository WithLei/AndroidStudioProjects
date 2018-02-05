package com.android.renly.zhihuhu;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class bottom_second extends AppCompatActivity {
    //实例化Activity
    private ArrayList<ImageView> imageviews;
    private TextView tv_secondbottom_ad;
    private LinearLayout ll_secondbottom_ad;
    private ViewPager vp_secondbottom_ad;
    //广告条添加数据
    //图
    private final int[] imagesid = {
            R.drawable.ad1,
            R.drawable.ad2,
            R.drawable.ad3,
            R.drawable.ad4
    };
    //文
    private final String[] texts = {
            "test1",
            "test2",
            "test3",
            "test4"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_second);
        //初始化广告条
        initAdView();
        //添加数据到imageViews
        addViews();
        //设置适配器
        vp_secondbottom_ad.setAdapter(new myAdapter());
    }

    private class myAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return imageviews.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageviews.get(position);
            container.addView(imageView);
            return imageView;
        }

        /**
         * 释放资源
         * @param container viewPager
         * @param position 要释放的位置
         * @param object 要释放的页面
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }


    private void addViews() {
        for (int i = 0; i < imagesid.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imagesid[i]);
            imageviews.add(imageView);
        }
    }

    private void initAdView() {
        vp_secondbottom_ad = findViewById(R.id.vp_secondbottom_ad);
        ll_secondbottom_ad = findViewById(R.id.ll_secondbottom_ad);
        tv_secondbottom_ad = findViewById(R.id.tv_secondbottom_ad);
    }


}