package com.android.renly.LiveDemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    private View LiveDialogView;
    private EditText et_roomName;

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

    private void initDialogView(){
        //初始化Dialog的视图
        LiveDialogView = View.inflate(MainActivity.this,R.layout.dialog_live,null);
        et_roomName = (EditText) LiveDialogView.findViewById(R.id.et_roomName);
    }

    private static final int GOTO_PUSHERACTIVITY = 1;
    private static final int GOTO_PLAYACTIVITY = 2;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GOTO_PUSHERACTIVITY:
                    String PusherRoomName = msg.getData().getString("RoomName");
                    Bundle pusherBundle = new Bundle();
                    pusherBundle.putString("RoomName",PusherRoomName);

                    Intent pusherIntent = new Intent(MainActivity.this,PusherActivity.class);
                    pusherIntent.putExtras(pusherBundle);
                    startActivity(pusherIntent);
                    break;
                case GOTO_PLAYACTIVITY:
                    String playRoomName = msg.getData().getString("RoomName");
                    Bundle playBundle = new Bundle();
                    playBundle.putString("RoomName",playRoomName);

                    Intent playIntent = new Intent(MainActivity.this,PlayActivity.class);
                    playIntent.putExtras(playBundle);
                    startActivity(playIntent);
                    break;
            }
        }
    };

    @OnClick({R.id.btn_main_startLive, R.id.btn_main_playLive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_main_startLive:
                initDialogView();
                new AlertDialog.Builder(this)
                        .setView(LiveDialogView)
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                gotoPusherActivity();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create()
                        .show();
                break;
            case R.id.btn_main_playLive:
                gotoPlayActivity();
                break;
        }
    }

    private void gotoPlayActivity() {
        String roomName = "哇哇哇直播间";

        Bundle bundle = new Bundle();
        bundle.putString("RoomName", roomName);

        Message msg = new Message();
        msg.setData(bundle);
        msg.what = GOTO_PLAYACTIVITY;

        handler.sendMessage(msg);
    }

    private void gotoPusherActivity(){
        String roomName = et_roomName.getText().toString();
        if (!roomName.equals("")){
            Bundle bundle = new Bundle();
            bundle.putString("RoomName", roomName);

            Message msg = new Message();
            msg.setData(bundle);
            msg.what = GOTO_PUSHERACTIVITY;

            handler.sendMessage(msg);
        }else{
            Toast.makeText(this, "直播名称不能为空", Toast.LENGTH_SHORT).show();
        }

    }
}
