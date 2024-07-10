package alumno.edu.tablas;

import alumno.edu.basedatos.PintarActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Pizarra extends View{

	private CrearBaseFiguras cbf;
	private PintarActivity pa;
	
	public Pizarra(Context context,CrearBaseFiguras vcbf) {
		super(context);
		pa=(PintarActivity)context;
		cbf=vcbf;
	}
	@Override
	public void onDraw(Canvas canva)
	{ 
		Paint lapiz=new Paint();
		canva.drawRGB(250,250,0);
		 SQLiteDatabase base=cbf.getReadableDatabase();
		 Cursor c=base.rawQuery("select * from figuras",null);
		 while(c.moveToNext())
		 {
			 int x1=c.getInt(1);
			 int y1=c.getInt(2);
			 int x2=c.getInt(3);
			 int y2=c.getInt(4);
			
			 lapiz.setARGB(255,0,0,0);
			 canva.drawLine(x1, y1, x2, y2, lapiz);
		 }
		 base.close();
		 
		
		 Paint lapiz2=new Paint();
		 lapiz2.setARGB(250,250,0,0);
		 lapiz2.setStrokeWidth(5);
		 lapiz2.setStyle(Paint.Style.STROKE);
		 canva.drawCircle(pa.getX1(), pa.getY1(),20,lapiz2);
	}
	

}
