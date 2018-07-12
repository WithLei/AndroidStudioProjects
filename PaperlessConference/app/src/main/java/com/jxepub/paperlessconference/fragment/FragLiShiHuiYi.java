package com.jxepub.paperlessconference.fragment;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import com.jxepub.paperlessconference.R;
import com.jxepub.paperlessconference.activity.MainActivity;
import com.jxepub.paperlessconference.adapter.LiShiAdapter;
import com.jxepub.paperlessconference.entity.HuiYiInfo;
import com.jxepub.paperlessconference.util.CommonUtil;
import com.jxepub.paperlessconference.util.ParseUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FragLiShiHuiYi extends Fragment implements OnClickListener {
	private View view;
	private SharedPreferences references;
	private Context mContext;
	private String adminID;
	private ListView frg_lishi_lv;
	private LiShiAdapter adapter;
	private FragmentManager manager;
	private FragmentTransaction transaction;
	private int index = 1, pageCount = 1, pageSize = 5;
	private ImageView btn_xiayiye, btn_weiye, btn_shangyiye, btn_shouye;
	private TextView tv_yema;
	private List<HuiYiInfo> infos, tempInfos;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_lishihuiyi, null);
		init();
		initHuiYiData();
		return view;
	}

	private void init() {
		tempInfos = new ArrayList<HuiYiInfo>();
		mContext = getActivity();
		references = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		adminID = references.getString("AdminID", "");
		btn_xiayiye = (ImageView) view.findViewById(R.id.btn_xiayiye);
		btn_weiye = (ImageView) view.findViewById(R.id.btn_weiye);
		btn_shangyiye = (ImageView) view.findViewById(R.id.btn_shangyiye);
		btn_shouye = (ImageView) view.findViewById(R.id.btn_shouye);
		btn_xiayiye.setOnClickListener(this);
		btn_weiye.setOnClickListener(this);
		btn_shangyiye.setOnClickListener(this);
		btn_shouye.setOnClickListener(this);

		tv_yema = (TextView) view.findViewById(R.id.tv_yema);

		frg_lishi_lv = (ListView) view.findViewById(R.id.frg_lishi_lv);
		manager = getFragmentManager();
		frg_lishi_lv.setOnItemClickListener(listener);

	}

	void initHuiYiData() {
		String url = String.format(CommonUtil.GetHuiYiInfo, adminID, "2");
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(url, new LiShiHuiYiResponseHandler());
	}

	class LiShiHuiYiResponseHandler extends TextHttpResponseHandler {
		@Override
		public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {

		}

		@Override
		public void onSuccess(int arg0, Header[] arg1, String arg2) {
			if (arg0 == 200) {
				infos = ParseUtil.GetHuiYiInfo(arg2);
				if (infos.size() > 0) {
					handler.sendEmptyMessage(100);
				}
			}
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 100) {
				if (infos.size() > 0) {
					if (infos.size() % pageSize == 0) {
						pageCount = infos.size() / pageSize;
					} else {
						pageCount = infos.size() / pageSize + 1;
					}
					tv_yema.setText(String.format("%s / %s", index, pageCount));
					showHuiYiInfo(1);
				}
			}
		};
	};

	private OnItemClickListener listener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// 替换当前布局
			FragLiShiHuiYiXQ liShiHuiYiXQ = new FragLiShiHuiYiXQ();
			transaction = manager.beginTransaction();
			HuiYiInfo info = infos.get(position);
			Bundle bundle = new Bundle();
			bundle.putSerializable("HuiYiInfo", info);
			liShiHuiYiXQ.setArguments(bundle);
			transaction.commit();
			MainActivity activity = (MainActivity) getActivity();
			if (activity.frags.size() > 4) {
				for (int i = 4; i < activity.frags.size(); i++) {
					activity.frags.remove(i);
				}
			}
			activity.frags.add(liShiHuiYiXQ);
			activity.adapter.notifyDataSetChanged();
			activity.viewPager.setCurrentItem(4);
		}
	};

	/**
	 * 替换当前布局 FragLiShiHuiYiXQ liShiHuiYiXQ = new FragLiShiHuiYiXQ();
	 * MainActivity activity = (MainActivity)getActivity();
	 * activity.frags.add(liShiHuiYiXQ);
	 * activity.adapter.notifyDataSetChanged();
	 * activity.viewPager.setCurrentItem(4);
	 */

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_xiayiye:// 下一页
			index++;
			if (index > pageCount) {
				index = pageCount;
			}
			showHuiYiInfo(index);
			break;
		case R.id.btn_weiye:// 尾页
			if (index != pageCount) {
				index = pageCount;
				showHuiYiInfo(index);
			}
			break;
		case R.id.btn_shangyiye:// 上一页
			index--;
			if (index < 1) {
				index = 1;
			}
			showHuiYiInfo(index);
			break;
		case R.id.btn_shouye:// 首页
			if (index != 1) {
				index = 1;
				showHuiYiInfo(index);
			}
			break;
		}

	}

	private void showHuiYiInfo(int index) {
		tv_yema.setText(String.format("%s / %s", index, pageCount));
		if (tempInfos.size() > 0) {
			tempInfos.clear();
		}
		int start = (index - 1) * pageSize;
		int end = index * pageSize;
		if (infos.size() <= end) {
			end = infos.size();
		}
		for (int i = start; i < end; i++) {
			tempInfos.add(infos.get(i));
		}
		adapter = new LiShiAdapter(mContext, tempInfos);
		frg_lishi_lv.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
}
