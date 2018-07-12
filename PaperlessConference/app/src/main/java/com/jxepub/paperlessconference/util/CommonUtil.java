package com.jxepub.paperlessconference.util;

public class CommonUtil {
	/**
	 * 文件下载地址前缀mFnnjvM7
	 */
	public static final String FileURL = "http://gfhy.93.qidanet.com/admin/AdminUploadFile/";
	/**
	 * 文件保存路径
	 */
	public static final String FILEPATH = "/data/data/com.jxepub.paperlessconference/HuiYiWenJian/";
	/**
	 * 笔记保存路径
	 */
	public static final String BIJIPATH = "/data/data/com.jxepub.paperlessconference/BIJI/";
	/**
	 * 登录
	 */
	public static final String GetLoginInfo = "http://gfhy.93.qidanet.com/json/GetLoginInfo.aspx?AdminID=%s&Password=%s";
	/**
	 * 获取会议信息 AdminID:用户名;ZT:会议状态(0表示即将要开的会议，1表示进行中的会议，2表示已完成的会议(历史会议))
	 */
	public static final String GetHuiYiInfo = "http://gfhy.93.qidanet.com/json/GetHuiYiInfo.aspx?AdminID=%s&ZT=%s";
}
