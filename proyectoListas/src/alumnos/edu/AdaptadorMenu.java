package alumnos.edu;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdaptadorMenu extends ArrayAdapter<Menu> {

	private Activity contenido;
	private Menu [] elementos;
	
	public AdaptadorMenu(Activity contenido, Menu [] element)
	{
		super(contenido,R.layout.main,element);
		this.contenido=contenido;
		elementos=element;
	}
	public View getView(int pos, View convertir, ViewGroup padre)
	{
		View elemento=convertir;
		Etiqueta menu;
		if (elemento!=null)
		{
			menu=(Etiqueta)elemento.getTag();
		}
		else{
			LayoutInflater inflado= contenido.getLayoutInflater();
			elemento=inflado.inflate(R.layout.listadomenu,null);
			menu=new Etiqueta();
			menu.lbTitulo=(TextView)elemento.findViewById(R.id.lbTitulo);
			menu.lbDesc=(TextView)elemento.findViewById(R.id.lbDescripcion);
			elemento.setTag(menu);
		}
		menu.lbTitulo.setText(elementos[pos].getTitulo());
		menu.lbDesc.setText(elementos[pos].getDescripcion());
		return elemento;
	}
}
class Etiqueta
{
	TextView lbTitulo;
	TextView lbDesc;
}