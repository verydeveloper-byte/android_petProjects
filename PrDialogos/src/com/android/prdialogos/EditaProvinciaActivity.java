package com.android.prdialogos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class EditaProvinciaActivity extends Activity implements OnClickListener {

	private EditText etProvincia;
	private ImageView ivProvincia;
	private Button btAceptar;
	private Button btCancelar;
	// Provincia obj del Activity que nos llama
	Provincia p;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editar_provincia);

		cargar();
	}

	private void cargar() {
		etProvincia = (EditText) findViewById(R.id.etProvincia);
		ivProvincia = (ImageView) findViewById(R.id.ivProvincia);
		btAceptar = (Button) findViewById(R.id.btAceptar);
		btCancelar = (Button) findViewById(R.id.btCancelar);
		
		// Para recuperar valores del objeto Provincia que nos llega
		p = (Provincia) getIntent().getParcelableExtra("provincia");
		etProvincia.setText(p.getNombre());
		ivProvincia.setImageResource(p.getImagen());
		btAceptar.setOnClickListener(this);
		btCancelar.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btAceptar:
			Intent returnIntent = new Intent();
			returnIntent.putExtra("prov_name", etProvincia.getText().toString());
			returnIntent.putExtra("position", this.getIntent().getStringExtra("position"));
			
			setResult(RESULT_OK, returnIntent);
			finish();
			break;
		case R.id.btCancelar:
			finish();
			break;
		}
	}
}
