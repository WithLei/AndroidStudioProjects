package com.android.renly.listview;

import android.graphics.drawable.Drawable;

/**
 * 应用信息的封装类
 * Created by Renly on 2018/1/19.
 */

public class AppInfo {
    private Drawable icon;//应用名称
    private String appName;//应用名称
    private String packagename;//包名

    public AppInfo(Drawable icon, String appName, String packagename) {
        this.icon = icon;
        this.appName = appName;
        this.packagename = packagename;
    }

    public AppInfo() {
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "icon=" + icon +
                ", appName='" + appName + '\'' +
                ", packagename='" + packagename + '\'' +
                '}';
    }
}
