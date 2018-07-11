package com.onyx.android.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by seeksky on 2018/4/26.
 */

public class ScribbleDemoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sribble_demo);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.button_scribble_touch_helper)
    public void button_scribble_touch_helper() {
        go(ScribbleStylusTouchHelperDemoActivity.class);
    }

    @OnClick(R.id.button_surfaceview_stylus_scribble)
    public void button_surfaceview_stylus_scribble() {
        go(ScribbleStylusSurfaceViewDemoActivity.class);
    }

    @OnClick(R.id.button_webview_stylus_scribble)
    public void button_webview_stylus_scribble() {
        go(ScribbleStylusWebViewDemoActivity.class);
    }

    @OnClick(R.id.button_touch_screen_scribble)
    public void button_touch_screen_scribble() {
        go(ScribbleTouchScreenDemoActivity.class);
    }

    @OnClick(R.id.button_move_erase_scribble)
    public void button_move_erase_scribble() {
        go(ScribbleStylusMoveEraserDemoActivity.class);
    }

    private void go(Class<?> activityClass) {
        startActivity(new Intent(this, activityClass));
    }
}
