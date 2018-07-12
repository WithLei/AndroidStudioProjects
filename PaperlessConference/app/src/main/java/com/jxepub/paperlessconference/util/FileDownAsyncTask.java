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

	// ִ��֮ǰ ui�߳�
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
			// ������������
			URL url1 = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
			// �õ������ֽ���
			is = conn.getInputStream();
			output = new FileOutputStream(file);
			// ��ȡ���ļ�
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

	// doInBackground ִ�� return
	// ������ִ�� ui�̲߳��ѽ�����ݸ��˷��� execute(url)
	protected void onPostExecute(File file) {
		super.onPostExecute(file);
		if (file != null) {
			Toast.makeText(mContext, wenJianName + " �������", Toast.LENGTH_SHORT).show();
		}
	}

	// ������߳���ִ���� publishProgress(values);
	// �ͻ�ִ�д˷���ʵ��һ��һ���ĸ��� ui
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		// int size = values[0];
		// int total = values[1];
	}
}