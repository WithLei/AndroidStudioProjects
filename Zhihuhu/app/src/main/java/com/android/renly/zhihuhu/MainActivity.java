package com.android.renly.zhihuhu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager mViewPager;// 用来放置界面切换
    private PagerAdapter mPagerAdapter;// 初始化View适配器
    private List<View> mViews = new ArrayList<View>();// 用来存放Tab01-04
    // Top三个按钮
    private TextView tv_top_follow;
    private TextView tv_top_commond;
    private TextView tv_top_trending;
    //Bottom五个按钮
    private TextView tv_bottom_first;
    private TextView tv_bottom_second;
    private TextView tv_bottom_third;
    private TextView tv_bottom_fourth;
    private TextView tv_bottom_fifth;
    private Drawable top_img;//用于动态替换的图片

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initTopView();
        initBottomView();
        initViewPage();
        initTopEvent();
        initBottomEvent();
    }

    private void initBottomEvent() {
        //底部菜单栏点击监听
        tv_bottom_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"想法按钮", Toast.LENGTH_SHORT).show();
            }
        });

        tv_bottom_third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"市场按钮", Toast.LENGTH_SHORT).show();
            }
        });

        tv_bottom_fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"消息按钮", Toast.LENGTH_SHORT).show();
            }
        });

        tv_bottom_fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,activity_Login.class);
                startActivity(intent);
            }
        });
    }

    private void initBottomView() {
        //初始化布局
        tv_bottom_first = findViewById(R.id.tv_bottom_first);
        tv_bottom_second = findViewById(R.id.tv_bottom_second);
        tv_bottom_third = findViewById(R.id.tv_bottom_third);
        tv_bottom_fourth = findViewById(R.id.tv_bottom_fourth);
        tv_bottom_fifth = findViewById(R.id.tv_bottom_fifth);

        tv_bottom_first.setTextColor(Color.parseColor("#3186CF"));
        top_img = getResources().getDrawable(R.drawable.textblue);
        top_img.setBounds(0,0,top_img.getMinimumWidth(),top_img.getMinimumHeight());
        tv_bottom_first.setCompoundDrawables(null,top_img,null,null);
    }

    private void initTopEvent() {
        tv_top_follow.setOnClickListener(this);
        tv_top_commond.setOnClickListener(this);
        tv_top_trending.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /*
            ViewPage左右滑动时
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int currentItem = mViewPager.getCurrentItem();
                switch (currentItem){
                    case 0:
                        resetImg();
                        tv_top_follow.setTextColor(Color.BLACK);
                        break;
                    case 1:
                        resetImg();
                        tv_top_commond.setTextColor(Color.BLACK);
                        break;
                    case 2:
                        resetImg();
                        tv_top_trending.setTextColor(Color.BLACK);
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViewPage() {
        // 初妈化三个布局
        LayoutInflater mLayoutInflater = LayoutInflater.from(this);
        View activity_followpage = mLayoutInflater.inflate(R.layout.activity_followpage, null);
        View activity_commondpage = mLayoutInflater.inflate(R.layout.activity_commondpage, null);
        View activity_trending = mLayoutInflater.inflate(R.layout.activity_trending, null);

        mViews.add(activity_followpage);
        mViews.add(activity_commondpage);
        mViews.add(activity_trending);

        // 适配器初始化并设置
        mPagerAdapter = new PagerAdapter() {

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(mViews.get(position));

            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mViews.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {

                return arg0 == arg1;
            }

            @Override
            public int getCount() {

                return mViews.size();
            }
        };
        mViewPager.setAdapter(mPagerAdapter);
    }

    private void initTopView() {
        mViewPager = findViewById(R.id.id_viewpage);
        // 初始化四个按钮
        tv_top_follow = findViewById(R.id.tv_top_follow);
        tv_top_commond = findViewById(R.id.tv_top_commond);
        tv_top_trending = findViewById(R.id.tv_top_trending);
    }
    //提问按钮监听
    public void onAsk(View v){
        Intent intent = new Intent(MainActivity.this,askPage.class);
//        Toast.makeText(this,"test", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    /**
     * 判断哪个要显示，及设置按钮图片
     */
    @Override
    public void onClick(View arg0) {

        switch (arg0.getId()) {
            case R.id.tv_top_follow:
                mViewPager.setCurrentItem(0);
                resetImg();
                tv_top_follow.setTextColor(Color.BLUE);
                break;
            case R.id.tv_top_commond:
                mViewPager.setCurrentItem(1);
                resetImg();
                tv_top_commond.setTextColor(Color.BLUE);
                break;
            case R.id.tv_top_trending:
                mViewPager.setCurrentItem(2);
                resetImg();
                tv_top_trending.setTextColor(Color.BLUE);
                break;
            default:
                break;
        }
    }

    /**
     * 把所有文字变暗
     */
    private void resetImg() {
        tv_top_follow.setTextColor(Color.GRAY);
        tv_top_commond.setTextColor(Color.GRAY);
        tv_top_trending.setTextColor(Color.GRAY);
    }

    /*
        退出控件
     */
    private boolean flag = false;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                flag = false;
            }
        }
    };

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //监听Back键
        if(event.getKeyCode()==KeyEvent.KEYCODE_BACK){
            if (flag){
                finish();
            }
            Toast.makeText(MainActivity.this,"再按一次退出", Toast.LENGTH_SHORT).show();
            flag = true;
            handler.sendEmptyMessageDelayed(1,2000);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
