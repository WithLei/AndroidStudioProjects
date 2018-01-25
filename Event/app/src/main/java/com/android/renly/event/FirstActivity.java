package com.android.renly.event;

import android.annotation.SuppressLint;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity implements View.OnTouchListener{
    private ImageView iv_first_pic;
    private int lastX;
    private int lastY;
    private int maxX;
    private int maxY;
    private RelativeLayout parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        iv_first_pic = findViewById(R.id.iv_first_pic);
//        parentView = (RelativeLayout) iv_first_pic.getParent();
        //设置监听
        iv_first_pic.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int eventX = (int) event.getRawX();
        int eventY = (int) event.getRawY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //第一次记录lastX,lastY
                if(maxY==0){
                    parentView = (RelativeLayout) v.getParent();
                    maxX = parentView.getRight();
                    maxY = parentView.getBottom();
                }
                //Toast.makeText(this,maxX+"--"+maxY,Toast.LENGTH_SHORT).show();
                lastX = eventX;
                lastY = eventY;
                break;
            case MotionEvent.ACTION_MOVE:
                //计算事件的偏移
                int dx = eventX - lastX;
                int dy = eventY - lastY;
                //根据事件的偏移来一栋imageView
                int top,left,right,down;
                top = iv_first_pic.getTop() + dy;
                left = iv_first_pic.getLeft() + dx;
                right = iv_first_pic.getRight() + dx;
                down = iv_first_pic.getBottom() +dy;
                if(top<0){
                    down -= top;
                    top = 0;
                }
                if(left<0){
                    right -= left;
                    left = 0;
                }
                if(right>maxX){
                    left -= right - maxX;
                    right = maxX;
                }
                if(down>maxY){
                    top -= down - maxY;
                    down = maxY;
                }
                iv_first_pic.layout(left,top,right,down);
                lastX = eventX;
                lastY = eventY;
                break;
            default:break;
        }
        return true;
    }
}
