package com.android.renly.zhihuhu;

import android.graphics.drawable.Drawable;

/**
 * Created by Renly on 2018/1/29.
 */

public class itemInfo {
    private Drawable headphoto;//头像
    private String username;//用户名
    private String action;//用户行为
    private long time;//经过时间
    private String title;//标题
    private String content;//内容梗概
    private int agreeCount;//赞同数
    private int commentCount;//评论数

    public itemInfo(Drawable headphoto, String username, String action, long time, String title, String content, int agreeCount, int commentCount) {
        this.headphoto = headphoto;
        this.username = username;
        this.action = action;
        this.time = time;
        this.title = title;
        this.content = content;
        this.agreeCount = agreeCount;
        this.commentCount = commentCount;
    }

    public itemInfo() {
    }

    public Drawable getHeadphoto() {
        return headphoto;
    }

    public String getUsername() {
        return username;
    }

    public String getAction() {
        return action;
    }

    public long getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getAgreeCount() {
        return agreeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setHeadphoto(Drawable headphoto) {
        this.headphoto = headphoto;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAgreeCount(int agreeCount) {
        this.agreeCount = agreeCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public String toString() {
        return "itemInfo{" +
                "headphoto=" + headphoto +
                ", username='" + username + '\'' +
                ", action='" + action + '\'' +
                ", time=" + time +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", agreeCount=" + agreeCount +
                ", commentCount=" + commentCount +
                '}';
    }
}
