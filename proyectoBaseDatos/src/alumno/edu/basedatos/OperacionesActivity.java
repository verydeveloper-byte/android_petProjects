package alumno.edu.basedatos;

import alumno.edu.tablas.CrearBaseFiguras;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OperacionesActivity extends Activity {
	private CrearBaseFiguras cbf;
	private Button btGrabar,btBuscar,btActualizar,btEliminar;
	
	public void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);
	       setContentView(R.layout.activity_operaciones);
	       
	       crearBaseDatos();
	       btGrabar=(Button)findViewById(R.id.btGrabar);
	       btGrabar.setOnClickListener(new View.OnClickListener() {
			
	    	   public void onClick(View arg0) {
	    		   grabarTabla();
				}
	       });
	       btBuscar=(Button)findViewById(R.id.btBuscar);
	       btBuscar.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				buscar();
				
			}
	       });
	      btActualizar=(Button)findViewById(R.id.btModificar);
	      btActualizar.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				actualizar();
			}
	      });
	      btEliminar=(Button)findViewById(R.id.btEliminar);
	      btEliminar.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				eliminar();
				limpiar();
			}
	      });
	}
	private void limpiar()
	{
		EditText etTipo=(EditText)findViewById(R.id.etTipo);
		EditText etX1=(EditText)findViewById(R.id.etX1);
		EditText etY1=(EditText)findViewById(R.id.etY1);
		EditText etX2=(EditText)findViewById(R.id.etX2);
		EditText etY2=(EditText)findViewById(R.id.etY2);
		etTipo.setText("");
		etX1.setText("");
		etY1.setText("");
		etX2.setText("");
		etY2.setText("");
	}
	private void crearBaseDatos()
	{
		cbf=new CrearBaseFiguras(this,"BDFiguras", null, 1);
	}
	private void grabarTabla()
	{
		EditText etTipo=(EditText)findViewById(R.id.etTipo);
		EditText etX1=(EditText)findViewById(R.id.etX1);
		EditText etY1=(EditText)findViewById(R.id.etY1);
		EditText etX2=(EditText)findViewById(R.id.etX2);
		EditText etY2=(EditText)findViewById(R.id.etY2);
		
		SQLiteDatabase base=cbf.getWritableDatabase();
		
		if (base!=null)
		{
			String cadena=null;
			cadena="insert into figuras values ('"+ etTipo.getText().toString()+"'";
			cadena+= ","+etX1.getText().toString();
			cadena+= ","+etY1.getText().toString();
			cadena+= ","+etX2.getText().toString();
			cadena+= ","+etY2.getText().toString()+")";
			base.execSQL(cadena);
			base.close();
		}
	}
	private void buscar()
	{
		EditText etTipo=(EditText)findViewById(R.id.etTipo);
		//String consulta="select * from figuras where tipo='" + etTipo.getText().toString() + "'";
		String consulta="select * from figuras where tipo=?";
		
		String[] args=new String[]{etTipo.getText().toString()};
		SQLiteDatabase base=cbf.getReadableDatabase();
		Cursor datos=base.rawQuery(consulta,args);
		if (datos.moveToFirst())
		{
			EditText etX1=(EditText)findViewById(R.id.etX1);
			EditText etY1=(EditText)findViewById(R.id.etY1);
			EditText etX2=(EditText)findViewById(R.id.etX2);
			EditText etY2=(EditText)findViewById(R.id.etY2);
			
			etX1.setText(String.valueOf(datos.getInt(1)));
			etY1.setText(String.valueOf(datos.getInt(2)));
			etX2.setText(String.valueOf(datos.getInt(3)));
			etY2.setText(String.valueOf(datos.getInt(4)));
		}
		base.close();                
	}
    private void actualizar()
    {
    	EditText etTipo=(EditText)findViewById(R.id.etTipo);
		EditText etX1=(EditText)findViewById(R.id.etX1);
		EditText etY1=(EditText)findViewById(R.id.etY1);
		EditText etX2=(EditText)findViewById(R.id.etX2);
		EditText etY2=(EditText)findViewById(R.id.etY2);
		
		ContentValues valores=new ContentValues();
		valores.put("tipo",etTipo.getText().toString());
		valores.put("x1",etX1.getText().toString());
		valores.put("y1",etY1.getText().toString());
		valores.put("x2",etX2.getText().toString());
		valores.put("y2",etY2.getText().toString());
		
		SQLiteDatabase base=cbf.getWritableDatabase();
		base.update("figuras",valores, "tipo='"+ etTipo.getText().toString() +"'",null); 
		base.close();
    }
    private void eliminar()
    {
    	EditText etTipo=(EditText)findViewById(R.id.etTipo);
		
		
		ContentValues valores=new ContentValues();
		valores.put("tipo",etTipo.getText().toString());
		
		
		SQLiteDatabase base=cbf.getWritableDatabase();
		base.delete("figuras", "tipo='"+ etTipo.getText().toString()+"'",null);
		 
		base.close();
    }
}









