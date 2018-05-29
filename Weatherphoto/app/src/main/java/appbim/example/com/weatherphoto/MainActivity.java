package appbim.example.com.weatherphoto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText citynameEditText;
    private Button searchWeatherButton;
    private TextView citynametTextView;
    private TextView weahterTextView;
    private TextView tempTextView;
    private TextView h_tempTextView;
    private TextView l_tempTextView;
    String jsonString1;
    String jsonString2;
    ProgressDialog progressDialog;
    private static final int SET = 1;
    private static final int RET = 2;
    private static final int COMPLETE = 3;
    List<Map<String, Object>> all;
    Map<String, Object> map = new HashMap<String, Object>();
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SET:
                    try {
                        JSONObject jo = new JSONObject(jsonString1);
                        JSONObject jsonObject = jo.optJSONObject("weatherinfo");
                        Log.e("print","here");
                        map.put("cityName", jsonObject.optString("city"));
                        map.put("weather", jsonObject.optString("weather"));
                        map.put("l_temp", jsonObject.optString("temp1"));
                        map.put("h_temp", jsonObject.optString("temp2"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case RET:
                    try {
                        JSONObject jo = new JSONObject(jsonString2);
                        JSONObject jsonObject = jo.optJSONObject("weatherinfo");
                        map.put("temp",jsonObject.optString("temp"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case COMPLETE:
                    Log.e("print","111" + map.get("cityName").toString());
                        citynametTextView.setText(map.get("cityName").toString());
                        weahterTextView.setText(map.get("weather").toString());
                        tempTextView.setText(map.get("temp").toString());
                        h_tempTextView.setText(map.get("l_temp").toString());
                        l_tempTextView.setText(map.get("h_temp").toString());

                    break;
            }

        }

    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // 生命周期方法
        super.setContentView(R.layout.layout); // 设置要使用的布局管理器
        citynameEditText = (EditText) findViewById(R.id.myedit);
        searchWeatherButton = (Button) findViewById(R.id.searchweather);
        citynametTextView = (TextView) findViewById(R.id.city);
        weahterTextView = (TextView) findViewById(R.id.weather);
        tempTextView = (TextView) findViewById(R.id.temp);
        h_tempTextView = (TextView) findViewById(R.id.h_temp);
        l_tempTextView = (TextView) findViewById(R.id.l_temp);



        searchWeatherButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                new Thread(new NewThread()).start();
                Log.d("按键", "Success");


            }
        });
    }

    private class NewThread implements Runnable {

        public void run() {

            String cityID = null;
            switch (citynameEditText.getText().toString()){
                case "北京":
                    cityID = "101010100";
                    break;
                case "上海":
                    cityID = "101020100";
                    break;
                case "杭州":
                    cityID = "101210101";
                    break;
                case "广州":
                    cityID = "101280101";
                    break;
            }

            String address1 = "http://www.weather.com.cn/data/cityinfo/" + cityID + ".html";
            String address2 = "http://www.weather.com.cn/data/sk/" + cityID + ".html";

            HttpDownloader httpDownloader = new HttpDownloader();
            jsonString1 = httpDownloader.download(address1);
            jsonString2 = httpDownloader.download(address2);

            Message msg1 = MainActivity.this.handler.obtainMessage(MainActivity.SET);
            Message msg2 = MainActivity.this.handler.obtainMessage(MainActivity.RET);
            Message msg = MainActivity.this.handler.obtainMessage(MainActivity.COMPLETE);

            MainActivity.this.handler.sendMessage(msg1);
            MainActivity.this.handler.sendMessage(msg2);
            MainActivity.this.handler.sendMessage(msg);

            String weather = weahterTextView.getText().toString();
            LinearLayout background = findViewById(R.id.background);
            if(weather.indexOf("雨") != -1)
                background.setBackground(getResources().getDrawable(R.mipmap.ic_launcher));
            else if(weather.indexOf("风") != -1)
                background.setBackground(getResources().getDrawable(R.drawable.ic_launcher_background));
        }
    }
}