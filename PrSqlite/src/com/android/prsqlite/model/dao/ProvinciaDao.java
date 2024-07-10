package com.android.prsqlite.model.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.prsqlite.dbo.DBOpenHelperProvincia;
import com.android.prsqlite.model.Provincia;

public class ProvinciaDao {
	DBOpenHelperProvincia helperSqlite;
	SQLiteDatabase db;
	String res;

	public ProvinciaDao(Context context) {
		helperSqlite = new DBOpenHelperProvincia(context, "provincias.sqlite", 1);
	}

	/**
	 * Insertar una provincia en la BD.
	 * @param p Objeto provincia a insertar en la BD.
	 * @return Si la insercion tuvo exito.
	 */
	public boolean insertarProvincia(Provincia p) {
		long res = -1;
		ContentValues nuevoRegistro;

		db = helperSqlite.getWritableDatabase();
		if (p != null && db != null) {
			nuevoRegistro = new ContentValues();
			nuevoRegistro.put("provincia", p.getNombre());
			nuevoRegistro.put("descripcion", p.getDescripcion());
			nuevoRegistro.put("imagen", p.getImagen());

			res = db.insert("Provincias", null, nuevoRegistro);
			db.close();
		}

		return (res != -1) ? true : false;
	}

	/**
	 * Obtener las provincias guardadas en la BD.
	 * @param p ArrayList de objetos Provincia en el que almacenar el contenido de la BD.
	 * @return El numero de registros leidos de la BD.
	 */
	public int retrieveProvincias(ArrayList<Provincia> p) {
		String[] cols = new String[] { "id, provincia", "descripcion", "imagen" };
		int r = 0;
		Cursor c;
		
		db = helperSqlite.getReadableDatabase();
		if (db != null && p != null) {
			c = db.query("Provincias", cols, null, null, null, null, null);
			if (c.moveToFirst()) {
				r = c.getCount();
				do {
					p.add(new Provincia(c.getString(1), c.getString(2), Integer.parseInt(c.getString(3))));
				} while (c.moveToNext());
			}
			db.close();
		}

		return r;
	}

	/**
	 * Modificar Provincia en la BD.
	 * @param p Provincia a modificar.
	 * @param id Id de la provincia a modificar.
	 * @return Numero de registros modificados.
	 */
	public int actualizarProvincia(Provincia p, String where_prov) {
		ContentValues val;
		int r = 0;

		db = helperSqlite.getWritableDatabase();
		if (db != null && p != null) {
			val = new ContentValues();
			val.put("provincia", p.getNombre());
			val.put("descripcion", p.getDescripcion());
			val.put("imagen", p.getImagen());

			r = db.update("Provincias", val, "provincia = '" + where_prov + "'", null);
			db.close();
		}
		return r;
	}

	/**
	 * Comprueba si la tabla esta vacia
	 * @return si la tabla esta vacia.
	 */
	public boolean isTablaProvinciasRellena() {
		return (retrieveProvincias(new ArrayList<Provincia>()) > 0) ? true : false;
	}
}
