package ficheros;

import alumnos.edu.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityFicheros extends Activity implements OnClickListener{

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ficheros);
		
		Intent  interno=this.getIntent();
		String mensaje=interno.getStringExtra("mensajefecha");
		TextView tbFecha=(TextView)findViewById(R.id.tbFecha);
		tbFecha.setText(mensaje);
		
		Button btCerrar=(Button) findViewById(R.id.btCerrar);
		btCerrar.setOnClickListener(this);
	}

	public void onClick(View arg0) {
		this.finish();
		
	}
}
