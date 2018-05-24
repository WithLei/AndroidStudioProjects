package com.android.renly.pjltest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class MainActivity2Activity extends AppCompatActivity {
    TextView infomation;
    private ListView listView;
    private int[]ivR={
            R.drawable.canguan1,R.drawable.canguan2
    };
    private String[]tvR={
            "“源味鲜”坐落在美丽的泉城济南，它以健康的饮食理念，独特美味的口感和优质的服务，迅速赢得了广大消费者的认可和赞誉","浦之舟餐厅位于上海市浦东新区滨江大道3688号，人均价格811元，营业时间从早上9：30至21：00"
    };
    public static String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        infomation=(TextView)findViewById(R.id.infotext);
        try{
            String info=getIntent().getStringExtra("EDITNFO");
            infomation.setText(info);
        }catch (Exception e){
            Log.i(TAG, "intent解析错误");
        }
        listView=(ListView)findViewById(R.id.list_item);
        List<HashMap<String,Object>>arrayList=new ArrayList<>();
        for(int i=0;i<ivR.length;i++)
        {
            HashMap<String,Object>hashMap=new HashMap<>();
            hashMap.put("ITEMIV",ivR[i]);
            hashMap.put("ITEMTV",tvR[i]);
            arrayList.add(hashMap);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,arrayList,R.layout.activity_main_activity2,new String[]{
                "ITEMIV","ITEMTV"},new int[]{R.id.list_item_iv,R.id.list_item_v});
        listView.setAdapter(simpleAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
