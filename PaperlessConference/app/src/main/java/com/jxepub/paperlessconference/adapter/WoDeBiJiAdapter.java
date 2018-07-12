package com.jxepub.paperlessconference.adapter;

import java.util.List;

import com.jxepub.paperlessconference.R;
import com.jxepub.paperlessconference.entity.BiJi;
import com.jxepub.paperlessconference.entity.ScrSize;
import com.jxepub.paperlessconference.util.ScrUtil;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WoDeBiJiAdapter extends BaseAdapter {
	Context mContext;
	List<BiJi> sus;

	public WoDeBiJiAdapter(Context mContext, List<BiJi> sus) {
		this.mContext = mContext;
		this.sus = sus;
	}

	@Override
	public int getCount() {
		return sus.size();
	}

	@Override
	public Object getItem(int position) {
		return sus.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.gv_wodebiji_item, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (position == 0) {
			holder.rel_wdwj_bg.setBackgroundColor(Color.WHITE);
			holder.gv_wodebiji_img.setImageResource(R.drawable.add_biji);
			holder.gv_wodebiji_tv.setText("新增笔记本");
		} else {
			holder.gv_wodebiji_img.setImageURI(Uri.parse(sus.get(position).getPath()));
			holder.gv_wodebiji_tv.setText(sus.get(position).getName());
		}
		return convertView;
	}

	class ViewHolder {
		ImageView gv_wodebiji_img;
		TextView gv_wodebiji_tv;
		RelativeLayout rel_wdwj_bg;

		public ViewHolder(View v) {
			gv_wodebiji_img = (ImageView) v.findViewById(R.id.gv_wodebiji_img);
			ScrSize size = ScrSize.getInstance(mContext);
			LayoutParams params = gv_wodebiji_img.getLayoutParams();
			params.width = ScrUtil.GetBJWidth(size.width);
			params.height = ScrUtil.GetBJHeight(size.width);
			gv_wodebiji_img.setLayoutParams(params);
			gv_wodebiji_tv = (TextView) v.findViewById(R.id.gv_wodebiji_tv);
			rel_wdwj_bg = (RelativeLayout) v.findViewById(R.id.rel_wdwj_bg);
		}
	}

}
