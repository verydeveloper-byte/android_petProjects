/*
 * Android2014_7.pdf
 * Hacemos una aplicación con 5 botones y un TextView.
 * Botones:
 * 1.- Leer archivo raw y mostrarlo en el TextView
 * 2.- Escribir archivo en memoria interna 
 * 3.- Leer archivo de memoria interna y mostrarlo en el TextView
 * 4.- Escribir archivo en memoria externa 
 * 5.- Leer archivo de memoria externa y mostrarlo en el TextView
 * 
 */
package com.android.prandficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private final String ficheroMInterna = "prueba_mem_interna.txt";
	private final String ficheroSD = "prueba_mem_externa.txt";
	Button btLeer_raw;
	Button btEscribir_fichero_minterna;
	Button btLeer_fichero_minterna;
	Button btEscribir_fichero_sdcard;
	Button btLeer_fichero_sdcard;
	TextView tvMostrar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cargar();
	}

	private void cargar() {
		(btLeer_raw = (Button) this.findViewById(
				R.id.btLeer_raw)).
				setOnClickListener(this);

		(btEscribir_fichero_minterna = (Button) this.findViewById(
				R.id.btEscribir_fichero_minterna)).
				setOnClickListener(this);

		(btLeer_fichero_minterna = (Button) this.findViewById(
				R.id.btLeer_fichero_minterna)).
				setOnClickListener(this);

		(btEscribir_fichero_sdcard = (Button) this.findViewById(
				R.id.btEscribir_fichero_sdcard)).
				setOnClickListener(this);

		(btLeer_fichero_sdcard = (Button) this.findViewById(
				R.id.btLeer_fichero_sdcard)).
				setOnClickListener(this);

		tvMostrar = (TextView) this.findViewById(R.id.tvMostrar);
	}

	private String leer_raw() {
		String lineaLeida = null;

		try {
			// android proporciona el metodo openRawResource()
			// para acceder a ficheros en res/raw/
			InputStream fraw =
					getResources().openRawResource(R.raw.raw_prueba);

			// objeto BufferedReader para obtener una lectura
			// comoda con readLine()
			BufferedReader brin =
					new BufferedReader(new InputStreamReader(fraw));

			lineaLeida = brin.readLine();

			fraw.close();
		} catch (Exception ex) {
			Log.e("Ficheros", "Error al leer fichero desde recurso raw");
		}

		return lineaLeida;
	}

	private boolean escribirFicheroMInterna() {
		try {
			// clase que convierte un flujo de texto
			// en un flujo de bytes OutputStream.
			// Es decir, le pasamos una cadena con write()
			// que luego se convertira a un flujo de bytes
			// en su almacenamiento.
			OutputStreamWriter fout =
					new OutputStreamWriter(
							// android proporciona el metodo openFileOutput()
							// para acceder a ficheros en memoria interna
							// /data/data/com.android.prandficheros/files/prueba_mem_interna.txt"
							openFileOutput(ficheroMInterna, Context.MODE_PRIVATE));

			fout.write("Texto de prueba.");
			fout.close();
			return true;
		} catch (Exception ex) {
			Log.e("Ficheros", "Error al escribir fichero a memoria interna");
			return false;
		}
	}

	private String leerFicheroMInterna() {
		String lineaLeida = null;

		try {
			// objeto BufferedReader para 
			// lectura comoda con readLine()
			BufferedReader fin =
					new BufferedReader(
							new InputStreamReader(
									openFileInput(ficheroMInterna)));
			lineaLeida = fin.readLine();
			fin.close();
		} catch (Exception ex) {
			Log.e("Ficheros", "Error al leer fichero desde memoria interna");
		}

		return lineaLeida;
	}

	private boolean escribirFicheroSD() {
		File ruta_sd = getExternalFilesDir(null);
		File f = new File(ruta_sd.getAbsolutePath(), ficheroSD);

		switch (Environment.getExternalStorageState()) {
		case Environment.MEDIA_MOUNTED:
			try {
				FileOutputStream fs = new FileOutputStream(f);
				OutputStreamWriter fout = new OutputStreamWriter(fs);
				fout.write("texto de prueba en memoria externa");
				fout.close();

				return true;
			} catch (Exception e) {
				Log.e("Ficheros", "Error al escribir fichero a la tarjeta SD");
			}
			break;
		}
		return false;
	}

	private String leerFicheroSD() {
		File ruta_sd = getExternalFilesDir(null);
		File f = new File(ruta_sd, ficheroSD);
		String lineaLeida = null;

		switch (Environment.getExternalStorageState()) {
		case Environment.MEDIA_MOUNTED:
			try {
				FileInputStream fis = new FileInputStream(f);
				InputStreamReader fin = new InputStreamReader(fis);
				BufferedReader bin = new BufferedReader(fin);

				lineaLeida = bin.readLine();
				bin.close();
			} catch (Exception ex) {
				Log.e("Ficheros", "Error al leer fichero desde memoria interna");
			}
		}
		return lineaLeida;
	}

	@Override
	public void onClick(View v) {
		String lineaLeida = null;
		
		switch (v.getId()) {
		case R.id.btLeer_raw:
			//Log.d("aaaaa", "leer raw");
			tvMostrar.setText(leer_raw());
			break;
		case R.id.btEscribir_fichero_minterna:
			tvMostrar.setText((escribirFicheroMInterna() ? 
					"Fichero escrito correctamente en memoria interna" : 
					"ocurrio un error al escribir en memoria interna"));
			break;
		case R.id.btLeer_fichero_minterna:
			if ( (lineaLeida = leerFicheroMInterna()) != null)
				tvMostrar.setText(lineaLeida);
			else
				tvMostrar.setText("Fallo al leer de memoria interna");

			tvMostrar.setText(lineaLeida);
			break;
		case R.id.btEscribir_fichero_sdcard:
			escribirFicheroSD();
			tvMostrar.setText((escribirFicheroSD() ?
					"Fichero escrito correctamente en tarjeta SD" :
					"ocurrio un error al escribir en SD"));
			break;
		case R.id.btLeer_fichero_sdcard:
			if ( (lineaLeida = leerFicheroSD()) != null)
				tvMostrar.setText(lineaLeida);
			else
				tvMostrar.setText("Fallo al leer de tarjetaSD");

			tvMostrar.setText(lineaLeida);
			break;
		}
	}

}
