package com.jxepub.paperlessconference.entity;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class ScrSize {
	public int width;
	public int height;
	public float density;
	private static ScrSize scrSize = null;

	public static ScrSize getInstance(Context mContext) {
		if (scrSize == null) {
			Resources resources = mContext.getResources();
			DisplayMetrics dm = resources.getDisplayMetrics();
			float density = dm.density;
			int width = dm.widthPixels;
			int height = dm.heightPixels;

			scrSize = new ScrSize(width, height, density);
		}
		return scrSize;
	}

	private ScrSize(int width, int height, float density) {
		super();
		this.width = width;
		this.height = height;
		this.density = density;
	}
}
