package com.android.renly.listview;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv_main;
    private List<AppInfo>data;
    private AppAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化成员变量
        lv_main = findViewById(R.id.lv_item);
        data = getAllAppInfos();
        adapter = new AppAdapter();
        //显示列表
        lv_main.setAdapter(adapter);

        //给ListView设置item的点击监听
        lv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //提示当前行的应用名称
                String appName = data.get(position).getAppName();
                //提示
                Toast.makeText(MainActivity.this, appName, Toast.LENGTH_SHORT).show();
            }
        });
        //长按监听
        lv_main.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String appName = data.get(position).getAppName();
                //删除当前行
                //删除当前行的数据
                data.remove(position);
                //更新列表
//                lv_main.setAdapter(adapter);//会恢复初始位置显示列表,不会使用缓存的数据
                adapter.notifyDataSetChanged();//通知更新列表，当前位置更新,会使用缓存的数据
                //提示当前行的应用名称
                //提示
                Toast.makeText(MainActivity.this, appName + "已删除", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    class AppAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //如果convertView是null,加载item的布局文件
            if(convertView==null){
                convertView = View.inflate(MainActivity.this,R.layout.item_main,null);
            }
            //得到当前行数据对象
            AppInfo appInfo = data.get(position);
            //得到当前行需要更新的子view对象
            ImageView imageView = convertView.findViewById(R.id.iv_item_icon);
            TextView textView = convertView.findViewById(R.id.tv_item_name);
            //给视图设置对象
            imageView.setImageDrawable(appInfo.getIcon());
            textView.setText(appInfo.getAppName());
            //返回convertView
            return convertView;
        }
    }

    protected List<AppInfo> getAllAppInfos(){
        List<AppInfo> list = new ArrayList<>();
        //得到应用的packageManager
        PackageManager packageManager = getPackageManager();
        //创建一个主界面的Intent
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        //得到包含应用信息的列表
        List<ResolveInfo> ResolveInfos = packageManager.queryIntentActivities(intent,0);
        for(ResolveInfo ri : ResolveInfos){
            //得到包名
            String packageName = ri.activityInfo.packageName;
            //得到图标
            Drawable icon = ri.loadIcon(packageManager);
            //得到应用名称
            String appName = ri.loadLabel(packageManager).toString();
            //封装应用信息对象
            AppInfo appInfo = new AppInfo(icon,appName,packageName);
            //添加到list
            list.add(appInfo);
        }
        return list;
    }
}
