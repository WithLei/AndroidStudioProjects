package com.jxepub.paperlessconference.adapter;

import java.util.List;

import com.jxepub.paperlessconference.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

// public class DaoHangAdapter extends BaseAdapter {
// Context mContext;
// List<String> indexs;
//
// public DaoHangAdapter(Context mContext, List<String> indexs) {
// this.mContext = mContext;
// this.indexs = indexs;
// }
//
// @Override
// public int getCount() {
// return indexs.size();
// }
//
// @Override
// public Object getItem(int position) {
// return indexs.get(position);
// }
//
// @Override
// public long getItemId(int position) {
// return position;
// }
//
// @Override
// public View getView(int position, View convertView, ViewGroup parent) {
// ViewHolder holder = null;
// if (convertView == null) {
// convertView = LayoutInflater.from(mContext).inflate(R.layout.gv_daohang_item,
// null);
// holder = new ViewHolder(convertView);
// convertView.setTag(holder);
// } else {
// holder = (ViewHolder) convertView.getTag();
// }
// holder.daohang_index.setText(indexs.get(position));
// holder.daohang_bg.setOnClickListener(new MyClickListner(holder, position));
// return convertView;
// }
//
// class MyClickListner implements OnClickListener {
// ViewHolder holder;
// int position;
//
// public MyClickListner(ViewHolder holder, int position) {
// this.holder = holder;
// this.position = position;
// }
//
// @Override
// public void onClick(View v) {
// holder.daohang_bg.setBackgroundColor(Color.BLACK);
// holder.daohang_index.setTextColor(Color.WHITE);
//
// }
// }
//
// class ViewHolder {
// RelativeLayout daohang_bg;
// TextView daohang_index;
//
// public ViewHolder(View v) {
// daohang_bg = (RelativeLayout) v.findViewById(R.id.daohang_bg);
// daohang_index = (TextView) v.findViewById(R.id.daohang_index);
// }
// }
// }
