package com.example.ecode.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecode.R;
import com.example.ecode.activity.UserInfoActivity;
import com.example.ecode.common.BaseFragment;

/**
 * Created by 丶希泽尔 on 2018/5/16.
 */

public class MeFragment extends BaseFragment implements View.OnClickListener {

    private TextView tvTitle;

    private ImageView userIcon;     //用户头像
    private TextView tvUserName;    //用户名
    private TextView tvCompany;     //用户公司
    private LinearLayout meUserInformation;     //个人信息
    private LinearLayout meHistory;             //历史项目
    private LinearLayout meRecord;              //项目记录
    private LinearLayout mePwdVerify;           //修改密码
    private LinearLayout meLogout;              //退出登录

    @Override
    protected int getLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void findViews(View view) {

        tvTitle=view.findViewById(R.id.tv_title);

        userIcon=view.findViewById(R.id.user_icon);
        tvUserName=view .findViewById(R.id.tv_user_name);
        tvCompany=view.findViewById(R.id.tv_company);
        meUserInformation=view.findViewById(R.id.me_user_information);
        meHistory=view.findViewById(R.id.me_history);
        meRecord=view.findViewById(R.id.me_record);
        mePwdVerify=view.findViewById(R.id.me_pwd);
        meLogout=view.findViewById(R.id.me_logout);

        meUserInformation.setOnClickListener(this);
        meHistory.setOnClickListener(this);
        meRecord.setOnClickListener(this);
        mePwdVerify.setOnClickListener(this);
        meLogout.setOnClickListener(this);


    }

    @Override
    protected void initTitle() {

        tvTitle.setText("个人中心");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.me_user_information:
                //Toast.makeText(getActivity(),"个人信息",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
                break;
            case R.id.me_history:
                Toast.makeText(getActivity(),"历史项目",Toast.LENGTH_SHORT).show();
                break;
            case R.id.me_record:
                Toast.makeText(getActivity(),"考勤记录",Toast.LENGTH_SHORT).show();
                break;
            case R.id.me_pwd:
                Toast.makeText(getActivity(),"密码修改",Toast.LENGTH_SHORT).show();
                break;
            case R.id.me_logout:
                Toast.makeText(getActivity(),"退出登录",Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
