package com.jxepub.paperlessconference.util;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;

public class ShowMsgUtil {
	public static void showMsg(Context mContext, String title, String message, String negativeText,
			DialogInterface.OnClickListener negativeListener, String positiveText,
			DialogInterface.OnClickListener positiveListener) {
		Builder builder = new Builder(mContext);
		if (title != null) {
			builder.setTitle(title);
		}
		builder.setMessage(message);
		if (negativeText != null) {
			builder.setNegativeButton(negativeText, negativeListener);
		}
		if (positiveText != null) {
			builder.setPositiveButton(positiveText, positiveListener);
		}
		builder.show();
	}
}
