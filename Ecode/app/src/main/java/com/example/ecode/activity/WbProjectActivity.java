package com.example.ecode.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WbProjectActivity extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.waibao_progress)
    LinearLayout waibaoProgress;
    @BindView(R.id.waibao_list)
    LinearLayout waibaoList;
    @BindView(R.id.waibao_discuss)
    LinearLayout waibaoDiscuss;


    private String taskName;    //任务名称
    private int star;           //任务等级


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wb_project);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        taskName = intent.getStringExtra("task");
        star = intent.getIntExtra("star", 0);

        tvTitle.setText("外包项目");

    }

    @OnClick({R.id.back, R.id.waibao_progress, R.id.waibao_list, R.id.waibao_discuss})
    public void onViewClicked(View view) {

        Intent intent;
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.waibao_progress:
                intent=new Intent(this,WbFrogressActivity.class);
                intent.putExtra("task",taskName);
                intent.putExtra("star",star);
                startActivity(intent);
                break;
            case R.id.waibao_list:
                intent=new Intent(this,TaskListActivity.class);
                intent.putExtra("task",taskName);
                intent.putExtra("star",star);
                startActivity(intent);
                break;
            case R.id.waibao_discuss:
                Toast.makeText(this,"项目讨论",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
