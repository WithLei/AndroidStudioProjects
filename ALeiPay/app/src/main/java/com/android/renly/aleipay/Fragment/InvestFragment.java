package com.android.renly.aleipay.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.renly.aleipay.R;
import com.android.renly.aleipay.common.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Renly on 2018/3/17.
 */

public class InvestFragment extends BaseFragment {
    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_title_setting)
    ImageView ivTitleSetting;

    public void initTitle() {
        ivTitleBack.setVisibility(View.GONE);
        tvTitle.setText("财富");
        ivTitleSetting.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.fragment_invest;
    }

}
