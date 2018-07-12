package com.jxepub.paperlessconference.adapter;

import java.util.List;

import com.jxepub.paperlessconference.R;
import com.jxepub.paperlessconference.entity.HuiYiInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LiShiAdapter extends BaseAdapter {
	Context mContext;
	List<HuiYiInfo> infos;

	public LiShiAdapter(Context mContext, List<HuiYiInfo> infos) {
		this.mContext = mContext;
		this.infos = infos;
	}

	@Override
	public int getCount() {
		return infos.size();
	}

	@Override
	public Object getItem(int position) {
		return infos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.lv_lishihuiyi_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		HuiYiInfo info = infos.get(position);
		holder.name.setText(info.getHuiYiName());
		holder.faqiren.setText("发起人：" + info.getFaQiRen());
		holder.didian.setText("地点：" + info.getHuiYiShiNum());
		holder.shijian.setText("会议时间：" + info.getHuiYiTimeK().substring(0, info.getHuiYiTimeK().lastIndexOf(":")));
		holder.shichang.setText("会议时长：" + info.getShiChang());

		return convertView;
	}

	class ViewHolder {
		TextView name, faqiren, didian, shijian, shichang;

		public ViewHolder(View v) {
			name = (TextView) v.findViewById(R.id.lishi_huiyiname);
			faqiren = (TextView) v.findViewById(R.id.lishi_huiyifaqiren);
			didian = (TextView) v.findViewById(R.id.lishi_huiyididian);
			shijian = (TextView) v.findViewById(R.id.lishi_huiyishijian);
			shichang = (TextView) v.findViewById(R.id.lishi_huiyishichang);
		}
	}
}
