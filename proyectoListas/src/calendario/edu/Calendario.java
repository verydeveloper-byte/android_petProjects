package calendario.edu;

import java.util.Calendar;
import alumnos.edu.ActivityCalendario;
import android.widget.GridView;

public class Calendario {
 
	private Calendar fecha;
	private GridView gvCal;
	private ActivityCalendario contenido;
	private String [] dias =new String [50];
	
	public Calendario(ActivityCalendario vcontenido, GridView cal)
	{
	   fecha=Calendar.getInstance();
	   contenido=vcontenido;
	   gvCal=cal;
	   cargarSemana();
	   cargarDia(); 
	}
	public String[] getDias()
	{
		return dias;
	}
	private void cargarSemana()
	{
		dias[0]="L";
		dias[1]="M";
		dias[2]="X";
		dias[3]="J";
		dias[4]="V";
		dias[5]="S";
		dias[6]="D";
	}
	public void cargarDia()
	{
		Calendar  fechaaux=Calendar.getInstance();
		fechaaux.set(Calendar.DAY_OF_MONTH,1);
		fechaaux.set(Calendar.MONTH, fecha.get(Calendar.MONTH));
		fechaaux.set(Calendar.YEAR,fecha.get(Calendar.YEAR));
		int mesactual=fechaaux.get(Calendar.MONTH);
		int primero=fechaaux.get(Calendar.DAY_OF_WEEK);
		
		if (primero==1)
			 primero=7;
		else
			primero=primero-1;
		
		limpiar();
		for (int i=0;mesactual==fechaaux.get(Calendar.MONTH);i++)
		{
			if (i>= primero-1)
			{
				dias[i+7]=String.valueOf(fechaaux.get(Calendar.DATE));
				fechaaux.add(Calendar.DATE,1);
			}
		}	
	}
	public AdaptadorCalendario cargarDatos()
	{
		return new AdaptadorCalendario(contenido, this);
	}
	
	public String getDia(int pos)
	{
		return dias[pos];
	}
	public int getLongitud()
	{
		return dias.length;
	}
	public String getMessage()
	{
		String texto="";
		int mes=fecha.get(Calendar.MONTH);
		switch(mes)
		{
		case 0: texto="Enero";
				break;
		case 1: texto="Febrero";
				break; 
		case 2: texto="Marzo";
				break;
		case 3: texto="Abril";
				break;
		case 4: texto="Mayo";
				break;
		case 5: texto="Junio";
				break;
		case 6: texto="Julio";
				break;
		case 7: texto="Agosto";
				break;
		case 8: texto="Septiembre";
				break;
		case 9: texto="Octubre";
				break;
		case 10: texto="Noviembre";
				break;
		case 11: texto="Diciembre";
				break;
		}
		texto+=" "+fecha.get(Calendar.YEAR);
		return texto;
	}
	public void limpiar()
	{
		for (int i=7;i<dias.length;i++)
		{
			 dias[i]="";
		}
	}
    public void avanzar()
    {
    	fecha.add(Calendar.MONTH,1);
    }
    public void retroceder()
    {
    	fecha.add(Calendar.MONTH, -1);
    }
}
