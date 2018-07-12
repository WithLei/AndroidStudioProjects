package com.jxepub.paperlessconference.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class FileDownAsyncTask extends AsyncTask<String, Integer, File> {
	private String url;
	private Context mContext;
	private String wenJianName;

	public FileDownAsyncTask(Context mContext, String wenJianName) {
		this.mContext = mContext;
		this.wenJianName = wenJianName;
	}

	// 执行之前 ui线程
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		File file = new File(CommonUtil.FILEPATH);
		if (!file.exists()) {
			file.mkdir();
		}
	}

	@Override
	protected File doInBackground(String... params) {
		url = params[0];
		String name = URLDecoder.decode(params[0].substring(params[0].lastIndexOf("/") + 1));
		String fileName = CommonUtil.FILEPATH + name;
		File file = new File(fileName);
		if (file.exists()) {
			return file;
		}
		OutputStream output = null;
		InputStream is = null;
		try {
			// 建立网络连接
			URL url1 = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
			// 得到输入字节流
			is = conn.getInputStream();
			output = new FileOutputStream(file);
			// 读取大文件
			byte[] buffer = new byte[2 * 1024];
			int len = -1;
			while ((len = is.read(buffer)) != -1) {
				output.write(buffer, 0, len);
			}
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
				is.close();
			} catch (Exception e) {
			}
		}
		return file;
	}

	// doInBackground 执行 return
	// 后马上执行 ui线程并把结果传递给此方法 execute(url)
	protected void onPostExecute(File file) {
		super.onPostExecute(file);
		if (file != null) {
			Toast.makeText(mContext, wenJianName + " 下载完成", Toast.LENGTH_SHORT).show();
		}
	}

	// 如果新线程中执行了 publishProgress(values);
	// 就会执行此方法实现一条一条的更新 ui
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		// int size = values[0];
		// int total = values[1];
	}
}