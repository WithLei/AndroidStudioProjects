package com.jxepub.paperlessconference.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.jxepub.paperlessconference.R;
import com.jxepub.paperlessconference.fragment.FragHuiYiFuWu;
import com.jxepub.paperlessconference.fragment.FragLiShiHuiYi;
import com.jxepub.paperlessconference.fragment.FragWoDeBiJi;
import com.jxepub.paperlessconference.fragment.FragWoDeHuiYi;
import com.jxepub.paperlessconference.util.NetUtils;
import com.jxepub.paperlessconference.util.ShowMsgUtil;
import com.jxepub.paperlessconference.view.NoScrollViewPager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener {
	private SharedPreferences references;
	private Context mContext;
	private TextView user_name, main_time, main_dianliang;
	private ImageView main_wifi, main_setting, main_tongbu;
	public List<Fragment> frags;
	public NoScrollViewPager viewPager;
	private Fragment wodehuiyi, lishihuiyi, wodebiji, huiyifuwu;
	private RelativeLayout menu_wodehuiyi, menu_lishihuiyi, menu_wodebiji, menu_huiyifuwu;
	private ProgressBar dianchi_pro;
	public MyFrageStatePagerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		setListener();
		regReceiver();
	}

	void init() {
		mContext = MainActivity.this;
		user_name = (TextView) findViewById(R.id.user_name);
		main_wifi = (ImageView) findViewById(R.id.main_wifi);
		main_setting = (ImageView) findViewById(R.id.main_setting);
		main_tongbu = (ImageView) findViewById(R.id.main_tongbu);
		main_time = (TextView) findViewById(R.id.main_time);
		main_dianliang = (TextView) findViewById(R.id.main_dianliang);
		menu_wodehuiyi = (RelativeLayout) findViewById(R.id.menu_wodehuiyi);
		menu_lishihuiyi = (RelativeLayout) findViewById(R.id.menu_lishihuiyi);
		menu_wodebiji = (RelativeLayout) findViewById(R.id.menu_wodebiji);
		menu_huiyifuwu = (RelativeLayout) findViewById(R.id.menu_huiyifuwu);
		dianchi_pro = (ProgressBar) findViewById(R.id.dianchi_pro);
		viewPager = (NoScrollViewPager) findViewById(R.id.main_viewpager);
		viewPager.setNoScroll(true);// ��ֹ���һ����л����沢ȥ������Ч��
		frags = new ArrayList<Fragment>();
		wodehuiyi = new FragWoDeHuiYi();
		frags.add(wodehuiyi);
		lishihuiyi = new FragLiShiHuiYi();
		frags.add(lishihuiyi);
		wodebiji = new FragWoDeBiJi();
		frags.add(wodebiji);
		huiyifuwu = new FragHuiYiFuWu();
		frags.add(huiyifuwu);
		adapter = new MyFrageStatePagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);

		references = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		String adminName = references.getString("AdminName", "");
		if (!adminName.equals("")) {
			user_name.setText(adminName);
		}
	}

	void setListener() {
		main_wifi.setOnClickListener(this);
		main_setting.setOnClickListener(this);
		main_tongbu.setOnClickListener(this);
		menu_wodehuiyi.setOnClickListener(this);
		menu_lishihuiyi.setOnClickListener(this);
		menu_wodebiji.setOnClickListener(this);
		menu_huiyifuwu.setOnClickListener(this);
		user_name.setOnClickListener(this);
	}

	void regReceiver() {
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_TIME_TICK);
		filter.addAction(Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(receiver, filter);
	}

	private final BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			// ���µ�ǰϵͳʱ��
			if (action.equals(Intent.ACTION_TIME_TICK)) {
				main_time.setText(new SimpleDateFormat("HH:mm").format(new Date()));
			}
			// ���µ�ǰϵͳ����
			if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
				Bundle extras = intent.getExtras();// ��ȡ��ͼ�����еĸ�����Ϣ
				// ��ȡ��ǰ����
				int level = extras.getInt(BatteryManager.EXTRA_LEVEL/* ��ǰ���� */, 0);
				main_dianliang.setText(level + "%");
				dianchi_pro.setProgress(level);
			}
		}
	};

	@Override
	protected void onResume() {
		super.onResume();
		// ������ʾʱ��
		main_time.setText(new SimpleDateFormat("HH:mm").format(new Date()));
		if (!NetUtils.isNetworkConnected(mContext)) {
			main_wifi.setImageResource(R.drawable.nowifi);
			ShowMsgUtil.showMsg(mContext, null, "���粻������������������", "ȡ��", null, "ǰ������",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
							startActivity(intent);
						}
					});
		} else {
			main_wifi.setImageResource(R.drawable.wifi);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// ��ת��ϵͳ���ý���
		case R.id.main_setting:
			Intent intent = new Intent(Settings.ACTION_SETTINGS);
			startActivity(intent);
			break;
		// ��ת��WiFi�������ý���
		case R.id.main_wifi:
			Intent intent1 = new Intent(Settings.ACTION_WIFI_SETTINGS);
			startActivity(intent1);
			break;
		// ͬ�������ļ�
		case R.id.main_tongbu:
			Toast.makeText(mContext, "ͬ�������ļ����ܣ�����Ŭ��������...", Toast.LENGTH_SHORT).show();
			break;
		// �ҵĻ���
		case R.id.menu_wodehuiyi:
			viewPager.setCurrentItem(0);
			menu_wodehuiyi.setBackgroundResource(R.drawable.menu_selected);
			menu_lishihuiyi.setBackgroundResource(R.drawable.menu_select);
			menu_wodebiji.setBackgroundResource(R.drawable.menu_select);
			menu_huiyifuwu.setBackgroundResource(R.drawable.menu_select);
			break;
		// ��ʷ����
		case R.id.menu_lishihuiyi:
			viewPager.setCurrentItem(1);
			menu_wodehuiyi.setBackgroundResource(R.drawable.menu_select);
			menu_lishihuiyi.setBackgroundResource(R.drawable.menu_selected);
			menu_wodebiji.setBackgroundResource(R.drawable.menu_select);
			menu_huiyifuwu.setBackgroundResource(R.drawable.menu_select);
			break;
		// �ҵıʼ�
		case R.id.menu_wodebiji:
			viewPager.setCurrentItem(2);
			menu_wodehuiyi.setBackgroundResource(R.drawable.menu_select);
			menu_lishihuiyi.setBackgroundResource(R.drawable.menu_select);
			menu_wodebiji.setBackgroundResource(R.drawable.menu_selected);
			menu_huiyifuwu.setBackgroundResource(R.drawable.menu_select);
			break;
		// �������
		case R.id.menu_huiyifuwu:
			viewPager.setCurrentItem(3);
			menu_wodehuiyi.setBackgroundResource(R.drawable.menu_select);
			menu_lishihuiyi.setBackgroundResource(R.drawable.menu_select);
			menu_wodebiji.setBackgroundResource(R.drawable.menu_select);
			menu_huiyifuwu.setBackgroundResource(R.drawable.menu_selected);
			break;
		// ע����¼
		case R.id.user_name:
			ShowMsgUtil.showMsg(mContext, null, "�˳���¼", "ȡ��", null, "ȷ��", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					startActivity(new Intent(mContext, LeadActivity.class));
					finish();
				}
			});
			break;
		}
	}

	public class MyFrageStatePagerAdapter extends FragmentStatePagerAdapter {

		public MyFrageStatePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			return frags.get(arg0);
		}

		@Override
		public int getCount() {
			return frags.size();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (receiver != null) {
			unregisterReceiver(receiver);
		}
	}

	long firstTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (System.currentTimeMillis() - firstTime > 2000) {
				firstTime = System.currentTimeMillis();
				Toast.makeText(mContext, "�ٰ�һ�η��ؼ��˳���ֽ������", Toast.LENGTH_SHORT).show();
			} else {
				android.os.Process.killProcess(android.os.Process.myPid());
				System.exit(0);
				System.gc();
			}
		}
		return true;
	}
}
