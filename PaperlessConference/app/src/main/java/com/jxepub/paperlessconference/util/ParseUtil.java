package com.jxepub.paperlessconference.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jxepub.paperlessconference.entity.HuiYiInfo;
import com.jxepub.paperlessconference.entity.UserInfo;
import com.jxepub.paperlessconference.entity.WenJianInfo;

import android.util.Log;

public class ParseUtil {
	/**
	 * 
	 * @param json
	 * @return
	 */
	public static UserInfo ParseUserInfo(String json) {
		// {"ResultCode":"0","ResultMsg":"登录成功","Info":[{"AdminID":"ZYP","AdminName":"钟玉平","AdminPass":"123456","ZhiWei":"安卓开发工程师"}]}
		UserInfo info = null;
		try {
			JSONObject object = new JSONObject(json);
			String code = object.getString("ResultCode");
			if (code.equals("0")) {
				JSONArray array = object.getJSONArray("Info");
				for (int i = 0; i < array.length(); i++) {
					String adminID = array.getJSONObject(i).getString("AdminID");
					String adminName = array.getJSONObject(i).getString("AdminName");
					String adminPass = array.getJSONObject(i).getString("AdminPass");
					String zhiWei = array.getJSONObject(i).getString("ZhiWei");
					info = new UserInfo(adminID, adminName, adminPass, zhiWei);
					return info;
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return info;
	}

	/**
	 * 
	 * @param json
	 * @return
	 */
	public static List<HuiYiInfo> GetHuiYiInfo(String json) {
		List<HuiYiInfo> infos = new ArrayList<HuiYiInfo>();
		try {
			JSONObject object = new JSONObject(json);
			String code = object.getString("ResultCode");
			if (code.equals("0")) {
				JSONArray array = object.getJSONArray("Info");
				for (int i = 0; i < array.length(); i++) {
					String huiYiNum = array.getJSONObject(i).getString("HuiYiNum");
					String huiYiName = array.getJSONObject(i).getString("HuiYiName");
					String huiYiRenShu = array.getJSONObject(i).getString("HuiYiRenShu");
					String huiYiNeiRong = array.getJSONObject(i).getString("HuiYiNeiRong");
					String huiYiTimeK = array.getJSONObject(i).getString("HuiYiTimeK");
					String huiYiTimeJ = array.getJSONObject(i).getString("HuiYiTimeJ");
					String huiYiShiNum = array.getJSONObject(i).getString("HuiYiShiNum");
					String huiYiRenYuan = array.getJSONObject(i).getString("HuiYiRenYuan");
					String faQiRen = array.getJSONObject(i).getString("FaQiRen");
					String shiChang = array.getJSONObject(i).getString("ShiChang");
					String beiZhu = array.getJSONObject(i).getString("BeiZhu");
					String zT = array.getJSONObject(i).getString("ZT");

					List<WenJianInfo> wjInfos = new ArrayList<WenJianInfo>();
					JSONArray array2 = array.getJSONObject(i).getJSONArray("HuiYiWenJian");
					for (int j = 0; j < array2.length(); j++) {
						String wenJianNum = array2.getJSONObject(j).getString("WenJianNum");
						String wenJianName = array2.getJSONObject(j).getString("WenJianName");
						String url = array2.getJSONObject(j).getString("Url");
						String huiYiNum1 = array2.getJSONObject(j).getString("HuiYiNum");
						String wenJianDaXiao = array2.getJSONObject(j).getString("WenJianDaXiao");
						WenJianInfo info = new WenJianInfo(wenJianNum, wenJianName, url, huiYiNum1, wenJianDaXiao);
						wjInfos.add(info);
					}
					HuiYiInfo hyinfo = new HuiYiInfo(huiYiNum, huiYiName, huiYiRenShu, huiYiNeiRong, huiYiTimeK,
							huiYiTimeJ, huiYiShiNum, huiYiRenYuan, faQiRen, shiChang, beiZhu, zT, wjInfos);
					infos.add(hyinfo);
				}
			}
		} catch (Exception e) {
		}
		return infos;
	}
}
