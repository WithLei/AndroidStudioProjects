package com.android.renly.aleipay.common;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.renly.aleipay.util.UIUtils;

import butterknife.ButterKnife;

/**
 * Created by Renly on 2018/3/24.
 */

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIUtils.getView(getLayoutid());
        ButterKnife.bind(this,view);
        initTitle();
        initData();
        return view;
    }
    //初始化title
    protected abstract void initTitle();

    //初始化界面的数据
    protected abstract void initData(String content);
    public abstract int getLayoutid();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
