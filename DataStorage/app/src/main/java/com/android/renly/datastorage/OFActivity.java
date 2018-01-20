package com.android.renly.datastorage;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 测试手机外部存储
 */
public class OFActivity extends AppCompatActivity {

    private EditText et_of_name;
    private EditText et_of_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_of);
        et_of_name = findViewById(R.id.et_of_name);
        et_of_content = findViewById(R.id.et_of_content);
    }

    //存储1
    public void onClickSave(View v) throws IOException{
         //判断sd卡状态，如果是挂载状态才继续，否则提示
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ){
            //读取输入的文件名/内容
            String fileName = et_of_name.getText().toString();
            String content = et_of_content.getText().toString();
            if(fileName.isEmpty()||content.isEmpty()){
                Toast.makeText(this,"请完善信息", Toast.LENGTH_SHORT).show();
            }else{
                //得到制定文件的OutputStream
                //得到sd卡下的files路径
                String filesPath = getExternalFilesDir(null).getAbsolutePath();
                //组成完整路径
                String filePath = filesPath+'/'+fileName;
                //创建FileOutputStream
                FileOutputStream fos = new FileOutputStream(filePath);
                //写数据
                fos.write(content.getBytes("utf-8"));
                fos.close();
                //提示
                Toast.makeText(this,"保存完成",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"SD卡没有挂载", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickRead(View v) throws IOException {
        //判断sd卡状态，如果是挂载状态才继续，否则提示
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ){
            //读取输入的文件名/内容
            String fileName = et_of_name.getText().toString();
            if(fileName.isEmpty()){
                Toast.makeText(this,"请完善信息", Toast.LENGTH_SHORT).show();
            }else{
                //得到制定文件的InputStream
                //得到sd卡下的files路径
                String filesPath = getExternalFilesDir(null).getAbsolutePath();
                //组成完整路径
                String filePath = filesPath+'/'+fileName;
                //创建FileInputStream
                FileInputStream fis = new FileInputStream(filePath);
                //写数据
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = -1;
                while((len=fis.read(buffer))!=-1){
                    baos.write(buffer,0,len);
                }
                String content = baos.toString();

                et_of_content.setText(content);
                fis.close();
            }
        }else{
            Toast.makeText(this,"SD卡没有挂载", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickSave2(View v) throws IOException{

    }
    public void onClickRead2(View v){

    }
}
