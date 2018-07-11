package com.onyx.android.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.onyx.android.sample.test.OnyxTestActivity;
import com.onyx.android.sdk.api.device.epd.EpdController;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        final View view = findViewById(android.R.id.content);
        EpdController.enablePost(view, 1);
    }


    @OnClick(R.id.button_epd)
    public void button_epd() {
        go(EpdDemoActivity.class);
    }

    @OnClick(R.id.button_front_light)
    public void button_front_light() {
        go(FrontLightDemoActivity.class);
    }

    @OnClick(R.id.button_full_screen)
    public  void button_full_screen() {
        go(FullScreenDemoActivity.class);
    }

    @OnClick(R.id.button_environment)
    public void button_environment() {
        go(EnvironmentDemoActivity.class);
    }

    @OnClick(R.id.button_pen_touch_helper)
    public void button_pen_touch_helper() {
        go(PenStylusTouchHelperDemoActivity.class);
    }

    @OnClick(R.id.button_pen_webview_demo)
    public void button_pen_webview_demo() {
        go(PenStylusWebViewDemoActivity.class);
    }

    @OnClick(R.id.button_scribble_demo)
    public void button_scribble_demo() {
        go(ScribbleDemoActivity.class);
    }

    @OnClick(R.id.btn_onyx_test)
    public void btn_onyx_test() {
        go(OnyxTestActivity.class);
    }

    @OnClick(R.id.btn_dict_query)
    public void btn_dict_query(){
        go(DictionaryActivity.class);
    }
    
    @OnClick(R.id.button_reader)
    public void btn_reader() {
        go(ReaderDemoActivity.class);
    }

    private void go(Class<?> activityClass){
        startActivity(new Intent(this, activityClass));
    }
}
