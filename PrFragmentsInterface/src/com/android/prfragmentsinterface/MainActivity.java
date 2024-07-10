/*
 * Ejemplo de como pasar argumentos de un fragmento
 * a otro usando una interfaz entre fragmentos y Activity.
 */
package com.android.prfragmentsinterface;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.android.prfragmentsinterface.fragments.ListaFragment;
import com.android.prfragmentsinterface.fragments.ListaFragment.OnArticuloSelectedListener;
import com.android.prfragmentsinterface.fragments.ResultadoFragment;

/*
 * La Activity es la que se encarga de crear los fragmentos 
 * y de gestionar los datos entre ellos; los fragments
 * no deben comunicarse entre ellos, sino que es la Activity
 * de acogida la que se preocupa de pasar los datos entre
 * ellos mediante la implementacion de interfaces. 
 */
public class MainActivity extends FragmentActivity implements OnArticuloSelectedListener {



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		cargar();
	}

	public void cargar() {
		/*
		 * Cargar de nuevo el fragmento de la lista es
		 * redundante pero es para ver el ejemplo.
		 */
		ListaFragment lista = new ListaFragment();

		FragmentManager FragManager = getSupportFragmentManager();
		FragmentTransaction FragTransaction = FragManager.beginTransaction();
		FragTransaction.replace(R.id.fragment_container, lista);
		FragTransaction.commit();
	}

	/*
	 * En la implementacion de la interface recibimos una
	 * cadena con el nombre del item pulsado; este nombre
	 * lo vamos a pasar al nuevo fragment
	 */
	@Override
	public void onArticuloSelected(String str) {
		ResultadoFragment res = new ResultadoFragment();
		/*
		 * Creamos un objeto Bundle donde le meteremos como
		 * argumento la cadena que recibimos.
		 */
		Bundle args = new Bundle();
		args.putString("sItem", str);
		res.setArguments(args);
		/*
		 * Finalmente creamos el nuevo fragment que se
		 * encargara de recibir la cadena y de mostrarla.
		 */
		FragmentManager FragManager = this.getSupportFragmentManager();
		FragmentTransaction FragTransaction = FragManager.beginTransaction();
		FragTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		FragTransaction.replace(R.id.fragment_container, res);
		FragTransaction.addToBackStack(null);
		FragTransaction.commit();
	}

}
