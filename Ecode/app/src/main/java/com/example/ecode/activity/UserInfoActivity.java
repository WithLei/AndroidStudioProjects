package com.example.ecode.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecode.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInfoActivity extends Activity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.user_info_icon)
    ImageView userInfoIcon;
    @BindView(R.id.company_right)
    ImageView companyRight;
    @BindView(R.id.nick_right)
    ImageView nickRight;
    @BindView(R.id.sex_right)
    ImageView sexRight;
    @BindView(R.id.birthday_right)
    ImageView birthdayRight;
    @BindView(R.id.phone_right)
    ImageView phoneRight;
    @BindView(R.id.email_right)
    ImageView emailRight;
    @BindView(R.id.address_right)
    ImageView addressRight;
    @BindView(R.id.jianjie_right)
    ImageView jianjieRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.user_info_icon, R.id.company_right, R.id.nick_right, R.id.sex_right, R.id.birthday_right, R.id.phone_right, R.id.email_right, R.id.address_right, R.id.jianjie_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                fileList();
                break;
            case R.id.user_info_icon:
                break;
            case R.id.company_right:
                break;
            case R.id.nick_right:
                break;
            case R.id.sex_right:
                break;
            case R.id.birthday_right:
                break;
            case R.id.phone_right:
                break;
            case R.id.email_right:
                break;
            case R.id.address_right:
                break;
            case R.id.jianjie_right:
                break;
        }
    }
}
