package com.android.prfragments;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.prfragments.provincia.Provincia;


public class DetailPortraitActivity extends Activity {

	TextView tvPortProvName;
	TextView tvPortDesc;
	ImageView ivPortProvincia;
	Button btAtras;
	Provincia p;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.detalle_portrait);
		cargar();
	}

	private void cargar() {
		tvPortProvName = (TextView) findViewById(R.id.tvPortProvName);
		tvPortDesc = (TextView) findViewById(R.id.tvPortDesc);
		ivPortProvincia = (ImageView) findViewById(R.id.ivPortProvincia);
		p = this.getIntent().getParcelableExtra("provincia");

		tvPortProvName.setText(p.getNombre());
		tvPortDesc.setText(p.getDescripcion());
		ivPortProvincia.setImageResource(p.getImagen());

	}

}
