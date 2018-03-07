package com.android.renly.picass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class picassotranfromation extends AppCompatActivity {

    @Bind(R.id.lv_picasso_transfromation)
    ListView lvPicassoTransfromation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picassotranfromation);
        ButterKnife.bind(this);
        
        initDate();
    }

    private void initDate() {
        List<String> data = new ArrayList<>();

        for(int i=1;i<=36;i++){
            data.add(i+"");
        }
        //初始化view
        PicassoTransformationAdapter picassoTransformationAdapter = new PicassoTransformationAdapter(picassotranfromation.this);
        lvPicassoTransfromation.setAdapter(picassoTransformationAdapter);

    }
}
