package com.android.renly.LiveDemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.renly.LiveDemo.Activity.PlayActivity;
import com.android.renly.LiveDemo.Activity.PusherActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends Activity {
    @BindView(R.id.back_tv)
    TextView backTv;
    @BindView(R.id.back_ll)
    LinearLayout backLl;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.btn_main_startLive)
    Button btnMainStartLive;
    @BindView(R.id.btn_main_playLive)
    Button btnMainPlayLive;
    private Button btn_main_startLive, btn_main_palyLive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        backLl.setVisibility(View.GONE);
        titleTv.setText("主页");
    }

    @OnClick({R.id.btn_main_startLive, R.id.btn_main_playLive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_main_startLive:
                startActivity(new Intent(MainActivity.this, PusherActivity.class));

                break;
            case R.id.btn_main_playLive:
                startActivity(new Intent(MainActivity.this, PlayActivity.class));
                break;
        }
    }
}
