package alumnos.edu.tablas;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProyectoTablasActivity extends Activity {
    /** Called when the activity is first created. */
	private int perder=0;
	private int ganaste=0;
	private Button boton1,boton2,boton3,boton4,boton5,boton6;
	private Button botonr;
	private Drawable defecto;
	public void inicializar()
	{ 
		int op=0;
		perder= (int)(Math.random()*6+1);
		do{
			op=(int)(Math.random()*6+1);
		}while(op==perder);
		ganaste=op;
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        inicializar();
        
        botonr=(Button) findViewById(R.id.reiniciar);
        defecto=botonr.getBackground();
        botonr.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				boton1.setBackgroundDrawable(defecto);
				boton2.setBackgroundDrawable(defecto);
				boton3.setBackgroundDrawable(defecto);
				boton4.setBackgroundDrawable(defecto);
				boton5.setBackgroundDrawable(defecto);
				boton6.setBackgroundDrawable(defecto);
			}
		});
        boton1=(Button)findViewById(R.id.boton1);
        boton1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				int num=Integer.parseInt(boton1.getText().toString());
				if (num==perder)
				{
					boton1.setBackgroundResource(R.drawable.muerte);
				}
				else if(num==ganaste)
				{
					boton1.setBackgroundResource(R.drawable.tesoro);
				}
				else
				{
					boton1.setBackgroundColor(Color.BLUE);
				}
			}
		});
        boton2=(Button)findViewById(R.id.boton2);
        boton2.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				int num=Integer.parseInt(boton2.getText().toString());
				if (num==perder)
				{
					boton2.setBackgroundResource(R.drawable.muerte);
				}
				else if(num==ganaste)
				{
					boton2.setBackgroundResource(R.drawable.tesoro);
				}
				else
				{
					boton2.setBackgroundColor(Color.BLUE);
				}
			}
		});
        boton3=(Button)findViewById(R.id.boton3);
        boton3.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				int num=Integer.parseInt(boton3.getText().toString());
				if (num==perder)
				{
					boton3.setBackgroundResource(R.drawable.muerte);
				}
				else if(num==ganaste)
				{
					boton3.setBackgroundResource(R.drawable.tesoro);
				}
				else
				{
					boton3.setBackgroundColor(Color.BLUE);
				}
			}
		});
        boton4=(Button)findViewById(R.id.boton4);
        boton4.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				int num=Integer.parseInt(boton4.getText().toString());
				if (num==perder)
				{
					boton4.setBackgroundResource(R.drawable.muerte);
				}
				else if(num==ganaste)
				{
					boton4.setBackgroundResource(R.drawable.tesoro);
				}
				else
				{
					boton4.setBackgroundColor(Color.BLUE);
				}
			}
		});
        boton5=(Button)findViewById(R.id.boton5);
        boton5.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				int num=Integer.parseInt(boton5.getText().toString());
				if (num==perder)
				{
					boton5.setBackgroundResource(R.drawable.muerte);
				}
				else if(num==ganaste)
				{
					boton5.setBackgroundResource(R.drawable.tesoro);
				}
				else
				{
					boton5.setBackgroundColor(Color.BLUE);
				}
			}
		});
        boton6=(Button)findViewById(R.id.boton6);
        boton6.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				int num=Integer.parseInt(boton6.getText().toString());
				if (num==perder)
				{
					boton6.setBackgroundResource(R.drawable.muerte);
				}
				else if(num==ganaste)
				{
					boton6.setBackgroundResource(R.drawable.tesoro);
				}
				else
				{
					boton6.setBackgroundColor(Color.BLUE);
				}
			}
		});
        
               
    }
    
}