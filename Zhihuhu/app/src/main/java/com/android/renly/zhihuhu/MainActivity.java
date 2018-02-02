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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

    protected static final int WHAT_REQUEST_SUCCESS = 1;
    protected static final int WHAT_REQUEST_ERROR = 2;
    private ListView lv_main_item;
    private LinearLayout ll_main_loading;
    private List<itemInfo> data;
    private itemInfoAdapter adapter;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case WHAT_REQUEST_SUCCESS:
                    ll_main_loading.setVisibility(View.GONE);
                    //显示列表
                    lv_main_item.setAdapter(adapter);
                    break;
                case WHAT_REQUEST_ERROR:
                    ll_main_loading.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "加载数据失败", Toast.LENGTH_LONG).show();
                    break;

                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initTopView();
        initBottomView();
        initItemView();
        initViewPage();
        initTopEvent();
        initBottomEvent();
    }

    private void initItemView() {
        lv_main_item = findViewById(R.id.lv_main_item);
        ll_main_loading = findViewById(R.id.ll_main_loading);
        itemInfoAdapter adapter = new itemInfoAdapter();

        //1. 主线程, 显示提示视图
        ll_main_loading.setVisibility(View.VISIBLE);
        //2. 分线程, 联网请求
        //启动分线程请求服务器动态加载数据并显示
        new Thread() {
            public void run() {
                //联网请求得到jsonString
                try {
                    String jsonString = requestJson();
                    //解析成List<ShopInfo>
                    data = new Gson().fromJson(jsonString, new TypeToken<List<itemInfo>>() {
                    }.getType());
                    //3. 主线程, 更新界面
                    handler.sendEmptyMessage(WHAT_REQUEST_SUCCESS);//发请求成功的消息
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(WHAT_REQUEST_ERROR);//发送请求失败的消息
                }
            }
        }.start();
    }

    private String requestJson() throws Exception {
        String result = null;
        String path = "http://172.20.10.6:8080/L05_Web/ShopInfoListServlet";
        //1. 得到连接对象
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //2. 设置
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        //连接
        connection.connect();
        //发请求并读取服务器返回的数据
        int responseCode = connection.getResponseCode();
        if(responseCode==200) {
            InputStream is = connection.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            connection.disconnect();

            result = baos.toString();
        } else {
            //也可以抛出运行时异常
        }
        return result;
    }

    class itemInfoAdapter extends BaseAdapter{

        private ImageLoader imageLoader;

        public itemInfoAdapter() {
            imageLoader = new ImageLoader(MainActivity.this,R.drawable.loading,R.drawable.error);
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null) {
                convertView = View.inflate(MainActivity.this, R.layout.item_main, null);
            }
            //得到当前行的数据对象
            itemInfo itemInfo = data.get(position);
            //得到当前的子view
            ImageView iv_item_headphoto = convertView.findViewById(R.id.iv_item_headphoto);
            TextView tv_item_name = convertView.findViewById(R.id.tv_item_name);
            TextView tv_item_action = convertView.findViewById(R.id.tv_item_action);
            TextView tv_item_time = convertView.findViewById(R.id.tv_item_time);
            TextView tv_item_title = convertView.findViewById(R.id.tv_item_title);
            TextView tv_item_content = convertView.findViewById(R.id.tv_item_content);
            TextView tv_item_agreeCount = convertView.findViewById(R.id.tv_item_agreeCount);
            TextView tv_item_commentCount = convertView.findViewById(R.id.tv_item_commentCount);
            //设置数据
            tv_item_name.setText(itemInfo.getUsername());
            tv_item_action.setText(itemInfo.getAction());
            tv_item_time.setText(itemInfo.getTime());
            tv_item_title.setText(itemInfo.getTitle());
            tv_item_content.setText(itemInfo.getContent());
            tv_item_agreeCount.setText(itemInfo.getAgreeCount());
            tv_item_commentCount.setText(itemInfo.getCommentCount());
            String imagePath = itemInfo.getHeadphoto();
            //根据图片路径启动分线程动态请求服务加载图片并显示
            imageLoader.loadImage(imagePath,iv_item_headphoto);

            return convertView;
        }
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
