package com.android.renly.chartdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.renly.chartdemo.Activity.invertedActivity;
import com.android.renly.chartdemo.Activity.raderActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_main_inverted;
    private Button btn_main_rader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListner();
    }

    private void initView() {
        btn_main_inverted = findViewById(R.id.btn_main_inverted);
        btn_main_rader = findViewById(R.id.btn_main_rader);
    }

    private void initListner() {
        btn_main_inverted.setOnClickListener(this);
        btn_main_rader.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_main_inverted:
                startActivity(new Intent(MainActivity.this,invertedActivity.class));
                break;
            case R.id.btn_main_rader:
                startActivity(new Intent(MainActivity.this,raderActivity.class));
                break;
        }
    }
}
