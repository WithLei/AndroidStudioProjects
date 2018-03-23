package com.android.renly.aleipay.common;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Renly on 2018/3/23.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler crashHandler = null;
    private Context mContext;
    private Thread.UncaughtExceptionHandler defaulUncaughtExceptionHandler;

    private CrashHandler(){}
    //懒汉式实现单例

    public static CrashHandler getInstance(){
        if(crashHandler == null){
            crashHandler = new CrashHandler();
        }
        return crashHandler;
    }

    public void init(Context context){
        this.mContext = context;
        this.defaulUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    //异常信息发生时的处理，可以考虑发送给服务器
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if(e == null){
            defaulUncaughtExceptionHandler.uncaughtException(t,e);
        }else{
            handlerException(t,e);
        }
    }

    private void handlerException(Thread t, Throwable e) {
        //必须在主线程执行Toast
        new Thread(){
            public void run(){
                //Androiud 系统当中，默认情况下，
                // 线程是没有开启looper消息处理的，但是主线程除外
                Looper.prepare();
                Toast.makeText(mContext,"亲，出现异常了", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        //搜集异常信心
        collectException(e);

        try{
            //关闭资源

            Thread.sleep(2000);
            AppManager.getInstance().removeAll();
            android.os.Process.killProcess(android.os.Process.myPid());

            //关闭虚拟机，释放所有内存，参数0代表正常退出
            System.exit(0);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private void collectException(Throwable e) {
        final String deviceInfo = Build.DEVICE + ":" + Build.VERSION.SDK_INT
                + ":" + Build.MODEL + ":" + Build.PRODUCT;
        final String message = e.getMessage();
        new Thread(){
            public void run(){
                //可以通过联网将信息发送给后台,所以在分线程执行
                Log.e("TAG", "deviceInfo:" + deviceInfo + ",message:" + message);;
            }
        }.start();
    }
}
