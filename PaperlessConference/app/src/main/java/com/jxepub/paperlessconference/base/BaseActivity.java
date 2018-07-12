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
	 * @Description: 显示一个等待对话框
	 * @param mContext
	 *            上下文环境
	 * @param msg
	 *            消息 * @param cancelable 是否可取消
	 * @return 返回 Dialog这个对象
	 * 
	 */
	public void showLoadingDialog(Context context, String msg, boolean cancelable) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载 view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
		// 自定义图片
		ImageView iv_img = (ImageView) v.findViewById(R.id.iv_dialogloading_img);
		// 提示文字
		TextView tv_msg = (TextView) v.findViewById(R.id.tv_dialogloading_msg);
		// 加载动画
		Animation animation = AnimationUtils.loadAnimation(context, R.anim.loading_animation);
		// 使用 ImageView显示动画
		iv_img.startAnimation(animation);
		if (null != msg) {
			// 设置加载信息
			tv_msg.setText(msg);
		}
		// 创建自定义样式 dialog
		dialog = new Dialog(context, R.style.loading_dialog);
		// 不可以用“返回键”取消
		dialog.setCancelable(cancelable);
		// 设置布局
		dialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		// 显示 dialog
		dialog.show();
	}

	/**
	 * @Title: cancelDialog
	 * @Description: 取消 dialog显示
	 * @author
	 */
	public void cancelDialog() {
		if (null != dialog) {
			dialog.dismiss();
		}
	}

}
