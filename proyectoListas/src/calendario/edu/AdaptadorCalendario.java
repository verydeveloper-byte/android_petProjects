package calendario.edu;


import alumnos.edu.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdaptadorCalendario extends BaseAdapter {
	private Context contenido;
	private Calendario calen;
	
	public AdaptadorCalendario(Context contenido,Calendario c)
	{
		this.contenido=contenido;
		calen=c;
	}
	public int getCount()
	{
		return calen.getLongitud();
	}
	
	
	public long getItemId(int arg0) {
    	return 0;
	}

	public View getView(int arg0, View arg1, ViewGroup arg2) {
		
		LayoutInflater inflado=(LayoutInflater) contenido.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View gridView=arg1;
		TextView texto;
		if(gridView==null)
		{
			gridView=new View(contenido);
			gridView=inflado.inflate(R.layout.cajitas,null);
			texto=(TextView)gridView.findViewById(R.id.dias);
		    gridView.setTag(texto);
		}
		else {
			texto=(TextView)gridView.getTag();
		}
		texto.setText(calen.getDia(arg0));
		
		return gridView;
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	

	

}
