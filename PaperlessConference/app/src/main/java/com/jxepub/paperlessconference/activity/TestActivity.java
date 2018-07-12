package com.jxepub.paperlessconference.activity;

import static java.lang.String.format;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.jxepub.paperlessconference.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class TestActivity extends Activity implements OnPageChangeListener {

	public static final String SAMPLE_FILE = "sample.pdf";

	// public static final String ABOUT_FILE = "about.pdf";
	public static final String ABOUT_FILE = "about.pdf";
	PDFView pdfView;
	String pdfName = SAMPLE_FILE;
	Integer pageNumber = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pdfread);
		pdfView = (PDFView) findViewById(R.id.pdf_readview);
		about();

	}

	void afterViews() {
		display(pdfName, false);
	}

	public void about() {
		if (!displaying(ABOUT_FILE))
			display(ABOUT_FILE, true);
	}

	private void display(String assetFileName, boolean jumpToFirstPage) {
		if (jumpToFirstPage)
			pageNumber = 1;
		setTitle(pdfName = assetFileName);

		pdfView.fromAsset(assetFileName).defaultPage(pageNumber).onPageChange(this).load();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onPageChanged(int page, int pageCount) {
		// TODO Auto-generated method stub
		pageNumber = page;
		setTitle(format("%s %s / %s", pdfName, page, pageCount));
	}

	@Override
	public void onBackPressed() {
		if (ABOUT_FILE.equals(pdfName)) {
			display(SAMPLE_FILE, true);
		} else {
			super.onBackPressed();
		}
	}

	private boolean displaying(String fileName) {
		return fileName.equals(pdfName);
	}
}
