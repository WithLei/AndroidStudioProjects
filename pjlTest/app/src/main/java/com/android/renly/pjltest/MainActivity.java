package com.android.renly.pjltest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private LinearLayout loginLL;
    private EditText user,pass;
    private CheckBox checkBox;
    private Button button;
    private TextView username,password,box,loadtext;
    private boolean FLAG=true;
    private int mPro=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取组件
        loginLL=(LinearLayout)findViewById(R.id.login_11);
        user=(EditText)findViewById(R.id.editText1);
        pass=(EditText)findViewById(R.id.editText2);
        checkBox=(CheckBox)findViewById(R.id.remember);
        button=(Button)findViewById(R.id.login_btn);
        username=(TextView)findViewById(R.id.usernametxt);
        password=(TextView)findViewById(R.id.passwordtxt);
        box=(TextView)findViewById(R.id.checkboxtxt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = user.getText().toString();
                String password=pass.getText().toString();
                if(username.isEmpty())
                {
                    user.setError("用户名不能为空");
                    pass.requestFocus();
                    return;
                }
                if(password.isEmpty())
                {
                    user.setError("密码不能为空");
                    pass.requestFocus();
                    return;
                }
                loginLL.setVisibility(View.GONE);
                FLAG=true;
                Intent intent=new Intent(MainActivity.this,MainActivity2Activity.class);
                intent.putExtra("EDITNFO",username);
                startActivity(intent);
                startThread(username,password);
            }
        });

    }

    private void startThread(final String username, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(FLAG)
                {
                    mPro=addNum();

                    loginLL.setVisibility(View.VISIBLE);
                    user.setText("用户名：" + username);
                    pass.setText("密码："+password);
                    box.setText("CheckBox状态："+checkBox.isChecked());
                    Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                    FLAG=false;
                }
            }
        });

    }

    private int addNum(){
        mPro++;
        try{
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return  mPro;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

