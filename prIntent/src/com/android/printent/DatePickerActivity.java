package com.android.printent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;

public class DatePickerActivity extends Activity {
	
	DatePicker dpCalendario;
	Button btAtras;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datepicker);
		
		cargar();
	}
	
	private void cargar() {
		dpCalendario = (DatePicker) findViewById(R.id.datePicker);
		btAtras = (Button) findViewById(R.id.btBackToMenuFromDatePicker);
		
		btAtras.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
