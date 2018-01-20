package com.android.renly.datastorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.logging.SocketHandler;

public class SpActivity extends AppCompatActivity {

    private EditText et_sp_key;
    private EditText et_sp_value;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);
        et_sp_key = findViewById(R.id.et_sp_key);
        et_sp_value = findViewById(R.id.et_sp_value);

        sp = getPreferences(Context.MODE_PRIVATE);
    }
    public void onClickSave(View v){
        //得到editor对象
        SharedPreferences.Editor edit = sp.edit();
        //得到输入的Key/value
        String key = et_sp_key.getText().toString();
        String value = et_sp_value.getText().toString();
        if(key.isEmpty()||value.isEmpty()){
            Toast.makeText(this,"保存失败！",Toast.LENGTH_SHORT).show();
        }
        else{
            //使用editor保存key-value
            edit.putString(key,value).commit();
            //提示
            Toast.makeText(this,"保存成功！",Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickRead(View v){
        //得到输入的key
        String key = et_sp_key.getText().toString();
        if(key.isEmpty())
            //未输入key值
            Toast.makeText(this,"请输入key值", Toast.LENGTH_SHORT).show();
        else{
            String value = sp.getString(key,null);
            if(value==null){
                Toast.makeText(this,"查询失败",Toast.LENGTH_SHORT).show();
            }else{
                et_sp_value.setText(value);
                Toast.makeText(this,key+": "+value,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
