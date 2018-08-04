package com.android.renly.pictoword;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.ByteArrayOutputStream;
import java.io.File;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends Activity {
    private static final int NONE = 0;
    private static final int PHOTO_GRAPH = 1;// 拍照
    private static final int PHOTO_ZOOM = 2; // 缩放
    private static final int PHOTO_RESOULT = 3;// 结果
    private static final String IMAGE_UNSPECIFIED = "image/*";
    private ImageView imageView = null;
    private Button btnPhone = null;
    private Button btnTakePicture = null;
    private String change_path = "/edu_YunZhi";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        btnPhone = (Button) findViewById(R.id.btnPhone);
        btnPhone.setOnClickListener(onClickListener);
        btnTakePicture = (Button) findViewById(R.id.btnTakePicture);
        btnTakePicture.setOnClickListener(onClickListener);

        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnPhone) { //从相册获取图片
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
                startActivityForResult(intent, PHOTO_ZOOM);
            } else if (v == btnTakePicture) { //从拍照获取图片
                String filePath = Environment.getExternalStorageDirectory() + change_path;
                File localFile = new File(filePath);
                if (!localFile.exists()) {
                    localFile.mkdir();
                }
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory() + change_path
                        , "temp.jpg")));
                startActivityForResult(intent, PHOTO_GRAPH);
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == NONE){
            return;
        }
        // 拍照
        if (requestCode == PHOTO_GRAPH) {
            // 设置文件保存路径
            String filePath = Environment.getExternalStorageDirectory() + change_path;
            File localFile = new File(filePath);
            if (!localFile.exists()) {
                localFile.mkdir();
            }
            File picture = new File(Environment.getExternalStorageDirectory() + change_path
                    + "/temp.jpg");
            startPhotoZoom(Uri.fromFile(picture));
        }
        if (data == null){
            return;
        }
        // 读取相册缩放图片
        if (requestCode == PHOTO_ZOOM) {
            startPhotoZoom(data.getData());
        }
        // 处理结果
        if (requestCode == PHOTO_RESOULT) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0-100)压缩文件
                bytes = stream.toByteArray();
                //此处可以把Bitmap保存到sd卡中
                imageView.setImageBitmap(photo); //把图片显示在ImageView控件上
                picToWord();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    String sign;
    byte[] bytes;
    AsyncHttpClient client;
    RequestParams params;
    private void picToWord() {
        client = new AsyncHttpClient();
        setHeader();
        params = new RequestParams();
        setParams();
        client.setTimeout(30000);
        Log.e("print",params.toString());
        client.post(this, Secret.url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                JSONObject object = JSON.parseObject(response);
                int code = object.getInteger("code");
                String msg = object.getString("message");
                JSONArray items = object.getJSONArray("data.items");
                String content = "";
                for(int i = 0;i < items.size(); i++){
                    JSONObject obj = items.getJSONObject(i);
                    content += obj.getString("itemstring");
                }
                Log.e("print","code:" + code + " msg:" + msg + " content:" + content);
                Toast.makeText(MainActivity.this, "code:" + code + " msg:" + msg + " content:" + content, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(MainActivity.this, "网络开小差惹", Toast.LENGTH_SHORT).show();
//                Log.e("print","网络开小差a" + responseBody.toString());
                Log.e("print","网络开小差b" + statusCode);
                Log.e("print","网络开小差c" + error.toString());
            }
        });
    }

    public void setHeader() {
        // 腾讯云文字识别服务器域名
        client.addHeader("Host","recognition.image.myqcloud.com");

        // 根据不同接口选择：
        // 1. 使用 application/json 格式，参数为 url 或 base64，其值为图片链接或图片base64编码；
        // 2. 使用 multipart/form-data 格式，参数为 image，其值为图片的二进制内容。
//        client.addHeader("content-type","multipart/form-data");
        client.addHeader("Content-Type","application/json");

        // 多次有效签名，用于鉴权
        try {
            sign = Sign.appSign(Secret.appid,Secret.secretid,Secret.secretkey,"",Secret.addtime);
            client.addHeader("Authorization",sign);
            Log.e("print","sign == " + sign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.addHeader("Content-Length","350");
    }

    public void setParams() {
        params.put("appid",Secret.appid);
//        params.put("image",bytes);
        params.put("url","http://test-1254540501.cosgz.myqcloud.com/%E6%89%8B%E5%86%99%E4%BD%93.jpg");
        params.put("bucket","test");
    }

    /**
     * 收缩图片
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_RESOULT);
        Log.e("print","startActivityForResult(intent, PHOTO_RESOULT)");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}