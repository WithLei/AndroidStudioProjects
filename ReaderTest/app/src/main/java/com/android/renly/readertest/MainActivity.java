package com.android.renly.readertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.renly.readertest.Activity.PenStylusTouchHelperDemoActivity;
import com.android.renly.readertest.Activity.ReaderDemoActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.button_reader)
    Button buttonReader;
    @Bind(R.id.button_pen_touch_helper)
    Button buttonPenTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button_reader, R.id.button_pen_touch_helper})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_reader:
                startActivity(new Intent(this, ReaderDemoActivity.class));
                break;
            case R.id.button_pen_touch_helper:
                startActivity(new Intent(this, PenStylusTouchHelperDemoActivity.class));
                break;
        }
    }
}
