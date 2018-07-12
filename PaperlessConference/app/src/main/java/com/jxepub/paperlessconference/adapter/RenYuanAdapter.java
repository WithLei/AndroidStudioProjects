package com.jxepub.paperlessconference.adapter;

import com.jxepub.paperlessconference.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RenYuanAdapter extends BaseAdapter {
	Context mContext;
	String[] names;

	public RenYuanAdapter(Context mContext, String[] names) {
		this.mContext = mContext;
		this.names = names;
	}

	@Override
	public int getCount() {
		return names.length;
	}

	@Override
	public Object getItem(int position) {
		return names[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.gv_renyuan_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.name.setText(names[position]);
		return convertView;
	}

	class ViewHolder {
		TextView name;

		public ViewHolder(View v) {
			name = (TextView) v.findViewById(R.id.renyuanxm);
		}
	}

}
