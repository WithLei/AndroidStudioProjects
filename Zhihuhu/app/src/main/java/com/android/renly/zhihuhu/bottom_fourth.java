package com.android.renly.zhihuhu;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class bottom_fourth extends AppCompatActivity implements View.OnClickListener{
    //Bottom五个按钮
    private TextView tv_bottom_first;
    private TextView tv_bottom_second;
    private TextView tv_bottom_third;
    private TextView tv_bottom_fourth;
    private TextView tv_bottom_fifth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_fourth);
        //初始化底部布局
        initBottomView();
        //设置监听
        setListener();
    }
    private void setListener() {
        tv_bottom_first.setOnClickListener(this);
        tv_bottom_second.setOnClickListener(this);
        tv_bottom_third.setOnClickListener(this);
        tv_bottom_fourth.setOnClickListener(this);
        tv_bottom_fifth.setOnClickListener(this);
    }

    private void initBottomView() {
        //初始化布局
        tv_bottom_first = findViewById(R.id.tv_bottom_first);
        tv_bottom_second = findViewById(R.id.tv_bottom_second);
        tv_bottom_third = findViewById(R.id.tv_bottom_third);
        tv_bottom_fourth = findViewById(R.id.tv_bottom_fourth);
        tv_bottom_fifth = findViewById(R.id.tv_bottom_fifth);

        tv_bottom_fourth.setTextColor(Color.parseColor("#3186CF"));
        Drawable top_img = getResources().getDrawable(R.drawable.remindblue);
        top_img.setBounds(0, 0, top_img.getMinimumWidth(), top_img.getMinimumHeight());
        tv_bottom_fourth.setCompoundDrawables(null, top_img, null, null);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tv_bottom_first:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_bottom_second:
                intent = new Intent(this, bottom_second.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_bottom_third:
                intent = new Intent(this, bottom_third.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_bottom_fourth:
                break;
            case R.id.tv_bottom_fifth:
                intent = new Intent(this, activity_Login.class);
                startActivity(intent);
                break;
        }
    }
}
