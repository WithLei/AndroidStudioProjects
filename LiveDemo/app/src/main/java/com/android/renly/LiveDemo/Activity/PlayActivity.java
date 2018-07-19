package com.android.renly.LiveDemo.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.renly.LiveDemo.R;
import com.android.renly.LiveDemo.Utils.NetUtils;
import com.tencent.rtmp.TXLiveBase;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;


public class PlayActivity extends Activity {
    private TXCloudVideoView mView;
    private TXLivePlayer mLivePlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playroom);

        testCode();
        createPlayer();
        startPlay();
        adjustView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLivePlayer.stopPlay(true); // true 代表清除最后一帧画面
        mView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pausePlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumePlayer();
    }

    private void resumePlayer() {
        // 继续
        mLivePlayer.resume();
    }

    private void pausePlayer() {
        // 暂停
        mLivePlayer.pause();
    }

    private void adjustView() {
        /**
         * 画面调整
         * view：大小和位置
         *      如需修改画面的大小及位置，直接调整 step1 中添加的 “video_view” 控件的大小和位置即可。
         *
         * setRenderMode：铺满or适应
         *      可选值	                        含义
         *      RENDER_MODE_FULL_FILL_SCREEN	将图像等比例铺满整个屏幕，多余部分裁剪掉，此模式下画面不会留黑边，但可能因为部分区域被裁剪而显示不全。
         *      RENDER_MODE_ADJUST_RESOLUTION	将图像等比例缩放，适配最长边，缩放后的宽和高都不会超过显示区域，居中显示，画面可能会留有黑边。
         *
         * setRenderRotation：              画面旋转
         *      可选值	                        含义
         *      RENDER_ROTATION_PORTRAIT	    正常播放（Home 键在画面正下方）
         *      RENDER_ROTATION_LANDSCAPE	    画面顺时针旋转 270 度（Home 键在画面正左方）
         */

        // 设置填充模式
        mLivePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION);
        // 设置画面渲染方向
        mLivePlayer.setRenderRotation(TXLiveConstants.RENDER_ROTATION_LANDSCAPE);
    }

    private void startPlay() {
        /**
         * 启动播放
         *
         * 可选值	                枚举值	  含义
         * PLAY_TYPE_LIVE_RTMP	      0	      传入的 URL 为 RTMP 直播地址
         * PLAY_TYPE_LIVE_FLV	      1	      传入的 URL 为 FLV 直播地址
         * PLAY_TYPE_LIVE_RTMP_ACC	  5	      低延迟链路地址（仅适合于连麦场景）
         * PLAY_TYPE_VOD_HLS	      3	      传入的 URL 为 HLS（m3u8）播放地址
         */
        String flvUrl = NetUtils.LiveUrl;
        mLivePlayer.startPlay(flvUrl, TXLivePlayer.PLAY_TYPE_LIVE_FLV); //推荐 FLV
    }

    private void createPlayer() {
        /**
         * 创建 Player
         *
         * 视频云 SDK 中的 TXLivePlayer 模块负责实现直播播放功能，
         * 并使用 setPlayerView 接口将这它与我们刚刚添加到界面上的 video_view 控件进行关联。
         */
        //mPlayerView 即 step1 中添加的界面 view
        mView = (TXCloudVideoView) findViewById(R.id.video_view);

        //创建 player 对象
        mLivePlayer = new TXLivePlayer(this);

        //关键 player 对象与界面 view
        mLivePlayer.setPlayerView(mView);
    }

    private void testCode() {
        /**
         * 测试sdk是否连接
         */
        String sdkver = TXLiveBase.getSDKVersionStr();
        Log.d("liteavsdk", "liteav sdk version is : " + sdkver);
    }
}
