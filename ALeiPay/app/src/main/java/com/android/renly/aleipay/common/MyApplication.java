package com.android.renly.aleipay.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;


/**
 * Created by Renly on 2018/3/23.
 */

public class MyApplication extends Application {

    //在整个应用执行过程中，需要提供的变量
    public static Context context;//需要使用的上下文对象
    public static Thread mainThread;//提供主线程对象
    public static int mainThreadID;//提供主线程对象的id
    public static Handler handler;//需要使用的handler

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mainThread = Thread.currentThread();
        mainThreadID = android.os.Process.myTid();

        //初始化当前的异常处理器
        CrashHandler.getInstance().init(this);
    }
}
