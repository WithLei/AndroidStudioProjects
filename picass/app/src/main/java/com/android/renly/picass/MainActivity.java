package com.android.renly.picass;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_main_first;
    Button btn_main_second;
    Button btn_main_third;
    ImageView iv_main_first;
    ImageView iv_main_second;
    ImageView iv_main_third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_main_first:

                break;
            case R.id.btn_main_second:
                break;
            case R.id.btn_main_third:
                break;
        }
    }
}
