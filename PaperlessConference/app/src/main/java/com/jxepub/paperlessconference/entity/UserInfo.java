package com.jxepub.paperlessconference.entity;

public class UserInfo {
	/**
	 * 账户
	 */
	String AdminID;
	/**
	 * 用户姓名
	 */
	String AdminName;
	/**
	 * 密码
	 */
	String AdminPass;
	/**
	 * 职位
	 */
	String ZhiWei;

	public UserInfo(String adminID, String adminName, String adminPass, String zhiWei) {
		super();
		AdminID = adminID;
		AdminName = adminName;
		AdminPass = adminPass;
		ZhiWei = zhiWei;
	}

	public String getAdminID() {
		return AdminID;
	}

	public void setAdminID(String adminID) {
		AdminID = adminID;
	}

	public String getAdminName() {
		return AdminName;
	}

	public void setAdminName(String adminName) {
		AdminName = adminName;
	}

	public String getAdminPass() {
		return AdminPass;
	}

	public void setAdminPass(String adminPass) {
		AdminPass = adminPass;
	}

	public String getZhiWei() {
		return ZhiWei;
	}

	public void setZhiWei(String zhiWei) {
		ZhiWei = zhiWei;
	}

}
