package alumnos.edu.fichero;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdaptadorLista extends ArrayAdapter<Comentarios> {
    private Activity listado;
    private Comentarios[] comentario;
	
	public AdaptadorLista(Activity contenido,Comentarios[] comentario) {
		super(contenido,R.layout.tablacomentarios,comentario);
		listado=contenido;
		this.comentario=comentario;
	}
	@Override
	public View getView(int pos, View convertirView, ViewGroup padre)
	{
		View elemento=convertirView;
		Etiqueta etiq=null;
		if (elemento==null )
		{
			LayoutInflater inflado=listado.getLayoutInflater();
			elemento=inflado.inflate(R.layout.casillas,null);
			etiq=new Etiqueta();
			etiq.lbUsuario=(TextView)elemento.findViewById(R.id.usuarios);
			etiq.lbComentario=(TextView)elemento.findViewById(R.id.comentario);
			elemento.setTag(etiq);
		}
		else{
			etiq=(Etiqueta)elemento.getTag();
		}
		etiq.lbUsuario.setText(comentario[pos].getUsuario());
		etiq.lbComentario.setText(comentario[pos].getComentario());
		return elemento;
	}

}
class Etiqueta
{
	protected TextView lbUsuario;
	protected TextView lbComentario;
}