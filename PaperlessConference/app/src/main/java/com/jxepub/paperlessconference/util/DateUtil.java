package com.jxepub.paperlessconference.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jxepub.paperlessconference.entity.HuiYiInfo;

public class DateUtil {

	/**
	 * �ɽ�����ĳһʱ��,������뵱ǰ��ʱ��
	 */
	public static String CalculateTime(HuiYiInfo info) {
		long nowTime = System.currentTimeMillis(); // ��ȡ��ǰʱ��ĺ�����
		String msg = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// ָ��ʱ���ʽ
		Date satrtTime = null; // ���鿪ʼʱ��
		Date endTime = null; // �������ʱ��
		try {
			satrtTime = sdf.parse(info.getHuiYiTimeK()); // ���ַ���ת��Ϊָ����ʱ���ʽ
			endTime = sdf.parse(info.getHuiYiTimeJ());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long start = satrtTime.getTime(); // ��ȡָ��ʱ��ĺ�����
		long dateDiff = start - nowTime;

		if (dateDiff < 0) {
			long end = endTime.getTime(); // ��ȡָ��ʱ��ĺ�����
			long dateDiff1 = end - nowTime;
			if (dateDiff1 > 0) {
				msg = "�˻������ڽ�����...";
			} else {
				msg = "�˻����ѽ���";
			}
		} else {

			long dateTemp1 = dateDiff / 1000; // ��
			long dateTemp2 = dateTemp1 / 60; // ����
			long dateTemp3 = dateTemp2 / 60; // Сʱ
			long dateTemp4 = dateTemp3 / 24; // ����
			long dateTemp5 = dateTemp4 / 30; // ����
			long dateTemp6 = dateTemp5 / 12; // ����

			if (dateTemp6 > 0) {
				msg = "�����μӻ��黹��\u3000\u3000" + dateTemp6 + "��";

			} else if (dateTemp5 > 0) {
				msg = "�����μӻ��黹��\u3000\u3000" + dateTemp5 + "��";

			} else if (dateTemp4 > 0) {
				msg = "�����μӻ��黹��\u3000\u3000" + dateTemp4 + "��";

			} else if (dateTemp3 > 0) {
				msg = "�����μӻ��黹��\u3000\u3000" + dateTemp3 + "Сʱ";

			} else if (dateTemp2 > 0) {
				msg = "�����μӻ��黹��\u3000\u3000" + dateTemp2 + "����";
			}
		}
		return msg;
	}
}
