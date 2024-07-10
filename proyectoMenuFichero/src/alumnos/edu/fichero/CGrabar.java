package alumnos.edu.fichero;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CGrabar extends Activity{
	
	 private Button btGrabar,btCerrar;
	 
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.grabar);
	      btGrabar=(Button)findViewById(R.id.btGrabar);
	     btGrabar.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				grabarFicheroTarjeta();
			}
		});
	     btCerrar=(Button)findViewById(R.id.btCerrar);
	     btCerrar.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
			    finish();
				
			}
		});
	 }
	 
	/* private void grabarFichero()
	 {
		 EditText usuario=(EditText)findViewById(R.id.tbNombre);
		 EditText mensaje=(EditText)findViewById(R.id.tbMensaje);
		 
		 try {
			OutputStreamWriter fichero=new OutputStreamWriter(openFileOutput("comentarios.txt",Context.MODE_APPEND));
			String linea=usuario.getText().toString()+";"+mensaje.getText().toString();
			fichero.write(linea+"\n");
			fichero.close();
			crearVentana();
			limpiar();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }*/
	 public void grabarFicheroTarjeta()
	 {
		 EditText usuario=(EditText)findViewById(R.id.tbNombre);
		 EditText mensaje=(EditText)findViewById(R.id.tbMensaje);
		 
		 String estado=Environment.getExternalStorageState();
		 if (estado.equals(Environment.MEDIA_MOUNTED))
		 {
			 File ruta=Environment.getExternalStorageDirectory();
			 File fichero=new File(ruta.getAbsolutePath(),"comentarios.txt");
			 try {
				OutputStreamWriter escribir=new OutputStreamWriter(new FileOutputStream(fichero,true));
				escribir.write(usuario.getText().toString()+";"+mensaje.getText().toString()+"\n");
				escribir.close();
				crearVentana();
				
			} catch (FileNotFoundException e) {
			     Log.e("Ficheros","fichero no encontrado");
			} catch (IOException e) {
				Log.e("Ficheros","no se puede grabar en el fichero");
			}
		 }
		 else
		 {
			 crearVentana("tarjeta no disponible");
		 }
	 }
	 private void limpiar()
	 {
		 EditText usuario=(EditText)findViewById(R.id.tbNombre);
		 EditText mensaje=(EditText)findViewById(R.id.tbMensaje);
		 usuario.setText("");
		 mensaje.setText("");
	 }
	 private void crearVentana()
	 {
		 Builder  ventana=new Builder(this);
		 ventana.setTitle("Operacion Fichero");
		 ventana.setMessage("Datos grabados");
		 ventana.setPositiveButton("Ok", null); 
		 ventana.show();
	 }
	 private void crearVentana(String texto)
	 {
		 Builder  ventana=new Builder(this);
		 ventana.setTitle("Operacion Fichero");
		 ventana.setMessage(texto);
		 ventana.setPositiveButton("Ok", null); 
		 ventana.show();
	 }
}
