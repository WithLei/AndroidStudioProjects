package com.android.renly.aleipay.util;

import android.content.Context;
import android.os.Handler;

import com.android.renly.aleipay.common.MyApplication;


/**
 * 专门提供为处理一些UI相关的问题而创建的工具类
 */

public class UIUtils {
    public static Context getContext() {
        return MyApplication.context;
    }

    public static Handler getHandler() {
        return MyApplication.handler;
    }

    public static int getColor(int colorID) {
        return getContext().getResources().getColor(colorID);
    }

    public static String[] getStringArr(int arrID) {
        return getContext().getResources().getStringArray(arrID);
    }

    //与屏幕分辨率相关的

    public static int dp2px(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);
    }

    public static int px2dp(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }
}
