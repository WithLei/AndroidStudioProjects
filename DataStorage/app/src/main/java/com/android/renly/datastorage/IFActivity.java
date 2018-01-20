package com.android.renly.datastorage;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IFActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_if);

    }

    public void onClickSave(View v) throws IOException {
        AssetManager manager = getAssets();
        InputStream is = manager.open("user1.jpg");
        FileOutputStream fos = openFileOutput("user1.jpg", Context.MODE_PRIVATE);
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len =is.read(buffer))!=-1){
            fos.write(buffer,0,len);
        }
        fos.close();
        is.close();
        Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
    }

    public void onClickRead(View v){
        ImageView iv_if_pic = findViewById(R.id.iv_if_pic);
        String filesPath = getFilesDir().getAbsolutePath();
        String imagePath = filesPath+"/user1.jpg";

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);

        iv_if_pic.setImageBitmap(bitmap);
    }
}
