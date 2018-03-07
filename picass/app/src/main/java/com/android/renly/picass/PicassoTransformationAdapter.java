package com.android.renly.picass;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Renly on 2018/3/7.
 */

class PicassoTransformationAdapter extends BaseAdapter{
    private Context mContext;
    private List<String>data;

    public PicassoTransformationAdapter(Context mContext,List<String>data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if(convertView == null){
            convertView = View.inflate(mContext,R.layout.item_picasso_transformation,null);

            holder = new viewHolder(convertView);

            convertView.setTag(holder);
        }
        else holder = (viewHolder)convertView.getTag();

        //显示名称
        holder.name.setText("item"+(position+1));
        return convertView;
    }

    class viewHolder{
        @Bind(R.id.iv_picasso_transformation)
        ImageView iv;
        @Bind(R.id.tv_picasso_transformation)
        TextView name;

        public viewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
