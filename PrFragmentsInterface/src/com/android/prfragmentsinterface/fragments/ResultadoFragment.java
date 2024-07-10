package com.android.prfragmentsinterface.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.prfragmentsinterface.R;

public class ResultadoFragment extends Fragment {

	private View fragmentView;
	TextView tvItem_selected;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		fragmentView = inflater.inflate(R.layout.resultado_fragment, container, false);
		tvItem_selected = (TextView) fragmentView.findViewById(R.id.tvItem_selected);

		/*
		 * Recuperamos los argumentos que nos envia la 
		 * Activity y los ponemos en el TextView.
		 */
		Bundle args = this.getArguments();
		tvItem_selected.setText(args.getString("sItem"));
		return fragmentView;
	}
}
