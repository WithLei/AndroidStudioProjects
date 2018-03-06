package com.android.renly.picass;

import android.content.Context;
import android.provider.SyncStateContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import butterknife.ButterKnife;

/**
 * Created by Renly on 2018/3/6.
 */

public class picassoListviewAdapter extends BaseAdapter {
    Context mContext;

    public picassoListviewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return Constants.IMAGES.length;
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
            convertView = View.inflate(mContext,R.layout.item_picasso_listview,null);

            holder = new viewHolder(convertView);
        }
        return convertView;
    }

    class viewHolder{


        public viewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
