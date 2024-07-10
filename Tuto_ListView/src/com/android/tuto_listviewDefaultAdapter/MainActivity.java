/**
 * Pasos para crear un ListView con un Adapter por defecto
 * 1- añadimos el elemento ListView en el xml del activity.
 * 2- creamos un array con los valores.
 * 3- creamos un ArrayAdapter y se lo asignamos al ListView
 */

package com.android.tuto_listviewDefaultAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.tuto_listview.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cargar();
	}
	
	private void cargar() {
		// array con valores 
		String[] valores = {"elemento 1", "elemento 2", "elemento 3", "elemento 4"};
		// asociar el ListView
		ListView lv = (ListView) findViewById(R.id.listViewEjemplo);
		// crear adaptador y asociarlo al ListView
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, valores);		/* funciona porque los valores estan en el codigo */
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(view.getContext(), "has seleccionado " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
			}
		});
		
		
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
