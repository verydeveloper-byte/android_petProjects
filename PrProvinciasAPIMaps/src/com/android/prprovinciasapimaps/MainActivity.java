package com.android.prprovinciasapimaps;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.prprovinciasapimaps.adapter.ProvinciaAdapter;
import com.android.prprovinciasapimaps.provincia.Provincia;

public class MainActivity extends Activity {

	private ArrayList<Provincia> provincias;
	private ListView lvProvincias;

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
		
		double[][] coords = new double[][] {
				{36.7216555, -4.4274056},
				{36.526288, -6.288746},
				{37.891586, -4.7844853},
				{37.3753708, -5.9550582},
				{37.1809462, -3.5922032},
				{37.2708679, -6.9396903},
				{37.7800943, -3.796865},
				{36.8415281, -2.4571166}
		};

		for (int i = 0; i < provincia.length; i++) 
			p.add(new Provincia(provincia[i], descripcion[i], fotos[i], coords[i]));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		provincias = new ArrayList<Provincia>();
		lvProvincias = (ListView) this.findViewById(R.id.listViewProvincias);

		populate_provincias(provincias);
		lvProvincias.setAdapter(new ProvinciaAdapter(
				this.getApplicationContext(), 
				R.layout.layout_provincia, 
				provincias));
		lvProvincias.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent mapIntent = new Intent(getApplicationContext(), MapActivity.class);
				mapIntent.putExtra("prov", (Provincia) provincias.get(position));
				startActivity(mapIntent);
			}
		});
	}
}
