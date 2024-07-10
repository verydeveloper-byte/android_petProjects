/*
 * Android2014_6.1
 * Crear una aplicación que al ejecutarse la primera vez genere una base de
 * datos con una tabla que contenga las provincias de la comunidad autónoma de
 * Andalucía (id, nombre).
 * La página de inicio es la lista cargada con las provincias que aparecen en la
 * base de datos, mostrando el campo nombre.
 * Al seleccionar una de las provincias, se mostrará un formulario donde se podrá
 * editar el nombre de la misma, realizando la actualización correspondiente de la
 * Base de datos al pulsar un botón Guardar y mostrando de nuevo la lista
 * actualizada.
 */
package com.android.prsqlite;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.android.prsqlite.adapter.ProvinciaAdapter;
import com.android.prsqlite.model.Provincia;
import com.android.prsqlite.model.dao.ProvinciaDao;

public class MainActivity extends Activity {

	ArrayList<Provincia> provincias;
	ProvinciaDao provDao;
	ListView lvProvincias;
	final int EDICION_CODE = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cargar();
	}

	/*
	 * Introducir las Provincias en la BD.
	 */
	private void inicializarProvincias() {

		String[] provincia = new String[] { 
				"Malaga", 
				"Cadiz", 
				"Cordoba", 
				"Sevilla", 
				"Granada", 
				"Huelva", 
				"Jaen", 
				"Almeria" 
		};

		String[] descripcion = new String[] { 
				"Costa del sol", 
				"Playa y monumentos", 
				"Monumentos", 
				"Feria", 
				"Alcazaba", 
				"Oceano atlantico",
				"Olivos",
				"Sardinas" 
		};

		Integer[] fotos = new Integer[] { 
				R.drawable.malaga, 
				R.drawable.cadiz,
				R.drawable.cordoba,
				R.drawable.sevilla,
				R.drawable.granada,
				R.drawable.huelva,
				R.drawable.jaen,
				R.drawable.almeria
		};

		//		for (int i = 0; i < provincia.length; i++) {
		//			boolean res = provDao.insertarProvincia(new Provincia(provincia[i], descripcion[i], fotos[i]));
		//			Log.d("PRSQLITE", "insercion: " + Boolean.toString(res));
		//		}

		for (int i = 0; i < provincia.length; i++) 
			provDao.insertarProvincia(new Provincia(provincia[i], descripcion[i], fotos[i]));

	}

	private void cargar() {
		provDao = new ProvinciaDao(getApplicationContext());
		// evita rellenar de nuevo la tabla
		if (!provDao.isTablaProvinciasRellena())
			inicializarProvincias();

		provincias = new ArrayList<Provincia>();
		if (provDao.retrieveProvincias(provincias) > 0) {
			lvProvincias = (ListView) findViewById(R.id.listViewProvincias);
			lvProvincias.setAdapter(new ProvinciaAdapter(this, R.layout.layout_provincia, provincias));

			lvProvincias.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					Intent edicion = new Intent(getApplicationContext(), EdicionActivity.class).
							putExtra("provincia", (Provincia)parent.getItemAtPosition(position));
					startActivityForResult(edicion, EDICION_CODE);
				}
			});
		} else {
			Toast.makeText(getApplicationContext(), "La lista esta vacia", Toast.LENGTH_LONG).show();
		}

	}

	/**
	 * Releer los elementos del ListView.
	 */
	public void refreshListOnUpdateDB() {
		provincias.clear();
		provDao.retrieveProvincias(provincias);
		// refrescar
		lvProvincias.invalidateViews();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case 0:
			if (resultCode == Activity.RESULT_OK) {
				/*
				 * Actualizar la provincia en la BD
				 * y refrescar el ListView.
				 */
				provDao.actualizarProvincia((Provincia)data.getParcelableExtra("nuevaProv"), data.getStringExtra("nombreProvinciaAntiguo"));
				refreshListOnUpdateDB();

				Toast.makeText(this.getApplicationContext(), 
						data.getStringExtra("nombreProvinciaAntiguo") + " modificado satisfactoriamente", 
						Toast.LENGTH_SHORT).show();
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
