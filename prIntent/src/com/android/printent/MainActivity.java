/*
 * Android2014_4.4.pdf actividad 2.
 */

package com.android.printent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	ListView lvOpciones;
	String[] options;
	// codigo de solicitud para identificar la respuesta
	// cuando volvamos de la activity Login.
	final int LOGIN_REQUEST_CODE = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cargar();
	}

	private void cargar() {
		// Obtener de res/strings.xml el array con las opciones
		options = getResources().getStringArray(R.array.select_option);
		lvOpciones = (ListView) findViewById(R.id.listViewOpciones);

		lvOpciones.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options));
		lvOpciones.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				switch (position) {
				case 0:
					Intent intentDatePicker = new Intent(getApplicationContext(), DatePickerActivity.class);
					startActivity(intentDatePicker);
					break;
				case 1:
					Intent intentLoginForm = new Intent(MainActivity.this, LoginFormActivity.class);
					startActivityForResult(intentLoginForm, LOGIN_REQUEST_CODE);
					break;
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == LOGIN_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				try {
					setTitle("Hola " + data.getStringExtra("usuario"));
				} catch (NullPointerException e) {
					e.printStackTrace();
				}

			}
		}
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

}
