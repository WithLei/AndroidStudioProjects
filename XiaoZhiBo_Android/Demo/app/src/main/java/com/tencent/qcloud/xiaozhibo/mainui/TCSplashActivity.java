package com.tencent.qcloud.xiaozhibo.mainui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import com.tencent.qcloud.xiaozhibo.login.TCLoginActivity;

import java.lang.ref.WeakReference;

/**
 * Created by RTMP on 2016/8/1
 */
public class TCSplashActivity extends Activity {

    private static final String TAG = TCSplashActivity.class.getSimpleName();

    private static final int START_LOGIN = 2873;
    private final MyHandler mHandler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isTaskRoot()
                && getIntent().hasCategory(Intent.CATEGORY_LAUNCHER)
                && getIntent().getAction() != null
                && getIntent().getAction().equals(Intent.ACTION_MAIN)) {

            finish();
            return;
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Message msg = Message.obtain();
        msg.arg1 = START_LOGIN;
        mHandler.sendMessageDelayed(msg, 1000);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        //splashActivity下不允许back键退出O
        //super.onBackPressed();
    }

    private void jumpToLoginActivity() {
        Intent intent = new Intent(this, TCLoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private static class MyHandler extends Handler {
        private final WeakReference<TCSplashActivity> mActivity;

        public MyHandler(TCSplashActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            TCSplashActivity activity = mActivity.get();
            if (activity != null) {
                activity.jumpToLoginActivity();
            }
        }
    }

}
