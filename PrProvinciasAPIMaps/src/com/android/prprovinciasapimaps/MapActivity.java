package com.android.prprovinciasapimaps;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.android.prprovinciasapimaps.provincia.Provincia;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity {

	GoogleMap gMap;
	Provincia p;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_activity);
		
		p = this.getIntent().getParcelableExtra("prov");
		double[] coord = p.getCoordenadas();
		
		gMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		gMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		
		CameraUpdate camUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(coord[0], coord[1]), 15);
		gMap.animateCamera(camUpdate);
		
		gMap.addMarker(new MarkerOptions()
				.position(new LatLng(coord[0], coord[1]))
				.title(p.getNombre())
				.snippet(p.getDescripcion())
				//.icon(BitmapDescriptorFactory.fromResource(p.getImagen())
				);
		
	}
}
