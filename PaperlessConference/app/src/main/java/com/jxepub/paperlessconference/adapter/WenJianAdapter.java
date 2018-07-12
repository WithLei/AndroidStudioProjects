package com.jxepub.paperlessconference.adapter;

import java.util.List;

import com.jxepub.paperlessconference.R;
import com.jxepub.paperlessconference.entity.TestEntity;
import com.jxepub.paperlessconference.entity.WenJianInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WenJianAdapter extends BaseAdapter {
	Context mContext;
	List<WenJianInfo> entities;

	public WenJianAdapter(Context mContext, List<WenJianInfo> entities) {
		this.entities = entities;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return entities.size();
	}

	@Override
	public Object getItem(int position) {
		return entities.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.lv_wenjian_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.wjName.setText(entities.get(position).getWenJianName());
		return convertView;
	}

	class ViewHolder {
		TextView wjName;

		public ViewHolder(View v) {
			wjName = (TextView) v.findViewById(R.id.lv_wjnmae);
		}
	}
}
