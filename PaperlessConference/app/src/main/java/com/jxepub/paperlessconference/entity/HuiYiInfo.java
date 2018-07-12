package com.jxepub.paperlessconference.entity;

import java.io.Serializable;
import java.util.List;

public class HuiYiInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String HuiYiNum;
	String HuiYiName;
	String HuiYiRenShu;
	String HuiYiNeiRong;
	String HuiYiTimeK;
	String HuiYiTimeJ;
	String HuiYiShiNum;
	String HuiYiRenYuan;
	String FaQiRen;
	String ShiChang;
	String BeiZhu;
	String ZT;
	List<WenJianInfo> wjInfos;

	public String getHuiYiNum() {
		return HuiYiNum;
	}

	public void setHuiYiNum(String huiYiNum) {
		HuiYiNum = huiYiNum;
	}

	public String getHuiYiName() {
		return HuiYiName;
	}

	public void setHuiYiName(String huiYiName) {
		HuiYiName = huiYiName;
	}

	public String getHuiYiRenShu() {
		return HuiYiRenShu;
	}

	public void setHuiYiRenShu(String huiYiRenShu) {
		HuiYiRenShu = huiYiRenShu;
	}

	public String getHuiYiNeiRong() {
		return HuiYiNeiRong;
	}

	public void setHuiYiNeiRong(String huiYiNeiRong) {
		HuiYiNeiRong = huiYiNeiRong;
	}

	public String getHuiYiTimeK() {
		return HuiYiTimeK;
	}

	public void setHuiYiTimeK(String huiYiTimeK) {
		HuiYiTimeK = huiYiTimeK;
	}

	public String getHuiYiTimeJ() {
		return HuiYiTimeJ;
	}

	public void setHuiYiTimeJ(String huiYiTimeJ) {
		HuiYiTimeJ = huiYiTimeJ;
	}

	public String getHuiYiShiNum() {
		return HuiYiShiNum;
	}

	public void setHuiYiShiNum(String huiYiShiNum) {
		HuiYiShiNum = huiYiShiNum;
	}

	public String getHuiYiRenYuan() {
		return HuiYiRenYuan;
	}

	public void setHuiYiRenYuan(String huiYiRenYuan) {
		HuiYiRenYuan = huiYiRenYuan;
	}

	public String getFaQiRen() {
		return FaQiRen;
	}

	public void setFaQiRen(String faQiRen) {
		FaQiRen = faQiRen;
	}

	public String getShiChang() {
		return ShiChang;
	}

	public void setShiChang(String shiChang) {
		ShiChang = shiChang;
	}

	public String getBeiZhu() {
		return BeiZhu;
	}

	public void setBeiZhu(String beiZhu) {
		BeiZhu = beiZhu;
	}

	public String getZT() {
		return ZT;
	}

	public void setZT(String zT) {
		ZT = zT;
	}

	public List<WenJianInfo> getWjInfos() {
		return wjInfos;
	}

	public void setWjInfos(List<WenJianInfo> wjInfos) {
		this.wjInfos = wjInfos;
	}

	public HuiYiInfo(String huiYiNum, String huiYiName, String huiYiRenShu, String huiYiNeiRong, String huiYiTimeK,
			String huiYiTimeJ, String huiYiShiNum, String huiYiRenYuan, String faQiRen, String shiChang, String beiZhu,
			String zT, List<WenJianInfo> wjInfos) {
		super();
		HuiYiNum = huiYiNum;
		HuiYiName = huiYiName;
		HuiYiRenShu = huiYiRenShu;
		HuiYiNeiRong = huiYiNeiRong;
		HuiYiTimeK = huiYiTimeK;
		HuiYiTimeJ = huiYiTimeJ;
		HuiYiShiNum = huiYiShiNum;
		HuiYiRenYuan = huiYiRenYuan;
		FaQiRen = faQiRen;
		ShiChang = shiChang;
		BeiZhu = beiZhu;
		ZT = zT;
		this.wjInfos = wjInfos;
	}

}
