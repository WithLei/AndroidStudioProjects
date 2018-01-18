package com.android.renly.phonecall;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.Manifest.permission;

import java.security.Permission;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity {

    private EditText et_main_number;
    private EditText et_main_sns;
    private Button btn_main_call;
    private Button btn_main_send;
    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v==btn_main_call){//点击呼出
//                Toast.makeText(MainActivity.this, "点击呼出", Toast.LENGTH_SHORT).show();
                //创建一个隐式意图Itent
                String action = Intent.ACTION_DIAL;
                Intent intent = new Intent(action);
                //携带数据
                String num = et_main_number.getText().toString();
                intent.setData(Uri.parse("tel:"+num));
                //startActivity(intent)
                startActivity(intent);

            }
            else if(v==btn_main_send){//点击讯息
//                Toast.makeText(MainActivity.this, "点击讯息", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                String num = et_main_number.getText().toString();
                String sms = et_main_sns.getText().toString();
                intent.setData(Uri.parse("smsto:"+num));
                intent.putExtra("sms_body",sms);
                startActivity(intent);
            }
        }
    };
    public OnLongClickListener onLongClickListener = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if(v==btn_main_call){
//                Toast.makeText(MainActivity.this, "长按呼出", Toast.LENGTH_LONG).show();
                //创建一个隐式意图Itent
                Intent intent = new Intent(Intent.ACTION_CALL);
                //携带数据
                String num = et_main_number.getText().toString();
                intent.setData(Uri.parse("tel:"+num));
                //startActivity(intent)
                startActivity(intent);//6.0及以上系统需要做适配
            }
            else if(v==btn_main_send){
                Toast.makeText(MainActivity.this,"长按讯息",Toast.LENGTH_LONG).show();
                SmsManager smsManager = SmsManager.getDefault();
                String num = et_main_number.getText().toString();
                String sms = et_main_sns.getText().toString();
                smsManager.sendTextMessage(num,null,sms,null,null);
            }
            return true;//表示此事件已被消费，不会再触发点击事件
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化视图对象
        et_main_number = findViewById(R.id.et_main_phoneNum);
        et_main_sns = findViewById(R.id.et_main_shortText);
        btn_main_call = findViewById(R.id.btn_main_call);
        btn_main_send = findViewById(R.id.btn_main_shortText);
        //给视图对象设置点击监听
        btn_main_call.setOnClickListener(onClickListener);
        btn_main_send.setOnClickListener(onClickListener);
        //给视图对象设置长按监听
        btn_main_call.setOnLongClickListener(onLongClickListener);
        btn_main_send.setOnLongClickListener(onLongClickListener);=
    }
}
