package edu.alumno.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class ClientesProvider extends ContentProvider {

	private static final String URI=
			"content://edu.alumno.provider.tabla/clientes";
	public static final Uri CONTENT_URI=Uri.parse(URI);

	private static final int CLIENTES=1;
	private static final int CLIENTES_ID=2;
	private static final UriMatcher uriMatcher;
	
	static{
		uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI("edu.alumno.provider.tabla","clientes", CLIENTES);
		uriMatcher.addURI("edu.alumno.provider.tabla","clientes/#", CLIENTES_ID);
	}
	
	private ClientesSqliterHelp clientesBD;
	private static final String BD_NOMBRE="BDclientes";
	private static final int BD_VERSION=1;
	private static final String TABLA_CLIENTE="clientes";
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int cont=0;
		String where=selection;
		if(uriMatcher.match(uri)==CLIENTES_ID)
		{
			where="_id=" +  uri.getLastPathSegment();
		}
		SQLiteDatabase db=clientesBD.getWritableDatabase();
		cont=db.delete(TABLA_CLIENTE,where, selectionArgs);
		return cont;
	}

	@Override
	public String getType(Uri uri) {
		int tipo=uriMatcher.match(uri);
		switch(tipo)
		{
			case CLIENTES: return "vnd.android.cursor.dir/vnd.alumno.clientes";
					
			case CLIENTES_ID:
						return "vnd.android.cursor.item/vnd.alumno.clientes";
			default:
				return null;
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long registroId=1;
		SQLiteDatabase db=clientesBD.getWritableDatabase();
		registroId=db.insert(TABLA_CLIENTE,null,values);
		Uri newUri=ContentUris.withAppendedId(CONTENT_URI, registroId);
		return newUri;
	}

	@Override
	public boolean onCreate() {
		clientesBD=new ClientesSqliterHelp(getContext(),BD_NOMBRE,null, BD_VERSION);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		String condicion=selection;
		if (uriMatcher.match(uri)==CLIENTES_ID)
		{
			condicion="_id="+uri.getLastPathSegment();
		}
		SQLiteDatabase db=clientesBD.getWritableDatabase();
		Cursor c=db.query(TABLA_CLIENTE, projection,condicion,selectionArgs, null, null, sortOrder);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,String[] selectionArgs) {
		int cont=0;
		String where=selection;
		if(uriMatcher.match(uri)==CLIENTES_ID)
		{
			where="_id="+uri.getLastPathSegment();
		}
		SQLiteDatabase db=clientesBD.getWritableDatabase();
		cont=db.update(TABLA_CLIENTE, values, where, selectionArgs);
		return cont;
	}
   
}
