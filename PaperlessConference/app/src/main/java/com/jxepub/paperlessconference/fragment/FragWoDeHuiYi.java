package com.jxepub.paperlessconference.fragment;

import java.io.File;
import java.net.URLDecoder;
import java.util.List;
import org.apache.http.Header;
import com.jxepub.paperlessconference.R;
import com.jxepub.paperlessconference.activity.PDFReadActivity;
import com.jxepub.paperlessconference.adapter.RenYuanAdapter;
import com.jxepub.paperlessconference.adapter.WenJianAdapter;
import com.jxepub.paperlessconference.entity.HuiYiInfo;
import com.jxepub.paperlessconference.entity.WenJianInfo;
import com.jxepub.paperlessconference.util.CommonUtil;
import com.jxepub.paperlessconference.util.DateUtil;
import com.jxepub.paperlessconference.util.FileDownAsyncTask;
import com.jxepub.paperlessconference.util.ParseUtil;
import com.jxepub.paperlessconference.util.ShuZhiDaoHang;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FragWoDeHuiYi extends Fragment {
	private View view;
	private TextView frg_wode_tishi, frg_wode_title, faqibumen, didian, huiyishijian, huiyishichang, totalry,
			beizhuneirong;
	private List<HuiYiInfo> infos;
	private SharedPreferences references;
	private String adminID;
	private int index = 0;
	private GridView renyuan_gv;
	private List<WenJianInfo> wjinfos;
	private ListView frg_wode_lv;
	private Context mContext;
	private ShuZhiDaoHang szdh;
	private int size;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_wodehuiyi, null);
		findView(view);
		init();
		getHuiYiInfo();

		return view;
	}

	void findView(View v) {
		frg_wode_tishi = (TextView) v.findViewById(R.id.frg_wode_tishi);
		frg_wode_title = (TextView) v.findViewById(R.id.frg_wode_title);
		faqibumen = (TextView) v.findViewById(R.id.faqibumen);
		didian = (TextView) v.findViewById(R.id.didian);
		huiyishijian = (TextView) v.findViewById(R.id.huiyishijian);
		huiyishichang = (TextView) v.findViewById(R.id.huiyishichang);
		totalry = (TextView) v.findViewById(R.id.totalry);
		beizhuneirong = (TextView) v.findViewById(R.id.beizhuneirong);
		renyuan_gv = (GridView) v.findViewById(R.id.renyuan_gv);
		frg_wode_lv = (ListView) v.findViewById(R.id.frg_wode_lv);
	}

	void init() {
		mContext = getActivity();
		references = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		adminID = references.getString("AdminID", "");
		szdh = new ShuZhiDaoHang();
	}

	void getHuiYiInfo() {
		String url = String.format(CommonUtil.GetHuiYiInfo, adminID, "0");
		AsyncHttpClient client = new AsyncHttpClient();
		client.get(url, new HuiYiHttpResponseHandler());
	}

	class HuiYiHttpResponseHandler extends TextHttpResponseHandler {

		@Override
		public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {

		}

		@Override
		public void onSuccess(int arg0, Header[] arg1, String arg2) {
			if (arg0 == 200) {
				infos = ParseUtil.GetHuiYiInfo(arg2);
				if (infos.size() > 0) {
					size = infos.size();
					handler.sendEmptyMessage(index = 0);
				}
			}
		}
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			HuiYiInfo info = infos.get(index);
			frg_wode_tishi.setText(DateUtil.CalculateTime(info));
			frg_wode_title.setText(info.getHuiYiName());
			faqibumen.setText("发起人：" + info.getFaQiRen());
			huiyishijian.setText("会议时间：" + info.getHuiYiTimeK().substring(0, info.getHuiYiTimeK().lastIndexOf(":")));
			huiyishichang.setText("会议时长：" + info.getShiChang() + "小时");
			totalry.setText("共 " + info.getHuiYiRenShu() + " 人");
			beizhuneirong.setText(info.getHuiYiNeiRong());
			didian.setText("地点：" + info.getHuiYiShiNum());
			RenYuanAdapter adapter = new RenYuanAdapter(getActivity(), info.getHuiYiRenYuan().split("\\|"));
			renyuan_gv.setAdapter(adapter);
			adapter.notifyDataSetChanged();

			wjinfos = info.getWjInfos();
			WenJianAdapter jianAdapter = new WenJianAdapter(getActivity(), wjinfos);
			frg_wode_lv.setAdapter(jianAdapter);
			adapter.notifyDataSetChanged();
			frg_wode_lv.setOnItemClickListener(listener);

			if (size > 0) {
				for (int i = 0; i < size; i++) {
					final int j = i;
					if (j < 15) {
						RelativeLayout layout = (RelativeLayout) view.findViewById(szdh.relas.get(i));
						TextView textView = (TextView) view.findViewById(szdh.tvs.get(i));
						layout.setVisibility(View.VISIBLE);

						layout.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								handler.sendEmptyMessage(index = j);
							}
						});

						if (i == index) {
							layout.setBackgroundResource(R.drawable.daohangbg_selected);
							textView.setTextColor(Color.WHITE);
						} else {
							layout.setBackgroundResource(R.drawable.daohangbg_select);
							textView.setTextColor(Color.BLACK);
						}
					}
				}
			}
		};
	};
	private OnItemClickListener listener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			String fileUrl = wjinfos.get(position).getUrl();
			String name = URLDecoder.decode(fileUrl.substring(fileUrl.lastIndexOf("/") + 1));
			String fileName = CommonUtil.FILEPATH + name;
			File file = new File(fileName);
			if (file.exists()) {
				Intent intent = new Intent(getActivity(), PDFReadActivity.class);
				intent.putExtra("WenJianInfo", wjinfos.get(position));
				startActivity(intent);
			} else {
				FileDownAsyncTask asyncTask = new FileDownAsyncTask(mContext, wjinfos.get(position).getWenJianName());
				asyncTask.execute(fileUrl);
				Toast.makeText(getActivity(), "正在下载 " + wjinfos.get(position).getWenJianName(), Toast.LENGTH_SHORT)
						.show();
			}
		}
	};
}
