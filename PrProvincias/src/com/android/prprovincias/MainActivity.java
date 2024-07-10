/*
 * Android2014_4.2 
 * 
 * Crear una aplicación Android que muestre una lista con las provincias de la
 * comunidad autónoma de Andalucía. Para ello emplear un List View.
 * Cuando un usuario seleccione alguno de los elementos debe aparecer un Toast
 * con el mensaje:
 * “Yo soy de ...” según la provincia seleccionada.
 */
package com.android.prprovincias;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ArrayList<Provincia> provincias;
	ListView lvProvincias;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cargar();
	}

	private void populate_provincias(ArrayList<Provincia> p) {
		p.add(new Provincia("Malaga", "Costa del Sol", R.drawable.malaga));
		p.add(new Provincia("Cadiz", "playa y monumentos", R.drawable.cadiz));
		p.add(new Provincia("Cordoba", "Monumentos", R.drawable.cordoba));
		p.add(new Provincia("Sevilla", "Feria", R.drawable.sevilla));
		p.add(new Provincia("Granada", "Alcazaba", R.drawable.granada));
		p.add(new Provincia("Huelva", "Oceano atlantico", R.drawable.huelva));
		p.add(new Provincia("Jaen", "Olivos", R.drawable.jaen));
		p.add(new Provincia("Almeria", "Sardinas", R.drawable.almeria));
	}
	
	private void cargar() {
		provincias = new ArrayList<Provincia>();
		populate_provincias(provincias);
		
		lvProvincias = (ListView) findViewById(R.id.listViewProvincias);
		lvProvincias.setAdapter(new ProvinciaAdapter(this, R.layout.layout_provincia, provincias));
		
		lvProvincias.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(parent.getContext(), "Yo soy de " + ((Provincia) parent.getItemAtPosition(position)).getNombre(), Toast.LENGTH_SHORT).show();
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
