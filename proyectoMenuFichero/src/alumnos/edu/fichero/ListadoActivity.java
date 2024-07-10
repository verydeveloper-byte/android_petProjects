package alumnos.edu.fichero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ListadoActivity extends Activity {
	private ArrayList<Comentarios> comentar;
	private ListView listas;
	private String texto;
	private AdaptadorLista adapLista;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablacomentarios);
        
        comentar=new ArrayList<Comentarios>();
        leerFicheroTarjeta();
        Comentarios [] vcomentar=new Comentarios[comentar.size()];
        vcomentar=comentar.toArray(vcomentar);
        
        adapLista=new AdaptadorLista(this,vcomentar);
        listas=(ListView)findViewById(R.id.lvComentarios);
        listas.setAdapter(adapLista);
        registerForContextMenu(listas);
        listas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Comentarios aux=(Comentarios)comentar.get(arg2);
				texto=aux.getUsuario();
				
				return false;
			}
        	
		});
        
    }
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
	{
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflado=getMenuInflater();
		inflado.inflate(R.menu.menu_ctx, menu);
	}
	@Override
	public boolean onContextItemSelected(MenuItem elemento)
	{
		switch(elemento.getItemId())
		{
			case R.id.ctx_eliminar:
								eliminar();
			case R.id.ctx_modificar:
					 			return true;
			case R.id.ctx_mostrar:
					 			return true;
			default: return super.onContextItemSelected(elemento) ;
		}
		
	}
	 private void crearVentana()
	 {
		 Builder  ventana=new Builder(this);
		 ventana.setTitle("Operacion Fichero");
		 ventana.setMessage(texto+" se va a eliminar");
		 ventana.setPositiveButton("Ok", null); 
		 ventana.show();
	 }
	private void eliminar()
	{
		//lectura de fichero seleccionable
		try {
			BufferedReader lectura=new BufferedReader(new InputStreamReader(openFileInput("comentarios.txt")));
			String linea=lectura.readLine();
			comentar.clear();
			while(linea!=null)
			{
				String [] datos=linea.split(";");
				if (!datos[0].equals(texto))
				{
					comentar.add(new Comentarios(datos[0],datos[1]));
				}
				linea=lectura.readLine();
			}
            lectura.close();
            
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Grabar los datos al fichero
		 try {
			    Comentarios datos;
				OutputStreamWriter fichero=new OutputStreamWriter(openFileOutput("comentarios.txt",Context.MODE_PRIVATE));
				for (int pos=0;pos<comentar.size();pos++)
				{
					datos=(Comentarios)comentar.get(pos);
					String linea=datos.getUsuario()+";"+datos.getComentario();
					fichero.write(linea+"\n");
				}
				fichero.close();
				crearVentana();
				Comentarios [] vcomentar=new Comentarios[comentar.size()];
			    vcomentar=comentar.toArray(vcomentar);
			    adapLista=new AdaptadorLista(this,vcomentar);
				listas.setAdapter(adapLista);
	    	} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	/*private void leerFichero()
	{
		try {
			BufferedReader lectura=new BufferedReader(new InputStreamReader(openFileInput("comentarios.txt")));
			String linea=lectura.readLine();
			while(linea!=null)
			{
				String [] datos=linea.split(";");
				comentar.add(new Comentarios(datos[0],datos[1]));
				linea=lectura.readLine();
			}
            lectura.close();
            
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	private void leerFicheroTarjeta()
	{
		File ruta=Environment.getExternalStorageDirectory();
		File fichero=new File(ruta.getAbsolutePath(),"comentarios.txt");
		
		try {
			BufferedReader lectura=new BufferedReader(new InputStreamReader(new FileInputStream(fichero)));
			String linea=lectura.readLine();
			while(linea!=null)
			{
				String [] datos=linea.split(";");
				comentar.add(new Comentarios(datos[0],datos[1]));
				linea=lectura.readLine();
			}
            lectura.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
