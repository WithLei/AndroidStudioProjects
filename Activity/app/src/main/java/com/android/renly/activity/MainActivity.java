package com.android.renly.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {

    private EditText et_main_message;
    private Button btn_main_start1;
    private Button btn_main_start2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化识图对象
        et_main_message = findViewById(R.id.et_main_message);
        btn_main_start1 = findViewById(R.id.btn_main_start1);
        btn_main_start2 = findViewById(R.id.btn_main_start2);
        //设置点击监听
        btn_main_start1.setOnClickListener(this);
        btn_main_start2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {//v就是发生事件的视图对象
        if(v==btn_main_start1){
//            Toast.makeText(this,"一般启动",Toast.LENGTH_LONG).show();
//            1)创建Intent对象（显式）
            Intent intent = new Intent(this,SecondActivity.class);
//            2)启动Activity
            startActivity(intent);
        }
        else if(v==btn_main_start2){
            Toast.makeText(this,"回调启动",Toast.LENGTH_SHORT).show();
        }
    }
}
