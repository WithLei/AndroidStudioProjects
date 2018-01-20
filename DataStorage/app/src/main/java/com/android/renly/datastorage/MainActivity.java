package com.android.renly.datastorage;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //测试SP存储
    public void onClickSP(View v){
        startActivity(new Intent(this, SpActivity.class));
    }

    public void onClickIF(View v){
        startActivity(new Intent(this,IFActivity.class));
    }

    public void onClickOF(View v){

    }

    public void onClickDB(View v){

    }

    public void onClickNW(View v){

    }
}
