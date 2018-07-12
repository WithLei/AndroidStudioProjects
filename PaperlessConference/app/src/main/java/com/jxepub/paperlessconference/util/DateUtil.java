package com.jxepub.paperlessconference.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jxepub.paperlessconference.entity.HuiYiInfo;

public class DateUtil {

	/**
	 * 由将来的某一时间,计算距离当前的时间
	 */
	public static String CalculateTime(HuiYiInfo info) {
		long nowTime = System.currentTimeMillis(); // 获取当前时间的毫秒数
		String msg = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 指定时间格式
		Date satrtTime = null; // 会议开始时间
		Date endTime = null; // 会议结束时间
		try {
			satrtTime = sdf.parse(info.getHuiYiTimeK()); // 将字符串转换为指定的时间格式
			endTime = sdf.parse(info.getHuiYiTimeJ());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long start = satrtTime.getTime(); // 获取指定时间的毫秒数
		long dateDiff = start - nowTime;

		if (dateDiff < 0) {
			long end = endTime.getTime(); // 获取指定时间的毫秒数
			long dateDiff1 = end - nowTime;
			if (dateDiff1 > 0) {
				msg = "此会议正在进行中...";
			} else {
				msg = "此会议已结束";
			}
		} else {

			long dateTemp1 = dateDiff / 1000; // 秒
			long dateTemp2 = dateTemp1 / 60; // 分钟
			long dateTemp3 = dateTemp2 / 60; // 小时
			long dateTemp4 = dateTemp3 / 24; // 天数
			long dateTemp5 = dateTemp4 / 30; // 月数
			long dateTemp6 = dateTemp5 / 12; // 年数

			if (dateTemp6 > 0) {
				msg = "距您参加会议还有\u3000\u3000" + dateTemp6 + "年";

			} else if (dateTemp5 > 0) {
				msg = "距您参加会议还有\u3000\u3000" + dateTemp5 + "月";

			} else if (dateTemp4 > 0) {
				msg = "距您参加会议还有\u3000\u3000" + dateTemp4 + "天";

			} else if (dateTemp3 > 0) {
				msg = "距您参加会议还有\u3000\u3000" + dateTemp3 + "小时";

			} else if (dateTemp2 > 0) {
				msg = "距您参加会议还有\u3000\u3000" + dateTemp2 + "分钟";
			}
		}
		return msg;
	}
}
