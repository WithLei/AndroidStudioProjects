package com.android.renly.picass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListviewPicasso extends AppCompatActivity {

    @Bind(R.id.lv_picasso_list)
    ListView lvPicassoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_picasso);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        picassoListviewAdapter ListviewAdapter = new picassoListviewAdapter(this);

        lvPicassoList.setAdapter(ListviewAdapter);
    }
}
