package com.example.ecode.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ecode.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WbFrogressActivity extends Activity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.progress_list)
    ListView progressList;

    private String taskName;        //任务名称
    private int star;               //任务等级

    private List<String> progressContents;
    private List<String> progressTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wb_frogress);
        ButterKnife.bind(this);

        Intent intent=getIntent();
        taskName=intent.getStringExtra("task");
        star=intent.getIntExtra("star",0);

        initData();
    }

    private void initData() {

        progressContents=new ArrayList<>();
        progressContents.add("完成项目设计");
        progressContents.add("基本完成数据库搭建");
        progressContents.add("这是一个比较长的任务名称，用来测试的。这是一个比较长的任务名称，用来测试的。这是一个比较长的任务名称，用来测试的。");

        progressTimes=new ArrayList<>();
        progressTimes.add("2018-01-25 14:00");
        progressTimes.add("2018-01-27 21:00");
        progressTimes.add("2018-01-27 21:00");

        progressList.setAdapter(new MyAdapter());
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return progressContents.size();
        }

        @Override
        public Object getItem(int i) {
            return progressContents.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder holder;
            if(view==null){
                view=View.inflate(WbFrogressActivity.this,R.layout.item_progress,null);
                holder=new ViewHolder();
                holder.progressContent=view.findViewById(R.id.progress_content);
                holder.progressTime=view.findViewById(R.id.prorgress_time);
                view.setTag(holder);
            }else{
                holder= (ViewHolder) view.getTag();
            }

            holder.progressContent.setText(progressContents.get(i));
            holder.progressTime.setText(progressTimes.get(i));

            return view;
        }

        class ViewHolder{

            private TextView progressContent;
            private TextView progressTime;
        }
    }
}
