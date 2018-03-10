package com.android.renly.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_volly_get;
    Button btn_volly_post;
    Button btn_volly_image;
    Button btn_volly_imageloader;
    TextView tv_volly_text;
    ImageView iv_volly_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initClick();
    }

    private void initClick() {
        btn_volly_get.setOnClickListener(this);
        btn_volly_post.setOnClickListener(this);
        btn_volly_image.setOnClickListener(this);
        btn_volly_imageloader.setOnClickListener(this);
        tv_volly_text.setOnClickListener(this);
        iv_volly_image.setOnClickListener(this);

    }

    private void initView() {
        btn_volly_get = findViewById(R.id.btn_volly_get);
        btn_volly_post = findViewById(R.id.btn_volly_post);
        btn_volly_image = findViewById(R.id.btn_volly_image);
        btn_volly_imageloader = findViewById(R.id.btn_volly_imageloader);
        tv_volly_text = findViewById(R.id.tv_volly_text);
        iv_volly_image = findViewById(R.id.iv_volly_image);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_volly_get:
                //创建一个请求队列
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                //创建一个get请求
                String url = "https://github.com/";
                tv_volly_text.setVisibility(View.VISIBLE);
                StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tv_volly_text.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tv_volly_text.setText("数据出错");
                    }
                });
                //将get请求加入队列
                requestQueue.add(stringRequest);
                break;
            case R.id.btn_volly_post:
                Toast.makeText(MainActivity.this,"post",Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_volly_image:
                Toast.makeText(MainActivity.this,"image",Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_volly_imageloader:
                Toast.makeText(MainActivity.this,"imageloader",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
