package com.android.renly.picass;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_main_first;
    private Button btn_main_second;
    private Button btn_main_third;
    private ImageView iv_main_first;
    private ImageView iv_main_second;
    private ImageView iv_main_third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitView();

    }

    private void InitView() {
        btn_main_first = findViewById(R.id.btn_main_first);
        btn_main_first.setOnClickListener(this);
        btn_main_second = findViewById(R.id.btn_main_second);
        btn_main_second.setOnClickListener(this);
        btn_main_third = findViewById(R.id.btn_main_third);
        iv_main_first = findViewById(R.id.iv_main_first);
        iv_main_second = findViewById(R.id.iv_main_second);
        iv_main_third = findViewById(R.id.iv_main_third);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_main_first:
                //基本用法
                Toast.makeText(this,"test...",Toast.LENGTH_LONG).show();
                //普通加载图片
                Picasso.with(MainActivity.this)
                        .load("https://www.baidu.com/img/baidu_jgylogo3.gif")
                        .into(iv_main_first);
                //裁剪的方式加载图片
                Picasso.with(MainActivity.this)
                        .load("https://i0.hdslb.com/bfs/archive/aed2a8893a08f0ed8f9638023b4d2639ec3b064c.jpg")
                        .resize(100,100)
                        .into(iv_main_second);
                //旋转180度
                Picasso.with(MainActivity.this)
                        .load("https://i0.hdslb.com/bfs/archive/aed2a8893a08f0ed8f9638023b4d2639ec3b064c.jpg")
                        .rotate(180)
                        .into(iv_main_third);

//                Intent intent = new Intent();
//                startActivity(intent);
                break;
            case R.id.btn_main_second:
                Intent intent = new Intent(MainActivity.this,ListviewPicasso.class);
                startActivity(intent);
                break;
            case R.id.btn_main_third:
                break;
        }
    }
}
