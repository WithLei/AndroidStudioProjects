package com.jxepub.paperlessconference.fragment;

import java.util.ArrayList;
import java.util.List;

import com.jxepub.paperlessconference.R;
import com.jxepub.paperlessconference.adapter.WoDeBiJiAdapter;
import com.jxepub.paperlessconference.entity.BiJi;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class FragWoDeBiJi extends Fragment {
	private View view;
	private GridView gv_wodebiji, gv_wodewenjian;
	private WoDeBiJiAdapter adapter;
	private List<BiJi> sus;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_wodebiji, null);
		init();
		initWDBJ();

		return view;
	}

	void init() {
		gv_wodebiji = (GridView) view.findViewById(R.id.gv_wodebiji);
		gv_wodewenjian = (GridView) view.findViewById(R.id.gv_wodewenjian);
	}

	void initWDBJ() {// ��ʼ���ҵıʼǱ�
		sus = new ArrayList<BiJi>();
		sus.add(new BiJi("", "", ""));
		if (sus.size() > 0) {
			handler.sendEmptyMessage(200);
		}
	}

	void initWDWJ() {// ��ʼ���ҵ��ļ�

	}

	@Override
	public void onResume() {
		super.onResume();
		handler.sendEmptyMessage(100);
	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 100:
				new Thread(new Runnable() {
					@Override
					public void run() {
						initWDBJ();
						initWDWJ();
					}
				}).start();
				break;
			case 200:
				if (sus.size() > 0) {
					adapter = new WoDeBiJiAdapter(getActivity(), sus);
					gv_wodebiji.setAdapter(adapter);
					adapter.notifyDataSetChanged();
				}
				break;
			}
		};
	};

	// PdfContext pdfContext = new PdfContext();
	// PdfDocument pdfDocument = (PdfDocument)
	// pdfContext.openDocument(file.getAbsolutePath());//
	// pathΪҪ��ͼ��pdf��·��,String����
	// PdfPage pdfPage = (PdfPage) pdfDocument.getPage(0);// pageΪҪ��ȡ��ҳ��,int��
	// RectF rf = new RectF();// ʹ��һ������ȥ��ͼ
	// rf.bottom = rf.right = (float) 1.0;// �Ϸ����󷽲�ָ�����·����ҷ�ָ��Ϊ1.0��λ�ã�����������ͼ
	// Bitmap bitmap = pdfPage.renderBitmap(pdfPage.getWidth(),
	// pdfPage.getHeight(), rf); // ���bitmap��������Ҫ�ص�ͼ
}
