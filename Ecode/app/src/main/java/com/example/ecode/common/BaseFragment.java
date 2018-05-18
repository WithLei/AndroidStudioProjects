package com.example.ecode.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 丶希泽尔 on 2018/5/16.
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=View.inflate(getActivity(),getLayout(),null);
        findViews(view);
        initTitle();
        initData();
        return view;
    }

    protected abstract int getLayout();

    protected abstract void initData();

    protected abstract void findViews(View view);

    protected abstract void initTitle();
}
