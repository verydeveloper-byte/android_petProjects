package edu.alumno.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ClientesSqliterHelp extends SQLiteOpenHelper {

	private String sqlCreacion="create table clientes" +
			"(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
			" nombre TEXT," +
			" telefono TEXT," +
			" email TEXT )";
	
	public ClientesSqliterHelp(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(sqlCreacion);
		for (int i=0;i<10;i++)
		{
			String nombre="cliente" + i;
			String telefono="952-43-44-9" + i;
			String email=nombre+"@gmail.com";

			db.execSQL("INSERT INTO clientes (nombre,telefono,email) "+
			    " VALUES ('" + nombre + "','" + telefono + "','" + email+ "')");
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS clientes");
		db.execSQL(sqlCreacion);
		
	}

}
