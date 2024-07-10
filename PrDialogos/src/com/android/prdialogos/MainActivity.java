/*
 * Android2014_4.3 
 * Crear una aplicacion Android que muestre una lista de provincias, tal que:
 * - Al pulsar sobre cada elemento de la lista muestre un dialogo "Yo soy
 * 		de ..." segun la provincia seleccionada.
 * - Existe un menu general con las opciones Recargar y Limpiar que
 * 		recargara la lista con todos los datos o la eliminara por completo.
 * - Al dejar un item de la lista pulsado aparecera un menu contextual
 * 		con la opcion Eliminar que mostrara una dialogo de confirmacion
 * 		"¿Desea eliminar la provincia?"
 * - Intentar que el menu contextual tenga por titulo el nombre de la
 * 		provincia, y que la pregunta sea "¿Desea eliminar la provincia ...?
 * 		segun la provincia seleccionada.
 * 
 * Este ejercicio contiene tambien el PRIMER EJERCICIO DE LOS INTENTS 
 * por ser muy parecido:
 * - Al pulsar sobre Editar se abre una pantalla con el texto de la provincia en un
 * campo editable, y con la imagen debajo, de forma que el usuario pueda
 * modificar el nombre.
 */
package com.android.prdialogos;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ArrayList<Provincia> provincias;
	private ListView lvProvincias;
	private ProvinciaAdapter provAdapter;
	final int EDITAR_PROVINCIA_CODE = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cargar();
	}

	private void populate_provincias(ArrayList<Provincia> p) {
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

		for (int i = 0; i < provincia.length; i++) 
			p.add(new Provincia(provincia[i], descripcion[i], fotos[i]));
	}

	private void cargar() {
		provincias = new ArrayList<Provincia>();
		provAdapter = new ProvinciaAdapter(this, R.layout.layout_provincia, provincias);

		populate_provincias(provincias);
		lvProvincias = (ListView) findViewById(R.id.listViewProvincias);
		lvProvincias.setAdapter(provAdapter);

		lvProvincias.setOnItemClickListener(new OnItemClickListener() {
			/*
			 * Al hacer click sobre una provincia se muestra
			 * un dialogo con un boton neutro que imprime el
			 * nombre de la provincia en el cuerpo del mismo.
			 */
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				AlertDialog.Builder dialogo = new AlertDialog.Builder(view.getContext()).
						setTitle(((Provincia)parent.getItemAtPosition(position)).getNombre()).
						setMessage("Yo soy de " + ((Provincia) parent.getItemAtPosition(position)).getNombre()).
						// al hacer click fuera del dialogo no se sale de el
						setCancelable(false).
						setNeutralButton("OK", null);

				dialogo.show();
			}

			/*
			 * Dialog con menu contextual en un Toast
			 */
//						@Override
//						public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			/*  La diferencia entre un menu contextual mediante Dialog y otro
			 *  creado con onCreateContextMenu, es que para el ultimo hace falta
			 *  una pulsacion larga sobre el elemento vista.
			 *  Es mas apropiado usar la segunda forma.
			 */

			/*
			 *  en lugar de usar setMessage(), le pasamos un array con los strings
			 *  y un listener con lo que hacer al hacer click en cada elemento.
			 */
//							final CharSequence[] items = new CharSequence[] { "Editar", "Eliminar" };
//						
//										AlertDialog.Builder dialogo = new AlertDialog.Builder(view.getContext());
//										dialogo.setItems(items, new DialogInterface.OnClickListener() {
//						
//											@Override
//											public void onClick(DialogInterface dialog, int which) {
//												Toast.makeText(getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
//											}
//										});
//										dialogo.setCancelable(false);
//										dialogo.show();
//									}
		});

		registerForContextMenu(lvProvincias);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.action_settings:
			Toast.makeText(this, "nada que mostrar", Toast.LENGTH_SHORT).show();
			break;
		case R.id.clean_list:
			// se borra por completo el contenido del ArrayList
			provAdapter.clear();
			return true;

		case R.id.reload_list:
			// rellenar de nuevo los datos del ArrayList de provincias 
			// y actualizar el adapter en el ListView
			provAdapter.clear();
			populate_provincias(provincias);
			lvProvincias.setAdapter(provAdapter);

			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/*
	 * Al crear el menu contextual
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		// inflar el XML del menu contextual
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu_provincia, menu);
		// obtener el ListView
		ListView provListView = (ListView) v;

		// para obtener la posicion del item del 
		// ListView pulsado (long click)
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;

		Provincia p = (Provincia) provListView.getItemAtPosition(info.position);
		
		menu.setHeaderTitle(p.getNombre());
	}

	/*
	 * Una vez que hemos hecho click en una opcion
	 * del menu contextual (ej: eliminar) 
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// para obtener la posicion del item del 
		// ListView pulsado (long click)
		final AdapterContextMenuInfo menuInfo;
		Provincia p;
		
		switch (item.getItemId()) {
		case R.id.eliminar:
			menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
			p = (Provincia) provAdapter.getItem(menuInfo.position);

			/*
			 * Dialogo de confirmacion de la provincia
			 */
			AlertDialog.Builder confirmaEliminar = new AlertDialog.Builder(this).
					setTitle("Eliminar " + p.getNombre()).
					setMessage("¿Desea eliminar " + p.getNombre() + "?").
					setPositiveButton("Si", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							provAdapter.remove(provAdapter.getItem(menuInfo.position));
						}
					}).
					setNegativeButton("No", null);
			confirmaEliminar.show();

			return true;
		case R.id.editar:
			menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
			p = provAdapter.getItem(menuInfo.position);
			/*
			 * abrir una nueva activity para editar el nombre de la provincia
			 */
			Intent editaProvincia = new Intent(MainActivity.this, EditaProvinciaActivity.class).
					putExtra("provincia", p).
					putExtra("position", Integer.toString(menuInfo.position));
			
			startActivityForResult(editaProvincia, EDITAR_PROVINCIA_CODE);
			return true;
		}

		return super.onContextItemSelected(item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == EDITAR_PROVINCIA_CODE) {
			if (resultCode == RESULT_OK) {
				/*
				 * Para editar un elemento del ListView primero recuperamos
				 * el elemento con getItem(), lo editamos y llamamos al
				 * metodo notifyDataSetChanged() del adapter para refrescar. 				 
				 */
				Provincia p = (Provincia) provAdapter.getItem(Integer.valueOf(data.getStringExtra("position")));
				p.setNombre(data.getStringExtra("prov_name"));
				provAdapter.notifyDataSetChanged();
			}
		}
	}


}