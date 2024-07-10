package alumnos.edu.fichero;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.widget.EditText;

public class TextoMensaje extends EditText {

	private Paint p1,p2;
	
	public TextoMensaje(Context context, AttributeSet attrs) {
		super(context, attrs);
	    p1=new Paint(Paint.ANTI_ALIAS_FLAG);
	    p1.setColor(Color.BLACK);
	    p1.setStyle(Style.FILL);
	    
	    p2=new Paint(Paint.ANTI_ALIAS_FLAG);
	    p2.setColor(Color.WHITE);    
	}
	 public void onDraw(Canvas canva)
	 {
		 super.onDraw(canva);
		 canva.drawRect(this.getWidth()-30, 5, this.getWidth()-5,20, p1);
		 canva.drawText(""+this.getText().toString().length(), this.getWidth()-27, 16, p2);
	 }
  
}
