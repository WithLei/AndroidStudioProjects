package com.example.ecode.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecode.R;
import com.example.ecode.common.BaseFragment;

/**
 * Created by 丶希泽尔 on 2018/5/16.
 */

public class CheckonworkFragment extends BaseFragment implements View.OnClickListener {

    private TextView tvTitle;

    private TextView tvCheckTime;
    private ImageView ivIdentity;
    private TextView tvBeginCheck;

    @Override
    protected int getLayout() {
        return R.layout.fragment_checkonwork;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void findViews(View view) {

        tvTitle=view.findViewById(R.id.tv_title);
        tvBeginCheck=view.findViewById(R.id.tv_begin_check);
        ivIdentity=view.findViewById(R.id.iv_identity);

        ivIdentity.setOnClickListener(this);
        tvBeginCheck.setOnClickListener(this);
    }

    @Override
    protected void initTitle() {

        tvTitle.setText("工作考勤");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_identity:
                Toast.makeText(getActivity(),"人脸识别",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_begin_check:
                Toast.makeText(getActivity(),"开始考勤",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
