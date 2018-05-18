package com.example.ecode.fragment;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ecode.R;
import com.example.ecode.activity.WbProjectActivity;
import com.example.ecode.common.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 丶希泽尔 on 2018/5/16.
 */

public class WaibaoFragment extends BaseFragment {

    private TextView tvTitle;
    private ListView waibaoList;
    private List<String> tasks;
    private List<Integer> stars;

    @Override
    protected int getLayout() {
        return R.layout.fragment_waibao;
    }

    @Override
    protected void initData() {

        tasks=new ArrayList<>();
        tasks.add("任务三需要面部识别");
        tasks.add("任务三比较里厉害");
        tasks.add("我的安全级别最高");
        tasks.add("为什么你们都在说任务三");
        tasks.add("我需要面部识别吗");

        stars=new ArrayList<>();
        stars.add(3);
        stars.add(1);
        stars.add(5);
        stars.add(2);
        stars.add(4);

        waibaoList.setAdapter(new MyAdapter());

        waibaoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(getActivity(),WbProjectActivity.class);
                intent.putExtra("task",tasks.get(i));
                intent.putExtra("star",stars.get(i));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void findViews(View view) {

        tvTitle=view.findViewById(R.id.tv_title);
        waibaoList=view.findViewById(R.id.waibao_list);
    }

    @Override
    protected void initTitle() {

        tvTitle.setText("项目大厅");
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return stars.size();
        }

        @Override
        public Object getItem(int i) {
            return stars.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder holder;
            if(view==null){

                view=View.inflate(getActivity(),R.layout.item_waibao,null);
                holder=new ViewHolder();
                holder.tvTask=view.findViewById(R.id.tv_task);
                holder.itemStar1=view.findViewById(R.id.item_star1);
                holder.itemStar2=view.findViewById(R.id.item_star2);
                holder.itemStar3=view.findViewById(R.id.item_star3);
                holder.itemStar4=view.findViewById(R.id.item_star4);
                holder.itemStar5=view.findViewById(R.id.item_star5);
                view.setTag(holder);
            }else{
                holder= (ViewHolder) view.getTag();
            }

            holder.tvTask.setText(tasks.get(i));
            if(stars.get(i)<5){
                holder.itemStar5.setVisibility(View.INVISIBLE);
            }
            if(stars.get(i)<4){
                holder.itemStar4.setVisibility(View.INVISIBLE);
            }
            if(stars.get(i)<3){
                holder.itemStar3.setVisibility(View.INVISIBLE);
            }
            if(stars.get(i)<2){
                holder.itemStar2.setVisibility(View.INVISIBLE);
            }
            if(stars.get(i)<1){
                holder.itemStar1.setVisibility(View.INVISIBLE);
            }

            return view;
        }

        class ViewHolder{

            private TextView tvTask;
            private ImageView itemStar1;
            private ImageView itemStar2;
            private ImageView itemStar3;
            private ImageView itemStar4;
            private ImageView itemStar5;
        }
    }
}
