package appbim.example.com.weatherphoto;

/**
 * Created by Administrator on 2018/5/22.
 */

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpDownloader {

    private URL url = null;

    /**
     * 根据URL下载文件,前提是这个文件当中的内容是文本,函数的返回值就是文本当中的内容
     * 1.创建一个URL对象
     * 2.通过URL对象,创建一个HttpURLConnection对象 3.得到InputStream 4.从InputStream当中读取数据
     *
     * @param urlStr
     * @return
     */
    public String download(String urlStr) {
        StringBuffer sb = new StringBuffer();
        String line = null;
        BufferedReader buffer = null;
        try {
            url = new URL(urlStr);
            HttpURLConnection urlConn = (HttpURLConnection) url
                    .openConnection();
            urlConn.setRequestMethod("GET");
            urlConn.setConnectTimeout(8000);
            urlConn.setReadTimeout(8000);
            buffer = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            while ((line = buffer.readLine()) != null) {
                sb.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(buffer!=null)
                    buffer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.e("print",sb.toString());
        return sb.toString();
    }
}
