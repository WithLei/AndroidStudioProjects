package com.android.renly.aleipay.Activity;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.android.renly.aleipay.R;

import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
