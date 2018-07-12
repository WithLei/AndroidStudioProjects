package com.jxepub.paperlessconference.util;

import android.content.Context;

public class ScrUtil {
	/**
	 * 将px转换为与之相等的dp
	 */
	public static int px2dp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 
	 * 将dp转换为与之相等的px
	 */
	public static int dp2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 
	 * 将px转换为sp
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 
	 * 将sp转换为px
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 获取我的笔记界面元素封面的宽度
	 * 
	 * @param width
	 * @return
	 */
	public static int GetBJWidth(int width) {
		return (width - 448) / 4;
	}

	/**
	 * 获取我的笔记界面元素封面的高度
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
