package com.jxepub.paperlessconference.fragment;

import com.jxepub.paperlessconference.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragHuiYiFuWu extends Fragment {
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_huiyifuwu, null);

		return view;
	}
}
