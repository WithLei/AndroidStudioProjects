package com.android.renly.picass;

import android.content.Context;
import android.provider.SyncStateContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
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

            convertView.setTag(holder);
        }
        else{
            holder = (viewHolder)convertView.getTag();
        }
        //名称
        holder.name.setText("item"+(position+1));
        //加载图片
        Picasso.with(mContext)
                .load(Constants.IMAGES[position])
                .placeholder(R.drawable.user1)
                .error(R.drawable.user4)
                .into(holder.iv);

        return convertView;
    }

    class viewHolder{
        @Bind(R.id.iv_picasso_item)
        ImageView iv;

        @Bind(R.id.tv_picasso_item)
        TextView name;


        public viewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
