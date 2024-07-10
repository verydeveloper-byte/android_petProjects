package com.android.prfragmentsinterface.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.prfragmentsinterface.R;

public class ListaFragment extends ListFragment {

	// en este objeto voy a mandar el item que pulse
	private OnArticuloSelectedListener listener;
	private String[] valores = new String[] { "item 1", "item 2", "item 3" };

	/*
	 * 1- Lo primero que necesitamos es una instancia de la interface.
	 * listener = activity que implementa OnArticuloSelectedListener.
	 * Para asegurarnos de que la Activity de acogida implementa (implements)
	 * nuestra interface listener, lo comprobamos en el metodo onAttach(); que
	 * es llamado cada vez que la Activity crea una instancia del fragmento.
	 */
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			listener = (OnArticuloSelectedListener) activity;
		}
		catch (ClassCastException e) {}
	}

	/*
	 * Creamos el adaptador del ListView usando un layout por defecto
	 * con los valores del array String 'valores'.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setListAdapter(new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, valores));
	}

	/*
	 * Establecemos el layout del fragment mediante su XML. 
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		return inflater.inflate(R.layout.list_fragment, container, false);
	}

	/*
	 * 2- Sobreescribimos el metodo onListItemClick programandolo
	 * para que cuando se haga click en un elemento del ListView
	 * envie el evento (click al item) y los datos del evento
	 * a la interface. Es decir, cada vez que el fragmento reciba
	 * el evento click sobre un item lo manda a la interface que a
	 * su vez lo recibe el Activity que lo implementa.
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		listener.onArticuloSelected(valores[position]);
	}

	/*
	 * Para pasar valores entre Fragments declaramos una interfaz que luego 
	 * sera implementada por la Activity para poder compartir eventos con ella
	 * y enviar los datos de este Fragment a otro.
	 */
	public interface OnArticuloSelectedListener {
		public void onArticuloSelected(String str);
	}
}