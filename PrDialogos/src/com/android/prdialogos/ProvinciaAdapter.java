/*
 * El funcionamiento interno del adapter es el siguiente; Cuando se establece un adaptador, 
 * ya sea con setListAdapter(), Listview.setAdapter(), etc. El ListView llama a los metodos 
 * del adapter para rellenarse con los datos correspondientes. 
 * Llama al metodo getCount() para saber el numero de objetos a mostrar. 
 * Despues el ListView empieza a solicitar datos. Aqui es cuando entra en juego el ViewHolder 
 * y el reciclaje de Views. Se llama a getView() tantas veces como datos entren en la pantalla, 
 * conforme se desplaza el ListView para mostrar mas datos tambien se llama a dicho metodo y 
 * llegara un momento en el que se empezara a reciclar los Views gracias al ViewHolder.
 * 
 * info reciclaje Views: http://developer.android.com/training/improving-layouts/smooth-scrolling.html
 */

package com.android.prdialogos;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProvinciaAdapter extends ArrayAdapter<Provincia> {

	private ArrayList<Provincia> provincia;
	private Context context;

	public ProvinciaAdapter(Context context, int textViewResourceId, ArrayList<Provincia> elementos) {
		super(context, textViewResourceId, elementos);
		this.provincia = elementos;
		this.context = context;
	}

	/*
	 * getView() se llama cada vez que se pinta un elemento del ListView
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		/*
		 * El reciclaje de Views consiste en lo siguiente: Si convertView 
		 * es un valor nulo dentro de getView(), significa que el ListView 
		 * esta empezando a dibujar el objeto y aun no se ha inflado, por
		 * tanto se inflan los elementos del XML y despues se instancia 
		 * un objeto ViewHolder en el que se guardan los elementos del XML
		 * para despues con un setTag() (todos los Views tienen una propiedad 
		 * llamada Tag) guardar el ViewHolder.
		 */
		if (convertView == null) {
			/*
			 * coge el layout XML layout_provincia.xml e infla
			 * (inflar = convertir a un objeto de java los elementos del XML)
			 * los elementos en el objeto convertView.
			 */
			LayoutInflater vi;
			vi = LayoutInflater.from(context);
			convertView = vi.inflate(R.layout.layout_provincia, null);

			holder = new ViewHolder();
			holder.texto = (TextView) convertView.findViewById(R.id.tvNombre);
			holder.descripcion = (TextView) convertView.findViewById(R.id.tvDescripcion);
			holder.foto = (ImageView) convertView.findViewById(R.id.ivImagen);

			/* 
			 * se le pone una etiqueta al View, que es el ID del objeto del elemento en el que estamos.
			 * es decir, vinculamos el objeto ViewHolder (clase interna) a convertView
			 */ 
			convertView.setTag(holder);
		} 
		/*
		 * si no es null significa que el ListView esta reusando el objeto
		 * convertView del argumento, por lo que no necesitamos reinflar el 
		 * layout XML de provincias, ni tampoco asociar los elementos del
		 * XML con findViewById().
		 * Simplemente hay que recuperar la info que guardamos con setTag()
		 * la primera vez que creamos el objeto, con lo que se evitan llamadas
		 * innecesarias a findViewById() y volver a recuperar los datos.
		 */
		else {
			holder = (ViewHolder) convertView.getTag();
		}

		/*
		 * a partir de aqui ya tenemos el objeto holder preparado,
		 * solo queda asignarles valores 
		 */
		Provincia p = provincia.get(position);
		if (p != null) {
			holder.texto.setText(p.getNombre());
			holder.descripcion.setText(p.getDescripcion());
			holder.foto.setImageResource(p.getImagen());
		}
		return convertView;
	}

	public int getCount() {
		return provincia.size();
	}

	public Provincia getItem(int position) {
		return provincia.get(position);
	}

	/*
	 * al crear un adapter se suelen "cachear" las propiedades de los objetos View
	 * (nombre, descripcion, imagen), para mejorar el renderizado cuando se dibuje
	 * el ListView.
	 * Esta clase tiene que contener los mismos miembros (y del mismo tipo) que los
	 * elementos del XML layout_provincias.
	 */
	static class ViewHolder {
		ImageView foto;
		TextView texto;
		TextView descripcion;
	}

}
