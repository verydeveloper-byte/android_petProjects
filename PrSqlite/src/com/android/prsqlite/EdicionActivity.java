package com.android.prsqlite;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.prsqlite.model.Provincia;

public class EdicionActivity extends Activity implements OnClickListener {

	Provincia p;
	String nombreProvinciaAntiguo;
	EditText etProv;
	ImageView ivProv;
	Button btGuardar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.edicion_layout);

		cargar();
	}

	private void cargar() {
		etProv = (EditText) findViewById(R.id.etProv);
		ivProv = (ImageView) findViewById(R.id.ivProv);
		btGuardar = (Button) findViewById(R.id.btGuardar);

		p = getIntent().getParcelableExtra("provincia");
		// guardo el nombre original porque es el where
		nombreProvinciaAntiguo = p.getNombre();
		this.setTitle("Edicion " + p.getNombre());
		etProv.setText(p.getNombre());
		ivProv.setImageResource(p.getImagen());
		btGuardar.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btGuardar:
			
			p.setNombre(etProv.getText().toString());
			this.getIntent().putExtra("nuevaProv", p);
			this.getIntent().putExtra("nombreProvinciaAntiguo", nombreProvinciaAntiguo);
			setResult(Activity.RESULT_OK, this.getIntent());
			finish();
			break;
		}

	}
}

