/*
 * javascript:void(prompt('',gApplication.getMap().getCenter()))
 */
package com.android.prmapsapi2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity {

	private SupportMapFragment mFragment;
	private GoogleMap gMap;
	private CameraUpdate camUpdate;
	private LatLng malaga = new LatLng(36.719726, -4.421611);

	/*
	 * Esta funcion crea el objeto GoogleMap y manipula 
	 * los datos necesarios del mapa.
	 */
	//	private void setUpMapIfNeeded() {
	//		// Do a null check to confirm that we have not already instantiated the map.
	//		if (gMap == null) {
	//			gMap = ((SupportMapFragment) this.getSupportFragmentManager().findFragmentByTag("fragment_mapa"))
	//					.getMap();
	//			// Check if we were successful in obtaining the map.
	//			if (gMap != null) {
	//				//The Map is verified. It is now safe to manipulate the map.
	//				gMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
	//			}
	//		}
	//	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		/*
		 * Mediante codigo:
		 * primero creamos una nueva instancia de MapFragment mediante 
		 * SupportMapFragment.newInstance(); y la añadimos al container 
		 * del layout de la Activity.
		 * Tenemos que llamar a .getSupportFragmentManager().executePendingTransactions()
		 * para que el commit() se haga efectivo inmediatamente y podamos obtener
		 * el objeto GoogleMap sin problemas.
		 * Despues obtenemos el objeto GoogleMap con el que poder trabajar con el mapa
		 * llamando a setUpMapIfNeeded() (no se pq cuando intento obtener el objeto
		 * GoogleMap fuera de esta funcion, devuelve siempre nulo.
		 * 
		 * Trabajar con los fragmentos de mapas mediante codigo resulta ser 
		 * muy engorroso ...
		 */
		/*
		 * Estado inicial del mapa nada mas ejecutarse la app
		 * mediante creacion de objeto GoogleMapOptions y pasando
		 * las opciones como parametros al metodo newInstance del 
		 * obj SupportFragmentManager.
		 */
		//		GoogleMapOptions opt = new GoogleMapOptions();
		//		opt.mapType(GoogleMap.MAP_TYPE_HYBRID)
		//			.compassEnabled(false)
		//			.rotateGesturesEnabled(false);
		//			
		//		mFragment = SupportMapFragment.newInstance(opt);
		//		this.getSupportFragmentManager().beginTransaction().
		//		add(R.id.container, mFragment, "fragment_mapa").
		//		commit();
		// INSTRUCCION MUY IMPORTANTE, hacer el commit() inmediatamente
		//		this.getSupportFragmentManager().executePendingTransactions();
		/*
		 * Hay que manipular el objeto GoogleMap desde esta funcion
		 * ya que a saber por que, si accedemos a cualquier metodo de 
		 * GoogleMap a continuacion de esta llamada, el objeto es nulo (?!?!?!?!?)
		 */
		//		setUpMapIfNeeded();

		/* 
		 * Mediante Fragments:
		 * solo hay que añadir el elemento <fragment> en el XML del layout
		 * (cuidado de cargar la clase SupportMapFragment si usamos la API de soporte)
		 */
		gMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		gMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

		/*
		 * mover la camara a una coordenada o hacer zoom
		 * (movimientos basicos) se hacen con CameraUpdateFactory
		 */
		camUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(36.6745008, -4.7335975), 15);
		gMap.moveCamera(camUpdate);

		/*
		 * Para cambiar el resto de parametros de la camara
		 * (o varios a la vez) se necesita un obj CameraPosition
		 */
		CameraPosition camPos = new CameraPosition.Builder()
		.target(malaga)
		.zoom(16)
		.bearing(45) // orientacion
		.tilt(70) // angulo de vision
		.build();

		camUpdate = CameraUpdateFactory.newCameraPosition(camPos);
		gMap.animateCamera(camUpdate);

		/*
		 * Para conocer la posicion de la camara, solo hay que
		 * llamar a getCameraPosition().
		 */
		CameraPosition camPos2 = gMap.getCameraPosition();
		LatLng coord = camPos2.target;
		double lat = coord.latitude;
		double lon = coord.longitude;
		float tilt = camPos2.tilt;
		float bearing = camPos2.bearing;

		Toast.makeText(this.getApplicationContext(), 
				"longitud: " + String.valueOf(lon) + 
				"latitud: " + String.valueOf(lat) +
				"tilt: " + String.valueOf(tilt) + 
				"bearing: " + String.valueOf(bearing), Toast.LENGTH_LONG).show();

		/*
		 * Agregar un marcador y su listener.
		 */
		gMap.addMarker(new MarkerOptions()
			.position(malaga)
			//.icon(BitmapDescriptorFactory.fromResource(R.drawable.malaga))
			.title("Malaga")
			.snippet("Centro de Malaga"));
		
		gMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker marker) {
				Toast.makeText(MainActivity.this.getApplicationContext(), marker.getTitle(), Toast.LENGTH_LONG).show();
				return false;
			}
		});

		/*
		 * Evento que se llama al pulsar sobre algun
		 * area del mapa.
		 */
		gMap.setOnMapClickListener(new OnMapClickListener() {
			
			@Override
			public void onMapClick(LatLng point) {
				/*
				 * devolver las coordenadas de la zona
				 * en la que pulsemos en un Toast.
				 */
				Toast.makeText(
						MainActivity.this, 
						"Lat: " + point.latitude + "\nLon: " + point.longitude,
						Toast.LENGTH_SHORT).show();
			}
		});
		
		/*
		 * Evento que se llama al mover la camara del mapa
		 */
		gMap.setOnCameraChangeListener(new OnCameraChangeListener() {
			
			@Override
			public void onCameraChange(CameraPosition pos) {
				Toast.makeText(
						MainActivity.this, 
						"Lat: " + pos.target.latitude + "\nLon: " + pos.target.longitude
						, Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		//		setUpMapIfNeeded();
	}
}
