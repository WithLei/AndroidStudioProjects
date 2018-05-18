package com.example.ecode.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecode.R;
import com.example.ecode.common.BaseFragment;

/**
 * Created by 丶希泽尔 on 2018/5/16.
 */

public class MsgFragment extends BaseFragment implements View.OnClickListener {

    private TextView tvTitle;

    private LinearLayout msgSystem;
    private LinearLayout msgInform;
    private LinearLayout msgGroup;

    @Override
    protected int getLayout() {
        return R.layout.fragment_msg;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void findViews(View view) {

        tvTitle=view.findViewById(R.id.tv_title);

        msgSystem=view.findViewById(R.id.msg_system);
        msgInform=view.findViewById(R.id.msg_inform);
        msgGroup=view.findViewById(R.id.msg_group);

        msgGroup.setOnClickListener(this);
        msgSystem.setOnClickListener(this);
        msgInform.setOnClickListener(this);
    }

    @Override
    protected void initTitle() {
        tvTitle.setText("消息列表");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.msg_system:
                Toast.makeText(getActivity(),"系统消息",Toast.LENGTH_SHORT).show();
                break;

            case R.id.msg_inform:
                Toast.makeText(getActivity(),"公司消息",Toast.LENGTH_SHORT).show();
                break;

            case R.id.msg_group:
                Toast.makeText(getActivity(),"项目讨论",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
