package com.jxepub.paperlessconference.activity;

import org.apache.http.Header;
import com.jxepub.paperlessconference.R;
import com.jxepub.paperlessconference.base.BaseActivity;
import com.jxepub.paperlessconference.entity.UserInfo;
import com.jxepub.paperlessconference.util.CommonUtil;
import com.jxepub.paperlessconference.util.NetUtils;
import com.jxepub.paperlessconference.util.ParseUtil;
import com.jxepub.paperlessconference.util.ShowMsgUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LeadActivity extends BaseActivity implements OnClickListener {
	private Context mContext;
	private EditText loginUser, loginPwd;
	private TextView tv_login_weixin;
	private ImageView img_login_weixin;
	private Button login_btn;
	private CheckBox login_checkbox;
	private SharedPreferences references;
	private UserInfo info;
	private String adminID, adminPass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lead);
		init();
	}

	void init() {
		mContext = LeadActivity.this;
		loginUser = (EditText) findViewById(R.id.edit_login_user);
		loginPwd = (EditText) findViewById(R.id.edit_login_pwd);
		tv_login_weixin = (TextView) findViewById(R.id.tv_login_weixin);
		img_login_weixin = (ImageView) findViewById(R.id.img_login_weixin);
		login_btn = (Button) findViewById(R.id.login_btn);
		login_checkbox = (CheckBox) findViewById(R.id.login_checkbox);
		tv_login_weixin.setOnClickListener(this);
		img_login_weixin.setOnClickListener(this);
		login_btn.setOnClickListener(this);
		references = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		adminID = references.getString("AdminID", "");
		adminPass = references.getString("AdminPass", "");
		if (!adminID.equals("") && !adminPass.equals("")) {
			login_checkbox.setChecked(true);
			loginUser.setText(adminID);
			loginPwd.setText(adminPass);
		} else {
			login_checkbox.setChecked(false);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 微信扫码登录
		case R.id.tv_login_weixin:
		case R.id.img_login_weixin:
			Toast.makeText(mContext, "正在努力开发中...", Toast.LENGTH_SHORT).show();
			break;
		// 登录
		case R.id.login_btn:
			String user = loginUser.getText().toString().trim();
			String pwd = loginPwd.getText().toString().trim();
			if (user.equals("")) {
				Toast.makeText(mContext, "请输入用户名！", Toast.LENGTH_SHORT).show();
			} else if (pwd.equals("")) {
				Toast.makeText(mContext, "请输入密码！", Toast.LENGTH_SHORT).show();
			} else {
//				showLoadingDialog(mContext, "正在登录...", true);
				if (!NetUtils.isNetworkConnected(mContext)) {
					if (login_checkbox.isChecked()) {
						if (adminID.equals(user) && adminPass.equals(pwd)) {
							handler.sendEmptyMessage(200);
						} else {
							handler.sendEmptyMessage(100);
						}
					}
				} else {
					String url = String.format(CommonUtil.GetLoginInfo, user, pwd);
					AsyncHttpClient client = new AsyncHttpClient();
					client.get(url, new TextHttpResponseHandler() {

						@Override
						public void onSuccess(int arg0, Header[] arg1, String arg2) {
							if (arg0 == 200) {
								info = ParseUtil.ParseUserInfo(arg2);
								if (info != null) {
									handler.sendEmptyMessage(200);
									if (login_checkbox.isChecked()) {
										Editor editor = references.edit();
										editor.putString("AdminID", info.getAdminID());
										editor.putString("AdminName", info.getAdminName());
										editor.putString("AdminPass", info.getAdminPass());
										editor.putString("ZhiWei", info.getZhiWei());
										editor.commit();
									} else {
										Editor editor = references.edit();
										editor.clear();
										editor.commit();
									}
								} else {
									handler.sendEmptyMessage(100);
								}
							}
						}

						@Override
						public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
						}
					});
				}
			}
			break;
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 100:
//				cancelDialog();
				ShowMsgUtil.showMsg(mContext, "登录提示", "登录失败，用户名或密码错误！", "知道了", null, null, null);
				break;
			case 200:
//				cancelDialog();
				startActivity(new Intent(mContext, MainActivity.class));
				finish();
				break;
			}
		};
	};

	// @Override
	// protected void onResume() {
	// super.onResume();
	// if (!NetUtils.isNetworkConnected(mContext)) {
	// ShowMsgUtil.showMsg(mContext, null, "网络不给力，请检查网络设置", "取消", null, "前往设置",
	// new DialogInterface.OnClickListener() {
	// @Override
	// public void onClick(DialogInterface dialog, int which) {
	// Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
	// startActivity(intent);
	// }
	// });
	// }
	// }
}
