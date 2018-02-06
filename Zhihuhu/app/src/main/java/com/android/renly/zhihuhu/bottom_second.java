package com.android.renly.zhihuhu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnAdapterChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class bottom_second extends AppCompatActivity implements View.OnClickListener{
    //实例化Activity
    private ArrayList<ImageView> imageViews;
    private TextView tv_secondbottom_ad;
    private ViewPager vp_secondbottom_ad;
    private LinearLayout ll_secondbottom_adpoint;

    //Bottom五个按钮
    private TextView tv_bottom_first;
    private TextView tv_bottom_second;
    private TextView tv_bottom_third;
    private TextView tv_bottom_fourth;
    private TextView tv_bottom_fifth;

    private int prePosition = 0;

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
            "弹琴三十年",
            "跳槽去新加坡",
            "玩转暑假长假",
            "合格父母必修课"
    };

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int item = vp_secondbottom_ad.getCurrentItem()+1;
            vp_secondbottom_ad.setCurrentItem(item);

            //延迟发消息
            handler.sendEmptyMessageDelayed(0,4000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_second);
        //初始化广告条
        initAdView();
        //初始化底部菜单
        initBottomView();
        //设置监听
        setListener();
        //添加数据到imageViews
        addViews();
        //设置适配器
        vp_secondbottom_ad.setAdapter(new myAdapter());
        //设置监听ViewPager页面的改变
        vp_secondbottom_ad.addOnPageChangeListener(new MyOnPageChangeListener());
        //设置中间位置
        vp_secondbottom_ad.setCurrentItem(imageViews.size()*2000);
        tv_secondbottom_ad.setText(texts[prePosition]);

        //发消息
        handler.sendEmptyMessageDelayed(0,3000);
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        /**
         * 当页面滚动了的时候回调方法
         * @param position 当前页面位置
         * @param positionOffset 滑动页面百分比
         * @param positionOffsetPixels 在屏幕上滑动的像素
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**
         * 当某个页面被选中了的时候回调
         * @param position 被选中页面的位置
         */
        @Override
        public void onPageSelected(int position) {
            int realPos = position % imageViews.size();
            //设置对应页面的文本信息
            tv_secondbottom_ad.setText(texts[realPos]);
            //把上一个高亮的设置默认-灰色
            ll_secondbottom_adpoint.getChildAt(prePosition).setEnabled(false);
            //当前的设置为高亮-红色
            ll_secondbottom_adpoint.getChildAt(realPos).setEnabled(true);

            prePosition = realPos;


        }

        /**
         * 当页面滚动状态变化的时候此方法
         * 静止->滑动
         * 滑动->静止
         * 静止->拖拽
         * @param state
         */
        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state){
                case ViewPager.SCROLL_STATE_DRAGGING:
                    Log.e("TAG","SCROLL_STATE_DRAGGING");
                    handler.removeCallbacksAndMessages(null);
                    break;
                case ViewPager.SCROLL_STATE_IDLE:
                    Log.e("TAG","SCROLL_STATE_IDLE");

                    break;
                case ViewPager.SCROLL_STATE_SETTLING:
                    Log.e("TAG","SCROLL_STATE_SETTLING");
                    handler.removeCallbacksAndMessages(null);
                    handler.sendEmptyMessageDelayed(0,4000);
                    break;
            }
        }
    }

    private void setListener() {
        tv_bottom_first.setOnClickListener(this);
        tv_bottom_third.setOnClickListener(this);
        tv_bottom_fourth.setOnClickListener(this);
        tv_bottom_fifth.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.tv_bottom_first:
                intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_bottom_third:
                Toast.makeText(this,"市场按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_bottom_fourth:
                Toast.makeText(this,"消息按钮", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_bottom_fifth:
                intent = new Intent(this,activity_Login.class);
                startActivity(intent);
                break;
        }
    }

    private void initBottomView() {
        //初始化布局
        tv_bottom_first = findViewById(R.id.tv_bottom_first);
        tv_bottom_second = findViewById(R.id.tv_bottom_second);
        tv_bottom_third = findViewById(R.id.tv_bottom_third);
        tv_bottom_fourth = findViewById(R.id.tv_bottom_fourth);
        tv_bottom_fifth = findViewById(R.id.tv_bottom_fifth);

        tv_bottom_second.setTextColor(Color.parseColor("#3186CF"));
        Drawable top_img = getResources().getDrawable(R.drawable.lightsblue);
        top_img.setBounds(0,0, top_img.getMinimumWidth(), top_img.getMinimumHeight());
        tv_bottom_second.setCompoundDrawables(null, top_img,null,null);
    }

    private class myAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int realPos = position % imageViews.size();
            ImageView imageView = imageViews.get(realPos);
            container.addView(imageView);

            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
//                            Log.e("TAG","down");
                            handler.removeCallbacksAndMessages(null);
                            break;
                        case MotionEvent.ACTION_MOVE:
                            break;
                        case MotionEvent.ACTION_CANCEL:
//                            Log.e("TAG","cancel");
                            break;
                        case MotionEvent.ACTION_UP:
//                            Log.e("TAG","up");
                            handler.removeCallbacksAndMessages(null);
                            handler.sendEmptyMessageDelayed(0, 3000);
                            break;
                    }
                    return true;
                }
            });
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
            imageViews.add(imageView);

            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_selector);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(8,8);

            if(i==0){
                point.setEnabled(true);//显示红色
            }else{
                point.setEnabled(false);//显示灰色
                params.leftMargin = 8;
            }
            point.setLayoutParams(params);
            ll_secondbottom_adpoint.addView(point);
        }
    }

    private void initAdView() {
        vp_secondbottom_ad = findViewById(R.id.vp_secondbottom_ad);
        tv_secondbottom_ad = findViewById(R.id.tv_secondbottom_ad);
        ll_secondbottom_adpoint = findViewById(R.id.ll_secondbottom_adpoint);
        //初始化inamgeViews!!!
        imageViews = new ArrayList<>();
    }

}