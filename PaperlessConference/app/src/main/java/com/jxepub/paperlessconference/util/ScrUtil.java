package com.jxepub.paperlessconference.util;

import android.content.Context;

public class ScrUtil {
	/**
	 * ��pxת��Ϊ��֮��ȵ�dp
	 */
	public static int px2dp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 
	 * ��dpת��Ϊ��֮��ȵ�px
	 */
	public static int dp2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 
	 * ��pxת��Ϊsp
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 
	 * ��spת��Ϊpx
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * ��ȡ�ҵıʼǽ���Ԫ�ط���Ŀ��
	 * 
	 * @param width
	 * @return
	 */
	public static int GetBJWidth(int width) {
		return (width - 448) / 4;
	}

	/**
	 * ��ȡ�ҵıʼǽ���Ԫ�ط���ĸ߶�
	 * 
	 * @param width
	 * @return
	 */
	public static int GetBJHeight(int width) {
		int imgW = (width - 448) / 4;
		float imgH = imgW / (0.75f);
		return (int) imgH;
	}
}
