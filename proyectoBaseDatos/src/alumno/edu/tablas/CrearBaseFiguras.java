package alumno.edu.tablas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CrearBaseFiguras extends SQLiteOpenHelper {

	private String comando;
	public CrearBaseFiguras(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		comando="create table figuras (tipo text, x1 integer, y1 integer, x2 integer, y2 integer)";
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(comando);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists figuras");
		db.execSQL(comando);
		
	}

}
