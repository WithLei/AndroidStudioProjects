package com.jxepub.paperlessconference.fragment;

import java.io.File;
import java.net.URLDecoder;
import java.util.List;
import com.jxepub.paperlessconference.R;
import com.jxepub.paperlessconference.activity.MainActivity;
import com.jxepub.paperlessconference.activity.PDFReadActivity;
import com.jxepub.paperlessconference.adapter.RenYuanAdapter;
import com.jxepub.paperlessconference.adapter.WenJianAdapter;
import com.jxepub.paperlessconference.entity.HuiYiInfo;
import com.jxepub.paperlessconference.entity.WenJianInfo;
import com.jxepub.paperlessconference.util.CommonUtil;
import com.jxepub.paperlessconference.util.FileDownAsyncTask;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class FragLiShiHuiYiXQ extends Fragment implements OnClickListener {
	private View view;
	private HuiYiInfo info;
	private TextView frg_lishi_title, lishi_faqibumen, lishi_didian, lishi_huiyishijian, lishi_huiyishichang,
			lishi_totalry, lishi_beizhuneirong;
	private GridView lishi_renyuan_gv;
	private ListView frg_lishi_lv;
	private List<WenJianInfo> wjinfos;
	private RelativeLayout btn_back_xq;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_lishihuiyixiangqing, null);
		init();
		return view;
	}

	void init() {
		Bundle bundle = getArguments();
		info = (HuiYiInfo) bundle.getSerializable("HuiYiInfo");

		frg_lishi_title = (TextView) view.findViewById(R.id.frg_lishi_title);
		lishi_faqibumen = (TextView) view.findViewById(R.id.lishi_faqibumen);
		lishi_didian = (TextView) view.findViewById(R.id.lishi_didian);
		lishi_huiyishijian = (TextView) view.findViewById(R.id.lishi_huiyishijian);
		lishi_huiyishichang = (TextView) view.findViewById(R.id.lishi_huiyishichang);
		lishi_totalry = (TextView) view.findViewById(R.id.lishi_totalry);
		lishi_beizhuneirong = (TextView) view.findViewById(R.id.lishi_beizhuneirong);
		lishi_renyuan_gv = (GridView) view.findViewById(R.id.lishi_renyuan_gv);
		frg_lishi_lv = (ListView) view.findViewById(R.id.frg_lishi_lv);
		btn_back_xq = (RelativeLayout) view.findViewById(R.id.btn_back_xq);
		btn_back_xq.setOnClickListener(this);

		frg_lishi_title.setText(info.getHuiYiName());
		lishi_faqibumen.setText("发起人：" + info.getFaQiRen());
		lishi_huiyishijian.setText("会议时间：" + info.getHuiYiTimeK().substring(0, info.getHuiYiTimeK().lastIndexOf(":")));
		lishi_huiyishichang.setText("会议时长：" + info.getShiChang() + "小时");
		lishi_totalry.setText("共 " + info.getHuiYiRenShu() + " 人");
		lishi_beizhuneirong.setText(info.getHuiYiNeiRong());
		lishi_didian.setText("地点：" + info.getHuiYiShiNum());
		RenYuanAdapter adapter = new RenYuanAdapter(getActivity(), info.getHuiYiRenYuan().split("\\|"));
		lishi_renyuan_gv.setAdapter(adapter);
		adapter.notifyDataSetChanged();

		wjinfos = info.getWjInfos();
		WenJianAdapter jianAdapter = new WenJianAdapter(getActivity(), wjinfos);
		frg_lishi_lv.setAdapter(jianAdapter);
		adapter.notifyDataSetChanged();
		frg_lishi_lv.setOnItemClickListener(listener);

	}

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
				FileDownAsyncTask asyncTask = new FileDownAsyncTask(getActivity(),
						wjinfos.get(position).getWenJianName());
				asyncTask.execute(fileUrl);
				Toast.makeText(getActivity(), "正在下载 " + wjinfos.get(position).getWenJianName(), Toast.LENGTH_SHORT)
						.show();
			}
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_back_xq:
			MainActivity activity = (MainActivity) getActivity();
			activity.viewPager.setCurrentItem(1);
			break;
		}
	}
}
