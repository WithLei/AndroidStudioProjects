package com.jxepub.paperlessconference.entity;

public class UserInfo {
	/**
	 * �˻�
	 */
	String AdminID;
	/**
	 * �û�����
	 */
	String AdminName;
	/**
	 * ����
	 */
	String AdminPass;
	/**
	 * ְλ
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
