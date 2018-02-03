package com.android.renly.zhihuhu;

import android.content.Context;
import android.graphics.Color;
import android.nfc.Tag;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.mediation.OnContextChangedListener;

public class askPage extends AppCompatActivity {
    private TextView tv_askpage_cancel;
    private TextView tv_askpage_next;
    private EditText et_askpage_askContent;
    private CharSequence temp;    // 监听前的文本
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ask_page);
        //设置ID
        initView();
        //设置监听
        setListener();

    }

    private void initView() {
        //取消按钮
        tv_askpage_cancel = findViewById(R.id.tv_askpage_cancel);
        //下一步按钮
        tv_askpage_next = findViewById(R.id.tv_askpage_next);
        //文本
        et_askpage_askContent = findViewById(R.id.et_askpage_askContent);


    }

    private void setListener() {
        //取消按钮监听
        tv_askpage_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //按钮起按监听
        tv_askpage_cancel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:
                        tv_askpage_cancel.setTextColor(Color.parseColor("#289DDC"));
                        break;
                    case MotionEvent.ACTION_DOWN:
                        tv_askpage_cancel.setTextColor(Color.GRAY);
                        break;
                    default:break;
                }
                return false;
            }
        });
        //下一步按钮监听
        tv_askpage_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_askpage_next.isClickable()){
                    //提交数据

                    //完成提交
                    finish();
                    Toast.makeText(askPage.this,"已提交数据",Toast.LENGTH_LONG).show();
                }
            }
        });
        tv_askpage_next.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:
                        tv_askpage_next.setTextColor(Color.parseColor("#289DDC"));
                        break;
                    case MotionEvent.ACTION_DOWN:
                        tv_askpage_next.setTextColor(Color.GRAY);
                        break;
                    default:break;
                }
                return false;
            }
        });
        tv_askpage_next.setClickable(false);
        //文本监听
        et_askpage_askContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(temp.length()>0){
                    tv_askpage_next.setClickable(true);
                    tv_askpage_next.setTextColor(Color.BLUE);
                }
                if(temp.length()==0){
                    tv_askpage_next.setClickable(false);
                    tv_askpage_next.setTextColor(Color.GRAY);
                }
            }
        });

    }

}
