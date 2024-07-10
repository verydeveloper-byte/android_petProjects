package com.android.prfragments.fragmentos;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.prfragments.R;
import com.android.prfragments.provincia.Provincia;
import com.android.prfragments.provincia.ProvinciaAdapter;


public class FragmentMaster extends ListFragment {

	private ArrayList<Provincia> provincias;
	private ProvinciaAdapter provAdapter;
	private OnProvinciaSelectedListener listener;

	TextView tvProvName;
	TextView tvDesc;
	ImageView ivProvincia;

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

	/*
	 * Asociar la actividad con el listener, pues
	 * es ella quien la implementa.
	 */
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof OnProvinciaSelectedListener) {
			listener = (OnProvinciaSelectedListener) activity;
		} else {
			throw new ClassCastException(activity.toString()
					+ "debe implementar la interfaz FragmentMaster.OnProvinciaSelectedListener");
		}
	}

	/*
	 * Asociar el layout al fragment
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		return inflater.inflate(R.layout.fragment_master, container, false);
	}

	/*
	 * Creacion inicial del fragment
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		provincias = new ArrayList<Provincia>();
		populate_provincias(provincias);

		provAdapter = new ProvinciaAdapter(this.getActivity(), R.layout.layout_provincia, provincias);
		setListAdapter(provAdapter);
	}

	/*
	 * Evento al pulsar sobre un item del ListView
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		/*
		 * Si el View del segundo fragment esta a null (portrait) 
		 * abrir otra activity. En caso contrario (landscape) 
		 * tenemos los dos fragments y se pueden recuperar Views 
		 * del segundo fragment.
		 */
		if (this.getActivity().findViewById(R.id.tvProvName) == null) {
			/*
			 * Orientacion vertical, abrir Activity
			 */
			listener.onProvinciaSelected(provincias.get(position), true);
		} else {
			/*
			 * Orientacion horizontal, ver detalle en 
			 * el segundo fragment.
			 */
			listener.onProvinciaSelected(provincias.get(position), false);
		}
	}

	/*
	 * Listener que maneja el evento pulsacion sobre
	 * un item de la lista; manda el evento a la Activity
	 * que es la que lo implementa y llama al siguiente 
	 * fragmento.
	 */
	public interface OnProvinciaSelectedListener {
		public void onProvinciaSelected(Provincia p, boolean isOrientationVertical);
	}

}
