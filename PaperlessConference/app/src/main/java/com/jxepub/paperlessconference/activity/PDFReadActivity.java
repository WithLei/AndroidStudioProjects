package com.jxepub.paperlessconference.activity;

import static java.lang.String.format;
import java.io.File;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.jxepub.paperlessconference.R;
import com.jxepub.paperlessconference.entity.WenJianInfo;
import com.jxepub.paperlessconference.util.CommonUtil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PDFReadActivity extends Activity implements OnClickListener, OnPageChangeListener {
	private RelativeLayout btn_back, pdf_top;
	private TextView pdf_name;
	private ImageView pdf_img_xpc, pdf_img_bj;
	private PDFView pdf_readview;
	private Context mContext;
	private int dqPage = 1;
	private WenJianInfo info;
	private String pdfFileName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pdfread);
		init();
		setListener();
	}

	int startX, startY, endX, endY;
	boolean isOkX = false, isOkY = false;

	void init() {
		mContext = PDFReadActivity.this;
		btn_back = (RelativeLayout) findViewById(R.id.pdf_backbtn);
		pdf_top = (RelativeLayout) findViewById(R.id.pdf_top);
		pdf_name = (TextView) findViewById(R.id.pdf_name);
		pdf_img_xpc = (ImageView) findViewById(R.id.pdf_img_xpc);
		pdf_img_bj = (ImageView) findViewById(R.id.pdf_img_bj);
		pdf_readview = (PDFView) findViewById(R.id.pdf_readview);
		Intent intent = getIntent();
		info = (WenJianInfo) intent.getSerializableExtra("WenJianInfo");
		pdfFileName = CommonUtil.FILEPATH + info.getUrl().substring(info.getUrl().lastIndexOf("/") + 1);
		Log.e("pdfFileName = ", pdfFileName);
		display(pdfFileName, true);
	}

	void setListener() {
		btn_back.setOnClickListener(this);
		pdf_img_bj.setOnClickListener(this);
		pdf_img_xpc.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pdf_backbtn:
			finish();
			break;
		case R.id.pdf_img_xpc:
			Toast.makeText(mContext, "正在努力开发中...", Toast.LENGTH_SHORT).show();
			break;
		case R.id.pdf_img_bj:
			Toast.makeText(mContext, "正在努力开发中...", Toast.LENGTH_SHORT).show();
			break;
		}
	}

	private void display(String pdfFileName, boolean isJumpToFirstPage) {
		if (isJumpToFirstPage) {
			dqPage = 1;
		}
		pdf_name.setText(info.getWenJianName());
		pdf_readview.fromFile(new File(pdfFileName)).defaultPage(dqPage).onPageChange(this).load();
	}

	@Override
	public void onPageChanged(int page, int pageCount) {
		dqPage = page;
		pdf_name.setText(format("%s  %s / %s", info.getWenJianName(), page, pageCount));
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == ev.ACTION_DOWN) {
			startX = (int) ev.getX();
			startY = (int) ev.getY();
		}
		if (ev.getAction() == ev.ACTION_UP) {
			endX = (int) ev.getX();
			endY = (int) ev.getY();
			Log.e("endY = ", endY + "");
			if ((startX - 5) <= endX && endX <= (startX + 5)) {
				isOkX = true;
			} else {
				isOkX = false;
			}
			if ((startY - 5) <= endY && endY <= (startY + 5)) {
				isOkY = true;
			} else {
				isOkY = false;
			}
			if (isOkX && isOkY) {
				if (pdf_top.getVisibility() == View.VISIBLE) {
					if (endY >= 100) {
						pdf_top.setVisibility(View.GONE);
					}
				} else {
					pdf_top.setVisibility(View.VISIBLE);
				}
			}

			if (endX - startX >= 100 || startX - endX >= 100) {
				if (pdf_top.getVisibility() == View.VISIBLE) {
					pdf_top.setVisibility(View.GONE);
				}
			}
		}
		return super.dispatchTouchEvent(ev);
	}
}
