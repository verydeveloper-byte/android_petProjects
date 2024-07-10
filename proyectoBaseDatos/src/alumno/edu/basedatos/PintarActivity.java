package alumno.edu.basedatos;

import alumno.edu.tablas.CrearBaseFiguras;
import alumno.edu.tablas.Pizarra;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

public class PintarActivity extends Activity implements OnTouchListener{
    private int x1;
    private int y1;
    private Pizarra p;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pintar);
        
        LinearLayout fondo=(LinearLayout)findViewById(R.id.fondo);
        CrearBaseFiguras cbf=new CrearBaseFiguras(this,"BDFiguras", null, 1);
        p=new Pizarra(this,cbf);
        fondo.setOnTouchListener(this);
        fondo.addView(p);
    }

	public boolean onTouch(View v, MotionEvent event) {
	    x1=(int)event.getX();
	    y1=(int)event.getY();
	    p.invalidate();
	    
		return false;
	}
	public int getX1()
	{
		return x1;
	}
	public int getY1()
	{
		return y1;
	}
}
