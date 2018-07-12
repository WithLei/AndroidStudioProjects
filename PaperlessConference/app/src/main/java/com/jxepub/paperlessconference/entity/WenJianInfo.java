package com.jxepub.paperlessconference.entity;

import java.io.Serializable;

import com.jxepub.paperlessconference.util.CommonUtil;

public class WenJianInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 文件编号
	 */
	String WenJianNum;
	/**
	 * 文件名
	 */
	String WenJianName;
	/**
	 * 文件地址
	 */
	String Url;
	/**
	 * 所属会议编号
	 */
	String HuiYiNum;
	/**
	 * 文件大小
	 */
	String WenJianDaXiao;

	public WenJianInfo(String wenJianNum, String wenJianName, String url, String huiYiNum, String wenJianDaXiao) {
		super();
		WenJianNum = wenJianNum;
		WenJianName = wenJianName;
		Url = url;
		HuiYiNum = huiYiNum;
		WenJianDaXiao = wenJianDaXiao;
	}

	public String getWenJianNum() {
		return WenJianNum;
	}

	public void setWenJianNum(String wenJianNum) {
		WenJianNum = wenJianNum;
	}

	public String getWenJianName() {
		return WenJianName;
	}

	public void setWenJianName(String wenJianName) {
		WenJianName = wenJianName;
	}

	public String getUrl() {
		return CommonUtil.FileURL + Url;
	}

	public void setUrl(String url) {
		Url = CommonUtil.FileURL + url;
	}

	public String getHuiYiNum() {
		return HuiYiNum;
	}

	public void setHuiYiNum(String huiYiNum) {
		HuiYiNum = huiYiNum;
	}

	public String getWenJianDaXiao() {
		return WenJianDaXiao;
	}

	public void setWenJianDaXiao(String wenJianDaXiao) {
		WenJianDaXiao = wenJianDaXiao;
	}
}
