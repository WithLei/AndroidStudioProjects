package com.android.renly.event;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.app.Activity;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_main_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FirstActivity.class));
                Log.e("Test","TouchActivity");
            }
        });

        findViewById(R.id.btn_main_longclick).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(new Intent(MainActivity.this,secondActivity.class));
                Log.e("Test","TouchActivity");
                return true;
            }
        });
    }

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

    public void onExitClick(View v){
        //显示确定的dialog
        new AlertDialog.Builder(this)
                .setMessage("你确定退出吗")
                .setPositiveButton("残忍的退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //退出
                        finish();
                    }
                })
                .setNegativeButton("再看看",null)
                .show();
    }
}
