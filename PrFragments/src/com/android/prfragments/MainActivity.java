package com.android.prfragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.prfragments.fragmentos.FragmentMaster.OnProvinciaSelectedListener;
import com.android.prfragments.provincia.Provincia;

public class MainActivity extends FragmentActivity implements OnProvinciaSelectedListener {

	TextView tvProvName;
	TextView tvDesc;
	ImageView ivProvincia;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
	}


	@Override
	public void onProvinciaSelected(Provincia p, boolean isOrientationVertical) {
		if (isOrientationVertical) {
			Intent detallePortraitActivity = new Intent(getApplicationContext(), DetailPortraitActivity.class).
					putExtra("provincia", p);
			startActivity(detallePortraitActivity);
		} else {
			tvProvName = (TextView) findViewById(R.id.tvProvName);
			tvDesc = (TextView) findViewById(R.id.tvDesc);
			ivProvincia = (ImageView) findViewById(R.id.ivProvincia);

			tvProvName.setText(p.getNombre());
			tvDesc.setText(p.getDescripcion());
			ivProvincia.setImageResource(p.getImagen());
		}
	}

}
