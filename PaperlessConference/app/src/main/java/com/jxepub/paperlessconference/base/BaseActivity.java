package com.jxepub.paperlessconference.base;

import com.jxepub.paperlessconference.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BaseActivity extends Activity {
	private Dialog dialog;

	/**
	 * @Title: showLoadingDialog
	 * @Description: ��ʾһ���ȴ��Ի���
	 * @param mContext
	 *            �����Ļ���
	 * @param msg
	 *            ��Ϣ * @param cancelable �Ƿ��ȡ��
	 * @return ���� Dialog�������
	 * 
	 */
	public void showLoadingDialog(Context context, String msg, boolean cancelable) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.dialog_loading, null);// �õ����� view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// ���ز���
		// �Զ���ͼƬ
		ImageView iv_img = (ImageView) v.findViewById(R.id.iv_dialogloading_img);
		// ��ʾ����
		TextView tv_msg = (TextView) v.findViewById(R.id.tv_dialogloading_msg);
		// ���ض���
		Animation animation = AnimationUtils.loadAnimation(context, R.anim.loading_animation);
		// ʹ�� ImageView��ʾ����
		iv_img.startAnimation(animation);
		if (null != msg) {
			// ���ü�����Ϣ
			tv_msg.setText(msg);
		}
		// �����Զ�����ʽ dialog
		dialog = new Dialog(context, R.style.loading_dialog);
		// �������á����ؼ���ȡ��
		dialog.setCancelable(cancelable);
		// ���ò���
		dialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		// ��ʾ dialog
		dialog.show();
	}

	/**
	 * @Title: cancelDialog
	 * @Description: ȡ�� dialog��ʾ
	 * @author
	 */
	public void cancelDialog() {
		if (null != dialog) {
			dialog.dismiss();
		}
	}

}
