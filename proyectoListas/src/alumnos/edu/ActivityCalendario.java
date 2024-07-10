package alumnos.edu;

import calendario.edu.Calendario;
import ficheros.ActivityFicheros;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class ActivityCalendario extends Activity implements OnItemClickListener {
 
	private Calendario c;
	private  Button btDerecho, btIzquierdo;
	private GridView gvCal;
	private TextView tvMes;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendario);
		
		gvCal=(GridView)findViewById(R.id.gvCalendario);
	    c=new Calendario(this, gvCal);
	    gvCal.setAdapter(c.cargarDatos());
	    
	    tvMes=(TextView)findViewById(R.id.mes);
	    tvMes.setText(c.getMessage()) ;

	    btDerecho=(Button)findViewById(R.id.der);
	    btDerecho.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				c.avanzar();
				c.cargarDia();
		        tvMes.setText(c.getMessage());
		        gvCal.setAdapter(c.cargarDatos());
			}
		});
	    btIzquierdo=(Button)findViewById(R.id.izq);
	    btIzquierdo.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				c.retroceder();
				c.cargarDia();
				tvMes.setText(c.getMessage());
				gvCal.setAdapter(c.cargarDatos());
			}
		});
	    gvCal.setOnItemClickListener(this);
	    
	}
	
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		/*AlertDialog.Builder dialogo=new AlertDialog.Builder(this);
		dialogo.setTitle("Informaci�n");
		dialogo.setMessage("Has pulsado el dia "+ c.getDia(arg2));
		dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				arg0.cancel();
			}
		});
		dialogo.show();*/
		Intent  interno=new Intent (this,ActivityFicheros.class);
		String mensaje=c.getMessage()+"-"+c.getDia(arg2);
		interno.putExtra("mensajefecha", mensaje);
		startActivity(interno);
		
	}
	
}
