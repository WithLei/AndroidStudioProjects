package com.atguigu.android.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.android.okhttp.base.BaseFragment;

/**
 * 作者：尚硅谷-杨光福 on 2016/7/21 19:27
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：第三方Fragment
 */
public class ThirdPartyFragment extends BaseFragment {


    private static final String TAG = ThirdPartyFragment.class.getSimpleName();//"CommonFrameFragment"
    private TextView textView;

    @Override
    protected View initView() {
        Log.e(TAG,"第三方Fragment页面被初始化了...");
        textView = new TextView(mContext);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG, "第三方Fragment数据被初始化了...");
        textView.setText("第三方页面");
    }
}
