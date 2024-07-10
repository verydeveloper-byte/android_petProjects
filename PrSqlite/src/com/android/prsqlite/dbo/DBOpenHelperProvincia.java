/*
 * adb shell 'run-as com.android.prsqlite cat /data/data/com.android.prsqlite/databases/provincias.sqlite > /sdcard/provincias.sqlite'
 */
package com.android.prsqlite.dbo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelperProvincia extends SQLiteOpenHelper {
	private String sqlCreate = "CREATE TABLE Provincias(" +
			"id INTEGER PRIMARY KEY AUTOINCREMENT," +
			"provincia NVARCHAR(20) NOT NULL," +
			"descripcion NVARCHAR(20) NOT NULL," +
			"imagen INTEGER NOT NULL)";

	public DBOpenHelperProvincia(Context context, String nombre, int version) {
		super(context, nombre, null, version);
	}

	/**
	 * Callback que es invocado por la clase SQLiteOpenHelper cuando
	 * sea necesaria la creacion de la BD (cuando no exista). Normalmente
	 * aqui se crea la estructura de la BD y la insercion de datos iniciales.
	 * @param db La base de datos.
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// ejecutar codigo SQL directamente.
		db.execSQL(sqlCreate);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
		//      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
		//      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
		//      a la nueva, por lo que este método debería ser más elaborado.

		// Se elimina la versión anterior de la tabla
		db.execSQL("DROP TABLE IF EXISTS Provincias");

		// Se crea la nueva versión de la tabla
		db.execSQL(sqlCreate);
	}

}
