package edu.alumno.provider;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
 
	private Button btConsultar,btInsertar,btEliminar,btActualizar;
	private TextView tvTexto;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btConsultar=(Button)findViewById(R.id.btConsultar);
        tvTexto=(TextView)findViewById(R.id.tvResultado);
        
        btConsultar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String [] proyeccion=new String[]{
					Clientes._ID,
					Clientes.COL_NOMBRE,
					Clientes.COL_TELEFONO,
					Clientes.COL_EMAIL
				};
				Uri clientesUri=ClientesProvider.CONTENT_URI;
				ContentResolver cr=getContentResolver();
				Cursor c=cr.query(clientesUri, proyeccion, null,null,null);
				if (c.moveToFirst())
				{
					String nombre;
					String telefono;
					String email;
					
					int colNombre=c.getColumnIndex(Clientes.COL_NOMBRE);
					int colTelefono=c.getColumnIndex(Clientes.COL_TELEFONO);
					int colEmail=c.getColumnIndex(Clientes.COL_EMAIL);
					
					tvTexto.setText("");
					
					do{
						nombre=c.getString(colNombre);
						telefono=c.getString(colTelefono);
						email=c.getString(colEmail);
						tvTexto.append(nombre+" - "+telefono+ " - " + email + "\n");
					}while(c.moveToNext());
				}
				
			}
		});
        btInsertar=(Button)findViewById(R.id.btInsertar);
        btInsertar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			   ContentValues valores=new ContentValues();
			   valores.put(Clientes.COL_NOMBRE, "paquito chocolatero");
			   valores.put(Clientes.COL_TELEFONO,"678956589");
			   valores.put(Clientes.COL_EMAIL,"paquito@gmail.com");
			   
			   ContentResolver cr=getContentResolver();
			   cr.insert(ClientesProvider.CONTENT_URI,valores);	
			}
		});
        btEliminar=(Button)findViewById(R.id.btEliminar);
        btEliminar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ContentResolver cr=getContentResolver();
				cr.delete(ClientesProvider.CONTENT_URI,Clientes.COL_NOMBRE+"='cliente1'", null);
				
			}
		});
        btActualizar=(Button)findViewById(R.id.btActualizar);
        btActualizar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ContentValues values=new ContentValues();
				values.put(Clientes.COL_TELEFONO,"954547575");
				values.put(Clientes.COL_EMAIL,"yomismo@gmail.com");
				
				ContentResolver cr=getContentResolver();
				cr.update(ClientesProvider.CONTENT_URI, values, Clientes.COL_NOMBRE+"='cliente9'", null);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
