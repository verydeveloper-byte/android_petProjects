package edu.alumno.xml;

import noticiasXml.Noticias;
import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdaptadorLista extends ArrayAdapter<Noticias>{
    private Activity listado;
    private Noticias[] noticias;
    	
	public AdaptadorLista(Activity contenido,Noticias[] vnoticias) {
		super(contenido,R.layout.main,vnoticias);
		listado=contenido;
		noticias=vnoticias;
	}
	@Override
	public View getView(int pos,View convertirView, ViewGroup padre)
	{
		View elemento=convertirView;
		Etiqueta et=null;
		
		if (elemento==null)
		{
			LayoutInflater inflado=listado.getLayoutInflater();
			elemento=inflado.inflate(R.layout.filas,null);
			et=new Etiqueta();
			et.titulo=(TextView)elemento.findViewById(R.id.tvTitulo);
			et.descripcion=(TextView)elemento.findViewById(R.id.tvDescripcion);
			et.fecha=(TextView)elemento.findViewById(R.id.tvFecha);
			et.categoria=(TextView)elemento.findViewById(R.id.tvCategoria);
			elemento.setTag(et);
		}
		else{
			et=(Etiqueta)elemento.getTag();
		}
		et.titulo.setText(noticias[pos].getTitulo());
		et.descripcion.setText(noticias[pos].getDescripcion());
		
		et.fecha.setText(noticias[pos].getFecha());
		et.categoria.setText(noticias[pos].getCategoria());
		return elemento;
	}
}
class Etiqueta
{
	protected TextView titulo;
	protected TextView descripcion;
	protected TextView fecha;
	protected TextView categoria;
}




